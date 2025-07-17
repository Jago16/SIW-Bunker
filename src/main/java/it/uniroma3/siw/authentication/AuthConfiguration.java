package it.uniroma3.siw.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import it.uniroma3.siw.model.User;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

	@Autowired
	private DataSource datasource;

	@Autowired
	@Lazy
	private SetCurrentUser setCurrentUser;

	@Autowired
	private RemoveCurrentUser removeCurrentUser;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(requests -> {
				requests
					// 🌐 Accesso pubblico
					.requestMatchers(HttpMethod.GET, "/", "/homePage", "/bunker-mappa").permitAll()
					.requestMatchers(HttpMethod.GET, "/login", "/register").permitAll()
					.requestMatchers(HttpMethod.POST, "/login", "/register","/success").permitAll()
					.requestMatchers(HttpMethod.GET, "/css/**", "/images/**", "/js/**", "/favicon.ico", "/logo.png").permitAll()

					// 🏢 Bunker - solo ADMIN
					.requestMatchers(HttpMethod.GET, "/bunker", "/bunker/create", "/bunker/{id}").hasAuthority(User.ADMIN_ROLE)
					.requestMatchers(HttpMethod.POST, "/bunker/create").hasAuthority(User.ADMIN_ROLE)

					// 👤 Sopravvissuti - ADMIN
					.requestMatchers(HttpMethod.GET, "/sopravvissuti", "/sopravvissuti/create", "/sopravvissuti/{id}").hasAuthority(User.ADMIN_ROLE)
					.requestMatchers(HttpMethod.POST, "/sopravvissuti/create").hasAuthority(User.ADMIN_ROLE)

					// 👤 Sopravvissuto loggato - USER
					.requestMatchers(HttpMethod.GET, "/sopravvissuti/profilo").hasAuthority(User.USER_ROLE)

					// 🎯 Missioni
					.requestMatchers(HttpMethod.GET, "/missioni").hasAuthority(User.USER_ROLE)
					.requestMatchers(HttpMethod.GET, "/missioni/{id}").hasAuthority(User.USER_ROLE)
					.requestMatchers(HttpMethod.GET, "/missioni/create").hasAuthority(User.ADMIN_ROLE)
					.requestMatchers(HttpMethod.POST, "/missioni/create").hasAuthority(User.ADMIN_ROLE)

					// 📜 Eventi
					.requestMatchers(HttpMethod.GET, "/eventi").hasAuthority(User.USER_ROLE)
					.requestMatchers(HttpMethod.GET, "/eventi/{id}").hasAuthority(User.USER_ROLE)
					.requestMatchers(HttpMethod.GET, "/eventi/create").hasAuthority(User.ADMIN_ROLE)
					.requestMatchers(HttpMethod.POST, "/eventi/create").hasAuthority(User.ADMIN_ROLE)
					.requestMatchers(HttpMethod.GET, "/dashboard").authenticated()

					
					.requestMatchers(HttpMethod.GET, "/bunker-mappa").permitAll()
					
					// Qualsiasi altra richiesta → autenticata
					.anyRequest().authenticated();
			})
			.exceptionHandling(handling -> handling.accessDeniedPage("/"))
			.formLogin(login -> login
				    .loginPage("/login")
				    .loginProcessingUrl("/login")
				    .successHandler(setCurrentUser)
				    .defaultSuccessUrl("/dashboard", true)
				    .failureUrl("/login?error=true")
				    .permitAll()
				)


//
//			.oauth2Login(oauth2 -> oauth2
//				.loginPage("/login")
//				.successHandler(setCurrentUser)
//			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.logoutSuccessHandler(removeCurrentUser)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.clearAuthentication(true)
				.permitAll()
			);

		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(datasource);
		manager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
		manager.setAuthoritiesByUsernameQuery("SELECT c.username, u.role FROM credentials c JOIN users u ON u.credentials_id = c.id WHERE c.username = ?");
		return manager;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}

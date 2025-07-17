package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.BunkerService;
import it.uniroma3.siw.service.EventoService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private BunkerService bunkerService;

    @Autowired
    private UserService userService;

    // 🔍 Lista eventi del proprio bunker
    @GetMapping
    public String listEventi(Model model) {
        User user = userService.getCurrentUser();
        Bunker bunker = user.getSopravvissuto().getBunker();
        List<Evento> eventi = eventoService.findByBunker(bunker);
        model.addAttribute("eventi", eventi);
        return "eventi/list";
    }

    // 📄 Dettaglio evento
    @GetMapping("/{id}")
    public String showEvento(@PathVariable("id") Long id, Model model) {
        Evento e = eventoService.findById(id).orElse(null);
        if (e == null) return "errors/notFound";
        model.addAttribute("evento", e);
        return "eventi/show";
    }

    // ➕ Form creazione evento (admin)
    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showCreateForm(Model model) {
        model.addAttribute("evento", new Evento());
        model.addAttribute("bunkerList", bunkerService.findAll());
        return "eventi/form";
    }

    // 💾 Salva nuovo evento (admin)
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createEvento(@Valid @ModelAttribute("evento") Evento evento,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bunkerList", bunkerService.findAll());
            return "eventi/form";
        }
        eventoService.save(evento);
        return "redirect:/eventi";
    }
}

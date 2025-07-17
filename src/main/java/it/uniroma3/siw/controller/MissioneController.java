package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.model.Missione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.BunkerService;
import it.uniroma3.siw.service.MissioneService;
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
@RequestMapping("/missioni")
public class MissioneController {

    @Autowired
    private MissioneService missioneService;

    @Autowired
    private BunkerService bunkerService;

    @Autowired
    private UserService userService;

    // 🟢 Lista missioni del bunker del sopravvissuto loggato
    @GetMapping
    public String listMissioni(Model model) {
        User user = userService.getCurrentUser();
        Bunker bunker = user.getSopravvissuto().getBunker();
        List<Missione> missioni = missioneService.findByBunker(bunker);
        model.addAttribute("missioni", missioni);
        return "missioni/list";
    }

    // 🔍 Dettaglio missione
    @GetMapping("/{id}")
    public String showMissione(@PathVariable("id") Long id, Model model) {
    	Missione m = missioneService.findById(id).orElse(null);
    	if (m == null) {
    	    return "errors/notFound";
    	}

        model.addAttribute("missione", m);
        return "missioni/show";
    }

    // 📝 Form creazione missione (solo admin)
    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showCreateForm(Model model) {
        model.addAttribute("missione", new Missione());
        model.addAttribute("bunkerList", bunkerService.findAll());
        return "missioni/form";
    }

    // 💾 Salva missione (solo admin)
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createMissione(@Valid @ModelAttribute("missione") Missione missione,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bunkerList", bunkerService.findAll());
            return "missioni/form";
        }

        missioneService.save(missione);
        return "redirect:/missioni";
    }
}


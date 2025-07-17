package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.service.BunkerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bunker")
public class BunkerController {

    @Autowired
    private BunkerService bunkerService;

    // Mostra tutti i bunker
    @GetMapping
    public String listBunkers(Model model) {
        model.addAttribute("bunker", bunkerService.findAll());
        return "bunker/list"; // templates/bunkers/list.html
    }

    // Mostra i dettagli di un bunker
    @GetMapping("/{id}")
    public String showBunker(@PathVariable("id") Long id, Model model) {
        bunkerService.findById(id).ifPresentOrElse(
            bunker -> model.addAttribute("bunker", bunker),
            () -> model.addAttribute("notFound", true)
        );
        return "bunker/show"; // templates/bunkers/show.html
    }

    // Form per creare un nuovo bunker (GET)
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("bunker", new Bunker());
        return "bunker/form"; // templates/bunkers/form.html
    }

    // Gestione form (POST)
    @PostMapping("/create")
    public String createBunker(@Valid @ModelAttribute("bunker") Bunker bunker,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "bunker/form";
        }

        bunkerService.save(bunker);
        return "redirect:/bunker";
    }
}


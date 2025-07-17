package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.model.Sopravvissuto;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.BunkerService;
import it.uniroma3.siw.service.MissioneService;
import it.uniroma3.siw.service.SopravvissutoService;
import it.uniroma3.siw.service.StanzaService;
import it.uniroma3.siw.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/sopravvissuti")
public class SopravvissutoController {

    @Autowired
    private SopravvissutoService sopravvissutoService;

    @Autowired
    private BunkerService bunkerService;

    @Autowired
    private MissioneService missioneService;

    @Autowired
    private StanzaService stanzaService;

    @Autowired
    private UserService userService;

    // ✅ DASHBOARD personale del sopravvissuto loggato
    @GetMapping("/profilo")
    public String profiloSopravvissuto(Model model) {
        User user = userService.getCurrentUser();
        Sopravvissuto me = user.getSopravvissuto();

        if (me == null || me.getBunker() == null) {
            model.addAttribute("errore", "Non sei associato a nessun sopravvissuto o bunker.");
            return "errors/notAuthorized";
        }

        Bunker bunker = me.getBunker();

        model.addAttribute("me", me);
        model.addAttribute("bunker", bunker);
        model.addAttribute("altri", sopravvissutoService.findByBunker(bunker));
        model.addAttribute("missioni", missioneService.findByBunker(bunker));
        model.addAttribute("stanze", stanzaService.findByBunker(bunker));

        return "sopravvissuti/profilo";
    }

    // ✅ Lista di tutti i sopravvissuti (opzionale, per admin)
    @GetMapping
    public String listSopravvissuti(Model model) {
        model.addAttribute("sopravvissuti", sopravvissutoService.findAll());
        return "sopravvissuti/list";
    }

    // ✅ Dettaglio di un sopravvissuto (visibile solo ad admin o staff)
    @GetMapping("/{id}")
    public String showSopravvissuto(@PathVariable("id") Long id, Model model) {
        Optional<Sopravvissuto> result = sopravvissutoService.findById(id);
        if (result.isPresent()) {
            model.addAttribute("sopravvissuto", result.get());
            return "sopravvissuti/show";
        } else {
            model.addAttribute("notFound", true);
            return "errors/notFound";
        }
    }

    // ✅ Form per creare un sopravvissuto (solo admin)
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("sopravvissuto", new Sopravvissuto());
        model.addAttribute("bunkerList", bunkerService.findAll());
        return "sopravvissuti/form";
    }

    // ✅ Salva nuovo sopravvissuto (solo admin)
    @PostMapping("/create")
    public String createSopravvissuto(@Valid @ModelAttribute("sopravvissuto") Sopravvissuto sopravvissuto,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bunkerList", bunkerService.findAll());
            return "sopravvissuti/form";
        }

        sopravvissutoService.save(sopravvissuto);
        return "redirect:/sopravvissuti";
    }
}

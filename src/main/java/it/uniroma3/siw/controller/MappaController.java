package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.service.BunkerService;

@Controller
public class MappaController {

    @Autowired
    private BunkerService bunkerService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/bunker-mappa")
    public String mostraMappa(Model model) {
        List<Bunker> bunkerList = bunkerService.findAll();
        try {
            String json = objectMapper.writeValueAsString(bunkerList);
            model.addAttribute("bunkerListJson", json);
        } catch (JsonProcessingException e) {
            model.addAttribute("bunkerListJson", "[]");
        }
        return "bunker/mappa";  // o "mappa" se si trova in templates/mappa.html
    }
}

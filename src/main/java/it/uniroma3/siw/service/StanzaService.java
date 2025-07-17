package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.repository.StanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StanzaService {

    @Autowired
    private StanzaRepository stanzaRepository;

    public List<Stanza> findAll() {
        return (List<Stanza>) stanzaRepository.findAll();
    }

    public Optional<Stanza> findById(Long id) {
        return stanzaRepository.findById(id);
    }

    public Stanza save(Stanza stanza) {
        return stanzaRepository.save(stanza);
    }

    public void deleteById(Long id) {
        stanzaRepository.deleteById(id);
    }

    public List<Stanza> findByTipo(String tipo) {
        return stanzaRepository.findByTipo(tipo);
    }

    public List<Stanza> findByStato(String stato) {
        return stanzaRepository.findByStato(stato);
    }

    public List<Stanza> findByBunker(Bunker bunker) {
        return stanzaRepository.findByBunker(bunker);
    }
}

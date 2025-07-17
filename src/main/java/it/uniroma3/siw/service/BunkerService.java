package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.repository.BunkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BunkerService {

    @Autowired
    private BunkerRepository bunkerRepository;

    public List<Bunker> findAll() {
        return (List<Bunker>) bunkerRepository.findAll();
    }
    
    

    public Optional<Bunker> findById(Long id) {
        return bunkerRepository.findById(id);
    }

    public Bunker save(Bunker bunker) {
        return bunkerRepository.save(bunker);
    }

    public void deleteById(Long id) {
        bunkerRepository.deleteById(id);
    }

    public List<Bunker> findByNome(String nome) {
        return bunkerRepository.findByNome(nome);
    }

    public List<Bunker> findByStato(String stato) {
        return bunkerRepository.findByStato(stato);
    }

    public List<Bunker> findByLivelloSicurezza(String livello) {
        return bunkerRepository.findByLivelloSicurezza(livello);
    }
}


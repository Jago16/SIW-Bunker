package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Missione;
import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.repository.MissioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MissioneService {

    @Autowired
    private MissioneRepository missioneRepository;

    public List<Missione> findAll() {
        return (List<Missione>) missioneRepository.findAll();
    }

    public Optional<Missione> findById(Long id) {
        return missioneRepository.findById(id);
    }

    public Missione save(Missione missione) {
        return missioneRepository.save(missione);
    }

    public void deleteById(Long id) {
        missioneRepository.deleteById(id);
    }

    public List<Missione> findByTipo(String tipo) {
        return missioneRepository.findByTipo(tipo);
    }

    public List<Missione> findByStato(String stato) {
        return missioneRepository.findByStato(stato);
    }

    public List<Missione> findByDifficolta(String difficolta) {
        return missioneRepository.findByDifficolta(difficolta);
    }

    public List<Missione> findByBunker(Bunker bunker) {
        return missioneRepository.findByBunker(bunker);
    }
}

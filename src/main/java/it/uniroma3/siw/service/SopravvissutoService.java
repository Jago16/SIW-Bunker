package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Sopravvissuto;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.repository.SopravvissutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SopravvissutoService {

    @Autowired
    private SopravvissutoRepository sopravvissutoRepository;

    public List<Sopravvissuto> findAll() {
        return (List<Sopravvissuto>) sopravvissutoRepository.findAll();
    }

    public Optional<Sopravvissuto> findById(Long id) {
        return sopravvissutoRepository.findById(id);
    }

    public Optional<Sopravvissuto> getByUser(User user) {
        return this.sopravvissutoRepository.findByUser(user);
    }

    
    public Sopravvissuto save(Sopravvissuto sopravvissuto) {
        return sopravvissutoRepository.save(sopravvissuto);
    }

    public void deleteById(Long id) {
        sopravvissutoRepository.deleteById(id);
    }

    public List<Sopravvissuto> findByRuolo(String ruolo) {
        return sopravvissutoRepository.findByRuolo(ruolo);
    }

    public List<Sopravvissuto> findByEtaLessThan(int eta) {
        return sopravvissutoRepository.findByEtaLessThan(eta);
    }

    public List<Sopravvissuto> findBySaluteMentaleLessThan(int valore) {
        return sopravvissutoRepository.findBySaluteMentaleLessThan(valore);
    }

    public List<Sopravvissuto> findByBunker(Bunker bunker) {
        return sopravvissutoRepository.findByBunker(bunker);
    }
}

package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.model.Bunker;
import it.uniroma3.siw.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findAll() {
        return (List<Evento>) eventoRepository.findAll();
    }

    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> findByTipo(String tipo) {
        return eventoRepository.findByTipo(tipo);
    }

    public List<Evento> findByData(LocalDate data) {
        return eventoRepository.findByData(data);
    }

    public List<Evento> findByBunker(Bunker bunker) {
        return eventoRepository.findByBunker(bunker);
    }
}

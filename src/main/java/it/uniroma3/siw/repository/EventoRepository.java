package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.model.Bunker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {

    List<Evento> findByTipo(String tipo);
    //esempio tutti gli eventi di tipo blackout, malattia, incendio ecc

    List<Evento> findByData(LocalDate data);
    //eventi in una data specifica

    List<Evento> findByBunker(Bunker bunker);
    //cronologia eventi di un bunker
}


package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Missione;
import it.uniroma3.siw.model.Bunker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissioneRepository extends CrudRepository<Missione, Long> {

    List<Missione> findByTipo(String tipo);

    List<Missione> findByStato(String stato);

    List<Missione> findByDifficolta(String difficolta);

    List<Missione> findByBunker(Bunker bunker);
}

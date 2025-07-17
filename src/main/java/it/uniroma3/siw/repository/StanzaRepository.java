package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.model.Bunker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StanzaRepository extends CrudRepository<Stanza, Long> {

	
    List<Stanza> findByTipo(String tipo);
    //tutte le stanze di tipo magazzino per esempio

    List<Stanza> findByStato(String stato);
    //esempio quelle operative

    List<Stanza> findByBunker(Bunker bunker);
    //tutte le stanze di un determinato bunker
}

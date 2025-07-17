package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Bunker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Estende CrudRepository<Bunker, Long> → ti dà già i metodi base come:
findAll()
findById(Long id)
save(Bunker b)
deleteById(Long id)
 */


@Repository
public interface BunkerRepository extends CrudRepository<Bunker, Long> {

    List<Bunker> findByNome(String nome);
    List<Bunker> findByStato(String stato);
    List<Bunker> findByLivelloSicurezza(String livelloSicurezza);
}

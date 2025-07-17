package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Sopravvissuto;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.Bunker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SopravvissutoRepository extends CrudRepository<Sopravvissuto, Long> {

	
    List<Sopravvissuto> findByRuolo(String ruolo);
    //restituisce tutti i medici o i ruoli

    List<Sopravvissuto> findByEtaLessThan(int eta);
    //restituisce tutti quelli sotto un tot di eta

    List<Sopravvissuto> findBySaluteMentaleLessThan(int valore);
    //ti restituisce chi ha sotto un tot di salute mentale

    List<Sopravvissuto> findByBunker(Bunker bunker);
    //utile per visualizzare chi sta in un determinato bunker
    Optional<Sopravvissuto> findByUser(User user);

}

package it.uniroma3.siw.model;

import jakarta.persistence.*;

@Entity
public class Missione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tipo;       // es. recupero cibo, difesa...
    private String difficolta; // es. facile, media, difficile
    private String stato;      // es. completata, in corso...

    @ManyToOne
    private Bunker bunker;

    public Missione() {}

    public Missione(String tipo, String difficolta, String stato, Bunker bunker) {
        this.tipo = tipo;
        this.difficolta = difficolta;
        this.stato = stato;
        this.bunker = bunker;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Bunker getBunker() {
        return bunker;
    }

    public void setBunker(Bunker bunker) {
        this.bunker = bunker;
    }
}

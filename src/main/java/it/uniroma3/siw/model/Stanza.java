package it.uniroma3.siw.model;


import jakarta.persistence.*;

@Entity
public class Stanza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tipo; // es. dormitorio, magazzino...

    private String stato; // es. funzionante, danneggiata

    @ManyToOne
    private Bunker bunker;

    public Stanza() {}

    public Stanza(String tipo, String stato, Bunker bunker) {
        this.tipo = tipo;
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

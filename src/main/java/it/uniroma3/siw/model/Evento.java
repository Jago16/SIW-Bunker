package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tipo;

    private String descrizione;

    private LocalDate data;

    @ManyToOne
    private Bunker bunker;

    public Evento() {}

    public Evento(String tipo, String descrizione, LocalDate data, Bunker bunker) {
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.data = data;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Bunker getBunker() {
        return bunker;
    }

    public void setBunker(Bunker bunker) {
        this.bunker = bunker;
    }
}

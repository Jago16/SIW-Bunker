package it.uniroma3.siw.model;

import jakarta.persistence.*;

@Entity
public class Sopravvissuto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String ruolo;

    private int eta;

    @Column(name = "salute_mentale")
    private int saluteMentale;

    @Column(name = "salute_fisica")
    private int saluteFisica;

    @ManyToOne
    private Bunker bunker;
    
    @OneToOne
    private User user;


    // Costruttori
    public Sopravvissuto() {}

    public Sopravvissuto(String nome, String ruolo, int eta, int saluteMentale, int saluteFisica, Bunker bunker, User user) {
        this.nome = nome;
        this.ruolo = ruolo;
        this.eta = eta;
        this.saluteMentale = saluteMentale;
        this.saluteFisica = saluteFisica;
        this.bunker = bunker;
        this.user = user;
    }

    
    // Getters & Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getSaluteMentale() {
        return saluteMentale;
    }

    public void setSaluteMentale(int saluteMentale) {
        this.saluteMentale = saluteMentale;
    }

    public int getSaluteFisica() {
        return saluteFisica;
    }

    public void setSaluteFisica(int saluteFisica) {
        this.saluteFisica = saluteFisica;
    }

    public Bunker getBunker() {
        return bunker;
    }

    public void setBunker(Bunker bunker) {
        this.bunker = bunker;
    }
}

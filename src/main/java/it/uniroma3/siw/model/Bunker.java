package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Bunker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Double latitudine;
    private Double longitudine;

    
    
    @Column(name = "livello_sicurezza")
    private String livelloSicurezza;

    @Column(name = "capacita_massima")
    private int capacitaMassima;

    private String stato; // es. operativo, in emergenza...

    @OneToMany(mappedBy = "bunker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sopravvissuto> sopravvissuti = new ArrayList<>();

    @OneToMany(mappedBy = "bunker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stanza> stanze = new ArrayList<>();

    @OneToMany(mappedBy = "bunker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventi = new ArrayList<>();

    @OneToMany(mappedBy = "bunker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Missione> missioni = new ArrayList<>();

    // Costruttori
    public Bunker() {}

    public Bunker(String nome, String livelloSicurezza, int capacitaMassima, String stato) {
        this.nome = nome;
        this.livelloSicurezza = livelloSicurezza;
        this.capacitaMassima = capacitaMassima;
        this.stato = stato;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }
    
    

    public Double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(Double latitudine) {
		this.latitudine = latitudine;
	}

	public Double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(Double longitudine) {
		this.longitudine = longitudine;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLivelloSicurezza() {
        return livelloSicurezza;
    }

    public void setLivelloSicurezza(String livelloSicurezza) {
        this.livelloSicurezza = livelloSicurezza;
    }

    public int getCapacitaMassima() {
        return capacitaMassima;
    }

    public void setCapacitaMassima(int capacitaMassima) {
        this.capacitaMassima = capacitaMassima;
    }
}
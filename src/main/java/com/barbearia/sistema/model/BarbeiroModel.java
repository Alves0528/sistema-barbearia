package com.barbearia.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BarbeiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeBarbeiro;
    private String telefoneBarbeiro;

    public BarbeiroModel(){
    }

    public BarbeiroModel(String nome, String telefone){
        this.nomeBarbeiro = nome;
        this.telefoneBarbeiro = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBarbeiro() {
        return nomeBarbeiro;
    }

    public void setNomeBarbeiro(String nome) {
        this.nomeBarbeiro = nome;
    }

    public String getTelefoneBarbeiro() {
        return telefoneBarbeiro;
    }

    public void setTelefoneBarbeiro(String telefone) {
        this.telefoneBarbeiro = telefone;
    }
}

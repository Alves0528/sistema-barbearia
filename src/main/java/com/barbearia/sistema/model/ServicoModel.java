package com.barbearia.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class ServicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String descrição;
    private BigDecimal precoServico;

    public ServicoModel(){
    }

    public ServicoModel(String descrição, BigDecimal precoServico) {
        this.descrição = descrição;
        this.precoServico = precoServico;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getDescrição() {

        return descrição;
    }

    public void setDescrição(String descrição) {

        this.descrição = descrição;
    }

    public BigDecimal getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(BigDecimal preco) {
        this.precoServico = preco;
    }
}

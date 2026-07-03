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

    private String nome;
    private String descricao;
    private BigDecimal precoServico;

    public ServicoModel(){
    }

    public ServicoModel(String nome, String descricao, BigDecimal precoServico) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoServico = precoServico;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public BigDecimal getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(BigDecimal preco) {
        this.precoServico = preco;
    }
}

package com.barbearia.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class BarbeiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeBarbeiro;
    private String telefoneBarbeiro;
    private Boolean ativo = true;
    private BigDecimal comissaoServico;
    private BigDecimal comissaoProduto;


    public BarbeiroModel(){
    }

    public BarbeiroModel(String nomeBarbeiro, String telefoneBarbeiro, BigDecimal comissaoServico, BigDecimal comissaoProduto){
        this.nomeBarbeiro = nomeBarbeiro;
        this.telefoneBarbeiro = telefoneBarbeiro;
        this.comissaoServico = comissaoServico;
        this.comissaoProduto = comissaoProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBarbeiro() {
        return nomeBarbeiro;
    }

    public void setNomeBarbeiro(String nomeBarbeiro) {
        this.nomeBarbeiro = nomeBarbeiro;
    }

    public String getTelefoneBarbeiro() {
        return telefoneBarbeiro;
    }

    public void setTelefoneBarbeiro(String telefoneBarbeiro) {
        this.telefoneBarbeiro = telefoneBarbeiro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getComissaoServico() {
        return comissaoServico;
    }

    public void setComissaoServico(BigDecimal comissaoServico) {
        this.comissaoServico = comissaoServico;
    }

    public BigDecimal getComissaoProduto() {
        return comissaoProduto;
    }

    public void setComissaoProduto(BigDecimal comissaoProduto) {
        this.comissaoProduto = comissaoProduto;
    }
}

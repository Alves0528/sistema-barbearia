package com.barbearia.sistema.model;


import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AtendimentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "babeiro_id")
    private BarbeiroModel barbeiro;

    @ManyToMany
    @JoinTable(
            name = "atendimento_produto",
            joinColumns = @JoinColumn(name = "atendimento_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoModel> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "atendimento_servico",
            joinColumns = @JoinColumn(name = "atendimento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<ServicoModel> servicos = new ArrayList<>();

    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaTermino;
    private BigDecimal valorTotal;
    private BigDecimal valorComissaoBarbeiro = BigDecimal.ZERO;

    public AtendimentoModel(){
        this.data = LocalDate.now();
        this.valorTotal = BigDecimal.ZERO;
    }

    public AtendimentoModel(LocalTime horaInicio, LocalTime horaTermino, BarbeiroModel barbeiro) {
        this.data = LocalDate.now();
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.valorTotal = BigDecimal.ZERO;
        this.barbeiro = barbeiro;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BarbeiroModel getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(BarbeiroModel barbeiro) {
        this.barbeiro = barbeiro;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    public List<ServicoModel> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoModel> servicos) {
        this.servicos = servicos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(LocalTime horaTermino) {
        this.horaTermino = horaTermino;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorComissaoBarbeiro() {
        return valorComissaoBarbeiro;
    }

    public void setValorComissaoBarbeiro(BigDecimal valorComissaoBarbeiro) {
        this.valorComissaoBarbeiro = valorComissaoBarbeiro;
    }
}

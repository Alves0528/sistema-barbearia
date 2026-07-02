package com.barbearia.sistema.model;


import jakarta.persistence.*;

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
    private LocalTime hora;
    private BigDecimal valorTotal;

    public AtendimentoModel(){
        this.data = LocalDate.now();
        this.valorTotal = BigDecimal.ZERO;
    }

    public AtendimentoModel(LocalTime hora, BarbeiroModel barbeiro) {
        this.data = LocalDate.now();
        this.hora = hora;
        this.valorTotal = BigDecimal.ZERO;
        this.barbeiro = barbeiro;

    }

    public void adicionarServico(ServicoModel servico){
        this.servicos.add(servico);
        this.valorTotal = this.valorTotal.add(servico.getPrecoServico());
    }

    public void adicionarProduto(ProdutoModel produto){
        this.produtos.add(produto);
        this.valorTotal = this.valorTotal.add(produto.getPrecoProduto());
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}

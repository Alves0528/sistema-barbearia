package com.barbearia.sistema.service;

import com.barbearia.sistema.model.AtendimentoModel;
import com.barbearia.sistema.model.ProdutoModel;
import com.barbearia.sistema.model.ServicoModel;
import com.barbearia.sistema.repository.AtendimentoRepository;
import com.barbearia.sistema.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    private final ServicoService servicoService;

    public AtendimentoService(AtendimentoRepository atendimentoRepository, ProdutoRepository produtoRepository, ProdutoService produtoService, ServicoService servicoService) {
        this.atendimentoRepository = atendimentoRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
        this.servicoService = servicoService;
    }

    public List<AtendimentoModel> listarAtendimentos(){
        return atendimentoRepository.findAll();
    }

    private BigDecimal calcularValorTotalAtendimento(AtendimentoModel atendimento){

        BigDecimal valorTotal = BigDecimal.ZERO;

        if(atendimento.getServicos() != null){
            for (ServicoModel servico : atendimento.getServicos()){
                ServicoModel servicoDisponiveis = servicoService.listarServicos().stream().filter( s -> s.getId()
                        .equals(servico.getId())).findFirst().orElse(null);
                if(servicoDisponiveis != null){
                    valorTotal = valorTotal.add(servicoDisponiveis.getPrecoServico());
                }
            }
        }

        if(atendimento.getProdutos() != null){
            for (ProdutoModel produto : atendimento.getProdutos()){
                ProdutoModel produtoDisponiveis = produtoService.listarProdutos().stream().filter(p -> p.getId()
                        .equals(produto.getId())).findFirst().orElse(null);
                if (produtoDisponiveis !=null){
                    valorTotal = valorTotal.add(produtoDisponiveis.getPrecoProduto());
                }
            }
        }

        return valorTotal;
    }

    public AtendimentoModel salvarAtendimento(AtendimentoModel atendimento){

        BigDecimal total = calcularValorTotalAtendimento(atendimento);
        atendimento.setValorTotal(total);

        if(atendimento.getProdutos() != null && !atendimento.getProdutos().isEmpty()){

            for (ProdutoModel produto : atendimento.getProdutos()){
                produtoService.diminuirEstoque(produto.getId(),1);
            }
        }

        return atendimentoRepository.save(atendimento);
    }

}

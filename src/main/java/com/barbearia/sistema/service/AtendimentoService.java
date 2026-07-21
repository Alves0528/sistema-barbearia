package com.barbearia.sistema.service;

import com.barbearia.sistema.model.AtendimentoModel;
import com.barbearia.sistema.model.BarbeiroModel;
import com.barbearia.sistema.model.ProdutoModel;
import com.barbearia.sistema.model.ServicoModel;
import com.barbearia.sistema.repository.AtendimentoRepository;
import com.barbearia.sistema.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final ProdutoService produtoService;
    private final ServicoService servicoService;
    private final BarbeiroService barbeiroService;

    public AtendimentoService(AtendimentoRepository atendimentoRepository, ProdutoService produtoService, ServicoService servicoService, BarbeiroService barbeiroService) {
        this.atendimentoRepository = atendimentoRepository;
        this.produtoService = produtoService;
        this.servicoService = servicoService;
        this.barbeiroService = barbeiroService;
    }

    public List<AtendimentoModel> listarAtendimentos(){
        return atendimentoRepository.findAll();
    }

    public AtendimentoModel salvarAtendimento(AtendimentoModel atendimento){

        atendimento.setValorTotal(calcularValorTotalAtendimento(atendimento));
        atendimento.setValorComissaoBarbeiro(calcularComissaoBarbeiro(atendimento));

        if(atendimento.getProdutos() != null && !atendimento.getProdutos().isEmpty()){
            for (ProdutoModel produto : atendimento.getProdutos()){
                produtoService.diminuirEstoque(produto.getId(),1);
            }
        }

        return atendimentoRepository.save(atendimento);
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

    private BigDecimal calcularComissaoBarbeiro(AtendimentoModel atendimento){
        BigDecimal totalComissao = BigDecimal.ZERO;
        BarbeiroModel barbeiro = barbeiroService.buscarBarbeiro(atendimento.getBarbeiro().getId());

        BigDecimal cem = new BigDecimal("100");

        if (atendimento.getServicos() != null) {
            for (ServicoModel servico : atendimento.getServicos()) {
                ServicoModel servicoEncontrado = servicoService.buscarServico(servico.getId());
                if (servicoEncontrado != null) {

                    BigDecimal comissao = servicoEncontrado.getPrecoServico()
                            .multiply(barbeiro.getComissaoServico())
                            .divide(cem, 2, RoundingMode.HALF_UP);

                    totalComissao = totalComissao.add(comissao);
                }
            }
        }

        if(atendimento.getProdutos() != null){
            for (ProdutoModel produto : atendimento.getProdutos()){
                ProdutoModel produtoEncontrado = produtoService.buscarProduto(produto.getId());
                if (produtoEncontrado != null){
                    BigDecimal comissao = produtoEncontrado.getPrecoProduto()
                            .multiply(barbeiro.getComissaoProduto())
                            .divide(cem, 2, RoundingMode.HALF_UP);

                    totalComissao = totalComissao.add(comissao);
                }
            }
        }

        return totalComissao;
    }

}

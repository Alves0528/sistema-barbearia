package com.barbearia.sistema.service;

import com.barbearia.sistema.model.AtendimentoModel;
import com.barbearia.sistema.model.ProdutoModel;
import com.barbearia.sistema.repository.AtendimentoRepository;
import com.barbearia.sistema.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    public AtendimentoService(AtendimentoRepository atendimentoRepository, ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.atendimentoRepository = atendimentoRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    public List<AtendimentoModel> listarAtendimentos(){
        return atendimentoRepository.findAll();
    }

    public AtendimentoModel salvarAtendimento(AtendimentoModel atendimento){

        if(atendimento.getProdutos() != null && !atendimento.getProdutos().isEmpty()){

            for (ProdutoModel produto : atendimento.getProdutos()){
                produtoService.diminuirEstoque(produto.getId(),1);
            }
        }

        return atendimentoRepository.save(atendimento);
    }
}

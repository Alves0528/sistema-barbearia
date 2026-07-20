package com.barbearia.sistema.service;

import com.barbearia.sistema.model.ProdutoModel;
import com.barbearia.sistema.repository.BarbeiroRepository;
import com.barbearia.sistema.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> listarProdutos(){
        return produtoRepository.findByAtivoTrue();
    }

    public ProdutoModel buscarProduto(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public ProdutoModel salvarProduto(ProdutoModel produto){
        return produtoRepository.save(produto);
    }

    public void excluirProduto(Long id){
        ProdutoModel produto = produtoRepository.findById(id).orElse(null);
        if (produto != null){
            produto.setAtivo(false);
            produtoRepository.save(produto);
        }
    }

    public ProdutoModel alterarProduto(Long id, ProdutoModel produtoNovo){
        ProdutoModel produtoAntigo = produtoRepository.findById(id).orElse(null);

        if(produtoAntigo == null){
            throw new RuntimeException("Produto não está cadastrado");
        }

        produtoAntigo.setNomeProduto(produtoNovo.getNomeProduto());
        produtoAntigo.setPrecoProduto(produtoNovo.getPrecoProduto());
        produtoAntigo.setQuantidadeEstoque(produtoNovo.getQuantidadeEstoque());

        return produtoRepository.save(produtoAntigo);
    }

    public void diminuirEstoque(Long id, Integer quantidadeVendida){
        ProdutoModel produto = produtoRepository.findById(id).orElse(null);

        if(produto == null){
            throw new RuntimeException("Produto não está cadastrado");
        }

        if(produto.getQuantidadeEstoque() < quantidadeVendida){
            throw new RuntimeException("Quantidade insuficiente no estoque");
        }

        int novoEstoque = produto.getQuantidadeEstoque() - quantidadeVendida;
        produto.setQuantidadeEstoque(novoEstoque);

        produtoRepository.save(produto);

    }

}

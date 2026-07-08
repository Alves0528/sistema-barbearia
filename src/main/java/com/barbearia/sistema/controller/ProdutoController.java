package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.ProdutoModel;
import com.barbearia.sistema.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public String lista(Model model){
        model.addAttribute("listaProdutos", produtoService.listarProdutos());
        return "produtos";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model){
        model.addAttribute("produto", new ProdutoModel());
        return "produto_novo";
    }

    @PostMapping
    public String salvarProduto(ProdutoModel produto){
        produtoService.salvarProduto(produto);
        return "redirect:/produtos";
    }

    @PostMapping("/editar")
    public String editarProduto(ProdutoModel produto){
        produtoService.alterarProduto(produto.getId(), produto);
        return "redirect:/produtos";
    }
}

package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.ProdutoModel;
import com.barbearia.sistema.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String listaProdutos(Model model) {
        model.addAttribute("listaProdutos", produtoService.listarProdutos());
        return "produto/produtos";
    }

    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new ProdutoModel());
        return "produto/novo_produto";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        ProdutoModel produto = produtoService.buscarProduto(id);
        model.addAttribute("produto", produto);
        return "produto/editar_produto";
    }

    @PostMapping("/salvarNovo")
    public String salvarProduto(ProdutoModel produto) {
        produtoService.salvarProduto(produto);
        return "redirect:/produtos";
    }

    @PostMapping("/salvarEditado")
    public String editarProduto(ProdutoModel produto) {
        produtoService.alterarProduto(produto.getId(), produto);
        return "redirect:/produtos";
    }

    @PostMapping("/excluir")
    public String excluirProduto(ProdutoModel produto) {
        produtoService.excluirProduto(produto.getId());
        return "redirect:/produtos";
    }
}
package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.AtendimentoModel;
import com.barbearia.sistema.service.AtendimentoService;
import com.barbearia.sistema.service.BarbeiroService;
import com.barbearia.sistema.service.ProdutoService;
import com.barbearia.sistema.service.ServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atendimentos")
public class AtendimentoController {
    private final AtendimentoService atendimentoService;
    private final BarbeiroService barbeiroService;
    private final ServicoService servicoService;
    private final ProdutoService produtoService;

    public AtendimentoController(AtendimentoService atendimentoService, BarbeiroService barbeiroService, ServicoService servicoService,ProdutoService produtoService) {
        this.atendimentoService = atendimentoService;
        this.barbeiroService = barbeiroService;
        this.servicoService = servicoService;
        this.produtoService = produtoService;
    }

    @GetMapping
    public String lista(Model model){
        model.addAttribute("listaAtendimentos", atendimentoService.listarAtendimentos());
        return "atendimentos";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model){
        model.addAttribute("atendimento", new AtendimentoModel());

        model.addAttribute("listaBarbeiros", barbeiroService.listarBarbeiros());
        model.addAttribute("listaServicos", servicoService.listarServicos());
        model.addAttribute("listaProdutos", produtoService.listarProdutos());

        return "atendimento_novo";
    }

    @PostMapping
    public String salvarAtenidmento(AtendimentoModel atendimento){
        atendimentoService.salvarAtendimento(atendimento);
        return "redirect:/atendimentos";
    }
}
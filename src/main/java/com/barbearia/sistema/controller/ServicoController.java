package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.BarbeiroModel;
import com.barbearia.sistema.model.ServicoModel;
import com.barbearia.sistema.service.BarbeiroService;
import com.barbearia.sistema.service.ServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicos")
public class ServicoController {
    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public String lista(Model model){
        model.addAttribute("listaServicos", servicoService.listarServicos());
        return "servicos";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model){
        model.addAttribute("servico", new ServicoModel());
        return "servico_novo";
    }

    @PostMapping
    public String salvarBarbeiro(ServicoModel servico){
        servicoService.salvarServico(servico);
        return "redirect:/servicos";
    }

    @PostMapping("/editar")
    public String editarBarbeiro(ServicoModel servico){
        servicoService.alterarServico(servico.getId(), servico);
        return "redirect:/servicos";
    }

}

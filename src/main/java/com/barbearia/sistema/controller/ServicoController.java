package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.ServicoModel;
import com.barbearia.sistema.service.ServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String listaServicos(Model model){
        model.addAttribute("listaServicos", servicoService.listarServicos());
        return "servico/servicos";
    }

    @GetMapping("/novo")
    public String novoServico(Model model){
        model.addAttribute("servico", new ServicoModel());
        return "servico/novo_servico";
    }

    @GetMapping("/editar/{id}")
    public String editarBarbeiro(@PathVariable Long id, Model model){
        ServicoModel servico = servicoService.buscarServico(id);
        model.addAttribute("servico", servico);
        return "servico/editar_servico";
    }

    @PostMapping("/salvarNovo")
    public String salvarServico(ServicoModel servico){
        servicoService.salvarServico(servico);
        return "redirect:/servicos";
    }

    @PostMapping("/salvarEditado")
    public String editarServico(ServicoModel servico){
        servicoService.alterarServico(servico.getId(), servico);
        return "redirect:/servicos";
    }

    @PostMapping("/excluir")
    public String excluirServico(ServicoModel servico){
        servicoService.excluirServico(servico.getId());
        return "redirect:/servicos";
    }
}
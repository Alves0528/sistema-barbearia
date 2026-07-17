package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.BarbeiroModel;
import org.springframework.ui.Model;
import com.barbearia.sistema.service.BarbeiroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/barbeiros")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;
    public BarbeiroController(BarbeiroService barbeiroService) {
        this.barbeiroService = barbeiroService;
    }

    @GetMapping
    public String listaBarbeiros(Model model){
        model.addAttribute("listaBarbeiros", barbeiroService.listarBarbeiros());
        return "barbeiro/barbeiros";
    }

    @GetMapping("/novo")
    public String novoBarbeiro(Model model){
        model.addAttribute("barbeiro", new BarbeiroModel());
        return "barbeiro/novo_barbeiro";
    }

    @GetMapping("/editar/{id}")
    public String editarBarbeiro(@PathVariable Long id, Model model){
       BarbeiroModel barbeiro = barbeiroService.buscarBarbeiro(id);
       model.addAttribute("barbeiro", barbeiro);
       return "barbeiro/editar_barbeiro";
    }

    @PostMapping("/salvarNovo")
    public String salvarBarbeiro(BarbeiroModel barbeiro){
        barbeiroService.salvarBarbeiro(barbeiro);
        return "redirect:/barbeiros";
    }

    @PostMapping("/salvarEditado")
    public String salvarBarbeiroAlterado(BarbeiroModel barbeiro){
        barbeiroService.alterarBarbeiro(barbeiro.getId(), barbeiro);
        return "redirect:/barbeiros";
    }

    @PostMapping("/excluir")
    public String excluirBarbeiro(BarbeiroModel barbeiro){
        barbeiroService.excluirBarbeiro(barbeiro.getId());
        return "redirect:/barbeiros";
    }
}


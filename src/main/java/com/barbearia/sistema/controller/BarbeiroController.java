package com.barbearia.sistema.controller;

import com.barbearia.sistema.model.BarbeiroModel;
import org.springframework.ui.Model;
import com.barbearia.sistema.service.BarbeiroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String lista(Model model){
        model.addAttribute("listaBarbeiros", barbeiroService.listarBarbeiros());
        return "barbeiros";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model){
        model.addAttribute("barbeiro", new BarbeiroModel());
        return "barbeiro_novo";
    }

    @PostMapping
    public String salvarBarbeiro(BarbeiroModel barbeiro){
        barbeiroService.salvarBarbeiro(barbeiro);
        return "redirect:/barbeiros";
    }

    @PostMapping("/editar")
    public String editarBarbeiro(BarbeiroModel barbeiro){
        barbeiroService.alterarBarbeiro(barbeiro.getId(), barbeiro);
        return "redirect:/barbeiros";
    }
}

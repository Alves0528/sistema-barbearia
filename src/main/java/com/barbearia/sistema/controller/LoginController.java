package com.barbearia.sistema.controller;

import com.barbearia.sistema.exception.LoginInvalidoException;
import com.barbearia.sistema.model.UsuarioModel;
import com.barbearia.sistema.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.LoginException;

@Controller
@RequestMapping("/admin")
public class LoginController {

    private final UsuarioService usuarioService;
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String abrirLogin(Model model) {
        model.addAttribute("usuario", new UsuarioModel());
        return "administracao/login";
    }

    @PostMapping("/validarLogin")
    public String AcessarSistema(UsuarioModel usuario, Model model) {
        try {
            usuarioService.verificarAcesso(usuario.getAcesso(), usuario.getSenha());
            return "administracao/indexAdmin";

        } catch (LoginInvalidoException e) {
            model.addAttribute("erroLogin", e.getMessage());
            return "administracao/login";
        }
    }

    @GetMapping("/dashboard")
    public String Dashboard() {
        return "administracao/dashboard";
    }
}

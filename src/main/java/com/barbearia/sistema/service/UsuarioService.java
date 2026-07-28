package com.barbearia.sistema.service;

import com.barbearia.sistema.exception.LoginInvalidoException;
import com.barbearia.sistema.model.UsuarioModel;
import com.barbearia.sistema.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel verificarAcesso(String usuarioValidar, String senhaValidar) {
        UsuarioModel usuario = usuarioRepository.findByAcesso(usuarioValidar)
                .orElseThrow(() -> new LoginInvalidoException("Usuário ou senha incorretos!"));

        if (!passwordEncoder.matches(senhaValidar, usuario.getSenha())) {
            throw new LoginInvalidoException("Usuário ou senha incorretos!");
        }

        return usuario;
    }
}

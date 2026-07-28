package com.barbearia.sistema.config;

import com.barbearia.sistema.model.UsuarioModel;
import com.barbearia.sistema.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String[] args) throws Exception {

        if (usuarioRepository.count() == 0) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            UsuarioModel admin1 = new UsuarioModel();
            admin1.setAcesso("Guilherme");
            admin1.setSenha(passwordEncoder.encode("senha123"));
            usuarioRepository.save(admin1);

            UsuarioModel admin2 = new UsuarioModel();
            admin2.setAcesso("Marcos");
            admin2.setSenha(passwordEncoder.encode("barba2026"));
            usuarioRepository.save(admin2);

            System.out.println("✅ Administradores criados com sucesso no banco H2!");
        } else {
            System.out.println("ℹ️ O banco H2 já possui administradores. Pulando a criação automática.");
        }
    }

}

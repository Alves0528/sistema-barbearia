package com.barbearia.sistema.service;

import com.barbearia.sistema.model.ServicoModel;
import com.barbearia.sistema.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<ServicoModel> listarServicos(){
        return servicoRepository.findAll();
    }

    public ServicoModel salvarServico(ServicoModel servico){
        return servicoRepository.save(servico);
    }

    public void deletarServico(Long id){
        servicoRepository.deleteById(id);
    }

    public ServicoModel alterarServico(Long id, ServicoModel servicoNovo){
        ServicoModel servicoAntigo = servicoRepository.findById(id).orElse(null);

        if(servicoAntigo == null){
            throw new RuntimeException("Servico não está cadastrado");
        }

        servicoAntigo.setNome(servicoNovo.getNome());
        servicoAntigo.setDescricao(servicoNovo.getDescricao());
        servicoAntigo.setPrecoServico(servicoNovo.getPrecoServico());

        return servicoRepository.save(servicoAntigo);
    }
}

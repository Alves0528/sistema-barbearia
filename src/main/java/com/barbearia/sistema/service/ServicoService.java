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
        return servicoRepository.findByAtivoTrue();
    }

    public ServicoModel buscarServico(Long id){
        return servicoRepository.findById(id).orElse(null);
    }

    public ServicoModel salvarServico(ServicoModel servico){
        return servicoRepository.save(servico);
    }

    public void excluirServico(Long id){
        ServicoModel servico = servicoRepository.findById(id).orElse(null);
        if ( servico != null){
            servico.setAtivo(false);
            servicoRepository.save(servico);
        }
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
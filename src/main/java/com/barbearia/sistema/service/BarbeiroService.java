package com.barbearia.sistema.service;

import com.barbearia.sistema.model.BarbeiroModel;
import com.barbearia.sistema.repository.BarbeiroRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    public BarbeiroService(BarbeiroRepository barbeiroRepository) {
        this.barbeiroRepository = barbeiroRepository;
    }

    public List<BarbeiroModel> listarBarbeiros(){
        return barbeiroRepository.findByAtivoTrue();
    }

    public BarbeiroModel buscarBarbeiro(Long id){
       return barbeiroRepository.findById(id).orElse(null);
    }

    public BarbeiroModel salvarBarbeiro(BarbeiroModel barbeiro){

        return barbeiroRepository.save(barbeiro);
    }

    public void excluirBarbeiro(Long id){
        BarbeiroModel barbeiro = barbeiroRepository.findById(id).orElse(null);
        if (barbeiro != null){
            barbeiro.setAtivo(false);
            barbeiroRepository.save(barbeiro);
        }
    }

    public BarbeiroModel alterarBarbeiro(Long id, BarbeiroModel barbeiroNovo){
        BarbeiroModel barbeiroAntigo = barbeiroRepository.findById(id).orElse(null);

        if(barbeiroAntigo == null){
            throw new RuntimeException("Barbeiro não está cadastrado");
        }

        barbeiroAntigo.setNomeBarbeiro(barbeiroNovo.getNomeBarbeiro());
        barbeiroAntigo.setTelefoneBarbeiro(barbeiroNovo.getTelefoneBarbeiro());
        barbeiroAntigo.setComissaoServico(barbeiroNovo.getComissaoServico());
        barbeiroAntigo.setComissaoProduto(barbeiroNovo.getComissaoProduto());

        return barbeiroRepository.save(barbeiroAntigo);
    }

}
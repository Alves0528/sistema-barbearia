package com.barbearia.sistema.service;

import com.barbearia.sistema.model.BarbeiroModel;
import com.barbearia.sistema.repository.BarbeiroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public BarbeiroService(BarbeiroRepository barbeiroRepository) {
        this.barbeiroRepository = barbeiroRepository;
    }

    public List<BarbeiroModel> listarBarbeiros(){
        return barbeiroRepository.findAll();
    }

    public BarbeiroModel salvarBarbeiro(BarbeiroModel barbeiro){
        return barbeiroRepository.save(barbeiro);
    }

    public void deletarBarbeiro(Long id){
        barbeiroRepository.deleteById(id);
    }

    public BarbeiroModel alterarBarbeiro(Long id, BarbeiroModel barbeiroNovo){
        BarbeiroModel barbeiroAntigo = barbeiroRepository.findById(id).orElse(null);

        if(barbeiroAntigo == null){
            throw new RuntimeException("Barbeiro não está cadastrado");
        }

        barbeiroAntigo.setNomeBarbeiro(barbeiroNovo.getNomeBarbeiro());
        barbeiroAntigo.setTelefoneBarbeiro(barbeiroNovo.getTelefoneBarbeiro());

        return barbeiroRepository.save(barbeiroAntigo);
    }
}

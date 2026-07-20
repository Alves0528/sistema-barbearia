package com.barbearia.sistema.repository;

import com.barbearia.sistema.model.ServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoModel, Long> {
    List<ServicoModel> findByAtivoTrue();
}

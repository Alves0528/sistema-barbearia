package com.barbearia.sistema.repository;

import com.barbearia.sistema.model.BarbeiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeiroRepository extends JpaRepository< BarbeiroModel, Long> {
}

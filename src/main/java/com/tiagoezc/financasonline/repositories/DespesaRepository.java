package com.tiagoezc.financasonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagoezc.financasonline.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}

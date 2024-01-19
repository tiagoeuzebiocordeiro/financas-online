package com.tiagoezc.financasonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagoezc.financasonline.entities.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

}

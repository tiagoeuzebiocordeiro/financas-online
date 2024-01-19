package com.tiagoezc.financasonline.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagoezc.financasonline.entities.Despesa;
import com.tiagoezc.financasonline.repositories.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	public List<Despesa> findAll() {
		return repository.findAll();
	}
	
}

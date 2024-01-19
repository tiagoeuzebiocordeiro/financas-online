package com.tiagoezc.financasonline.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagoezc.financasonline.entities.Despesa;
import com.tiagoezc.financasonline.services.DespesaService;

@RestController
@RequestMapping(value = "/despesas")
public class DespesaController {

	@Autowired
	private DespesaService service;
	
	@GetMapping
	public ResponseEntity<List<Despesa>> getAll() {
		List<Despesa> despesas = service.findAll();
		return ResponseEntity.ok(despesas);
	}
	
}

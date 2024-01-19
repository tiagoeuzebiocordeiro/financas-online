package com.tiagoezc.financasonline.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<Despesa> findById(@PathVariable Long id) {
		Despesa despesa = service.findById(id);
		return ResponseEntity.ok(despesa);
	}
	
	@PostMapping
	public ResponseEntity<Despesa> create(@RequestBody Despesa despesa) {
		despesa = service.create(despesa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(despesa.getId()).toUri();
		return ResponseEntity.created(uri).body(despesa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody Despesa obj) {
		Despesa newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

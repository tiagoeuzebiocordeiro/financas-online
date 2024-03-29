package com.tiagoezc.financasonline.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiagoezc.financasonline.dtos.ContaBancariaCreateRequest;
import com.tiagoezc.financasonline.entities.ContaBancaria;
import com.tiagoezc.financasonline.entities.Despesa;
import com.tiagoezc.financasonline.services.ContaBancariaService;

@RequestMapping(value = "/contasbancarias")
@RestController
public class ContaBancariaController {

	@Autowired
	private ContaBancariaService service;
	
	@GetMapping
	public ResponseEntity<List<ContaBancaria>> getAll() {
		List<ContaBancaria> contasBancarias = service.findAll();
		return ResponseEntity.ok(contasBancarias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaBancaria> findById(@PathVariable Long id) {
		ContaBancaria conta = service.findById(id);
		return ResponseEntity.ok().body(conta);
	}
	
	@PostMapping
	public ResponseEntity<ContaBancariaCreateRequest> create(@RequestBody ContaBancariaCreateRequest conta) {
		ContaBancaria newObj = service.create(conta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/depositar")
	public ResponseEntity<String> depositar(@RequestParam("contaId") Long contaId, @RequestParam("valor") double valor) {
		//Aqui eu pego o titular pra sair no corpo da requisição
		ContaBancaria conta = service.findById(contaId);
		service.depositar(contaId, valor);
		return ResponseEntity.ok("Você depositou R$" + valor + " na conta bancária de titular: " + conta.getTitular());
	}
	
	@PostMapping("/sacar")
	public ResponseEntity<String> sacar(@RequestParam("contaId") Long contaId, @RequestParam("valor") double valor) {
		//Aqui eu pego o titular pra sair no corpo da requisição
		ContaBancaria conta = service.findById(contaId);
		service.sacar(contaId, valor);
		return ResponseEntity.ok("Você sacou R$" + valor + " na conta bancária de titular: " + conta.getTitular());
	}
	
	@PostMapping("/pagardespesas/{contaId}")
	public ResponseEntity<String> pagarDespesas(@PathVariable Long contaId) {
		ContaBancaria conta = service.findById(contaId);
		double valor = 0.0;
		for (int i = 0; i < conta.getDespesas().size(); i++) {
			Despesa despesa = conta.getDespesas().get(i);
			valor += despesa.getValor();
		}
		service.pagar(contaId);
		return ResponseEntity.ok("Você pagou as despesas da conta de titular: " + conta.getTitular() + ", de VALOR = R$" + valor);
	}
	
}

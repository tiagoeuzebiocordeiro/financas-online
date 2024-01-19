package com.tiagoezc.financasonline.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagoezc.financasonline.entities.ContaBancaria;
import com.tiagoezc.financasonline.repositories.ContaBancariaRepository;

@Service
public class ContaBancariaService {

	@Autowired
	private ContaBancariaRepository repository;
	
	public List<ContaBancaria> findAll() {
		return repository.findAll();
	}
	
	public ContaBancaria findById(Long id) {
		ContaBancaria conta = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar a conta com o ID: " + id));
		return conta;
	}
	
	public ContaBancaria create(ContaBancaria obj) { 
		
		obj.setId(null);
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		ContaBancaria conta = findById(id);
		if (conta.getSaldo() > 0) {
			throw new RuntimeException("Não foi possível deletar a conta. Motivo: você ainda possui saldo! Saque tudo para deletar!");
		} else if (!conta.getDespesas().isEmpty()) {
			throw new RuntimeException("Não foi possível deletar a conta. Motivo: você possui despesas pendentes!");
		}
		
		repository.deleteById(id);
	}
	
	public void depositar(Long contaId, double valor) {
		ContaBancaria conta = findById(contaId);
		if (valor < 0) {
			throw new RuntimeException("Você não pode depositar um valor negativo!");
		} else if (valor == 0) {
			throw new RuntimeException("Insira um valor para depositar!");
		}
		conta.depositar(valor);
		repository.save(conta);
	}
	
	public void sacar(Long contaId, double valor) {
		ContaBancaria conta = findById(contaId);
		if (valor < 0) {
			throw new RuntimeException("Você não pode sacar um valor negativo!");
		} else if (valor == 0) {
			throw new RuntimeException("Insira um valor para sacar!");
		} else if (valor > conta.getSaldo()) {
			throw new RuntimeException("Saldo insuficiente para saque!");
		}
		
		conta.sacar(valor);
		repository.save(conta);
		
	}
	
}

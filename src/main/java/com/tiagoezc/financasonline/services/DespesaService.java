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
	
	public Despesa findById(Long id) {
		Despesa despesa = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar a despesa de ID:" + id));
		return despesa;
	}
	
	public Despesa create(Despesa obj) {
		obj.setId(null); // medida de segurança!
		return repository.save(obj);
	}
	
	public Despesa update(Long id, Despesa obj) {
		Despesa newObj = findById(id);
		
		newObj.setTitulo(obj.getTitulo());
		newObj.setValor(obj.getValor());
		newObj.setContaAssociada(obj.getContaAssociada());
		newObj.setDataDespesa(obj.getDataDespesa());
		return repository.save(newObj);
	}
	
	
	public void delete(Long id) {
		//Despesa despesa = findById(id);
		/*if (despesa.getContaAssociada() != null) {
			throw new RuntimeException("Esta despesa está associada com uma conta! Impossível deletar!");
		}*/
		repository.deleteById(id);
	}
}

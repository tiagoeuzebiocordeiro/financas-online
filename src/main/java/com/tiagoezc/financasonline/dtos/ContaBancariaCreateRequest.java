package com.tiagoezc.financasonline.dtos;

import com.tiagoezc.financasonline.entities.ContaBancaria;

public class ContaBancariaCreateRequest {

	private Long id;
	private String titular;
	private String tipoConta; // CPF ou CNPJ
	
	public ContaBancariaCreateRequest() {
	}	
	public ContaBancariaCreateRequest(ContaBancaria obj) {
		this.id = obj.getId();
		this.titular = obj.getTitular();
		this.tipoConta = obj.getTipoConta();
	}

	
	
	public void setId(Long id) {
		this.id = id;
	}



	public void setTitular(String titular) {
		this.titular = titular;
	}



	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}



	public Long getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public String getTipoConta() {
		return tipoConta;
	}
	
	
	
}

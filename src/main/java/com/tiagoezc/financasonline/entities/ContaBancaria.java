package com.tiagoezc.financasonline.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.tiagoezc.financasonline.dtos.ContaBancariaCreateRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contas_bancarias")
public class ContaBancaria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titular;
	private String tipoConta; // CPF ou CNPJ
	private Double saldo;
	@OneToMany(mappedBy = "contaAssociada", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Despesa> despesas;
	
	public ContaBancaria() {
		
	}

	public ContaBancaria(Long id, Double saldo) {
		this.id = id;
		this.saldo = saldo;
	}

	public ContaBancaria(ContaBancariaCreateRequest request) {
		this.id = request.getId();
		this.titular = request.getTitular();
		this.tipoConta = request.getTipoConta();
	}
	
	//local var
		
		public void pagar() {
			double soma = 0.0;
			for (int i = 0; i< despesas.size(); i++ ) {
				soma += despesas.get(i).getValor();
			}
			if (soma > this.saldo) {
				throw new RuntimeException("Não foi possível pagar as despesas! Saldo insuficiente!");
			}
			this.saldo -= soma; // aqui vai ser tipo: se eu tenho 800 e as despesas deu 600, fica: saldo = 800 - 600 = 200 de saldo e as despesas devem ZERAR.
			this.despesas.clear(); // aqui zera
		}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}
	
	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	public String getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public List<Despesa> getDespesas() {
		return despesas;
	}
	
	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}
	
	// Eu estou fazendo as validações no Service.
	public void depositar(double valor) {
		saldo += valor;
		System.out.println("Você depositou R$" + valor);
	}
	// Eu estou fazendo as validações no Service.
	public void sacar(double valor) {
		saldo -= valor;
		System.out.println("Você sacou R$" + valor);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBancaria other = (ContaBancaria) obj;
		return Objects.equals(id, other.id);
	}
}

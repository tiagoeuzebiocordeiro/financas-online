package com.tiagoezc.financasonline.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Despesa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private Double valor;
	private LocalDate dataDespesa;
	private ContaBancaria contaAssociada;
	
	public Despesa() {
		
	}
	
	public Despesa(Long id, String titulo, Double valor, LocalDate dataDespesa) {
		this.id = id;
		this.titulo = titulo;
		this.valor = valor;
		this.dataDespesa = dataDespesa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(LocalDate dataDespesa) {
		this.dataDespesa = dataDespesa;
	}
	
	public ContaBancaria getContaAssociada() {
		return contaAssociada;
	}
	
	public void setContaAssociada(ContaBancaria contaAssociada) {
		this.contaAssociada = contaAssociada;
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
		Despesa other = (Despesa) obj;
		return Objects.equals(id, other.id);
	}
}

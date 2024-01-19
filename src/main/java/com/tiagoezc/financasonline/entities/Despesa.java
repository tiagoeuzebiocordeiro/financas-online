package com.tiagoezc.financasonline.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_despesas")
public class Despesa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private Double valor;
	private LocalDate dataDespesa;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "conta_bancaria_id")
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

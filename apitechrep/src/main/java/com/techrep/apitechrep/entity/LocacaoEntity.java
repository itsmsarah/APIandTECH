package com.techrep.apitechrep.entity;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "locacao")

public class LocacaoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int locacaoId;
	private LocalDateTime  dataInicio;
	private LocalDateTime  dataFim;
	private String statusLocacao;
	private int clientesId;
	private int produtoId;
	private int quantidade;
	private double precoDia;
	
	public LocacaoEntity() {
		

	}

	public LocacaoEntity(int locacaoId, LocalDateTime dataInicio, LocalDateTime dataFim, String statusLocacao,
			int clientesId, int produtoId, int quantidade, double precoDia) {
		super();
		this.locacaoId = locacaoId;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.statusLocacao = statusLocacao;
		this.clientesId = clientesId;
		this.produtoId = produtoId;
		this.quantidade = quantidade;
		this.precoDia =precoDia;
	
		
	}

	public int getLocacaoId() {
		return locacaoId;
	}

	public void setLocacaoId(int locacaoId) {
		this.locacaoId = locacaoId;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public String getStatusLocacao() {
		return statusLocacao;
	}

	public void setStatusLocacao(String statusLocacao) {
		this.statusLocacao = statusLocacao;
	}

	public int getClientesId() {
		return clientesId;
	}

	public void setClientesId(int clientesId) {
		this.clientesId = clientesId;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPrecoDia() {
		return precoDia;
	}

	public void setPrecoDia(int precoDia) {
		this.precoDia = precoDia;
	}
	
}

	
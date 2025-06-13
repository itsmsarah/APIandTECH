package com.techrep.apitechrep.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamentos")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pagamentoId;

    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;
    private String tipoCartao;

    private LocalDateTime dataPagamento;

    private double valorTotal;

    @ManyToOne
    @JoinColumn(name = "locacao_id") // chave estrangeira para locacao
    private LocacaoEntity locacao;

    // CONSTRUTORES
    public PagamentoEntity() {
    }

    public PagamentoEntity(int pagamentoId, String numeroCartao, String nomeTitular,
                           String validade, String cvv, String tipoCartao, LocalDateTime dataPagamento,
                           double valorTotal, LocacaoEntity locacao) {
        this.pagamentoId = pagamentoId;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
        this.tipoCartao = tipoCartao;
        this.dataPagamento = dataPagamento;
        this.valorTotal = valorTotal;
        this.locacao = locacao;
    }

    // GETTERS E SETTERS

    public int getPagamentoId() {
        return pagamentoId;
    }

    public void setPagamentoId(int pagamentoId) {
        this.pagamentoId = pagamentoId;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocacaoEntity getLocacao() {
        return locacao;
    }

    public void setLocacao(LocacaoEntity locacao) {
        this.locacao = locacao;
    }
}

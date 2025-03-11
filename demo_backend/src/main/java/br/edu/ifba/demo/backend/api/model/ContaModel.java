package br.edu.ifba.demo.backend.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "conta")
public class ContaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontas")
    private long idcontas;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private Float valor;

    @Column(name = "data_vencimento", nullable = false)
    private Data data_vencimento;

    @Column(name = "data_pagamento", nullable = false)
    private Data data_pagamento;

    @Column(name = "tipo_conta", nullable = false)
    private String tipo_conta;

    @Column(name = "status_conta", nullable = false)
    private String tipo_conta;

}

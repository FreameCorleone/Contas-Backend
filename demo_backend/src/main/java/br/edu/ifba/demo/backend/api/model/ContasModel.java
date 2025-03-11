package br.edu.ifba.demo.backend.api.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "contas")
public class ContasModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontas")
    private long idcontas;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private Float valor;

    @Column(name = "data_vencimento", nullable = false)
    private Date data_vencimento;

    @Column(name = "data_pagamento", nullable = false)
    private Date data_pagamento;

    @Column(name = "tipo_conta", nullable = false)
    private String tipo_conta;

    @Column(name = "status_contas", nullable = false)
    private boolean status_contas;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private UsuarioModel idusuario;

    @ManyToOne
    @JoinColumn(name = "idcategoria", nullable = false)
    private UsuarioModel idcategoria;

    public ContasModel(long idcontas, String descricao, float valor, Date data_vencimento, 
    Date data_pagamento, String tipo_conta, boolean status_contas, UsuarioModel idusuario, UsuarioModel idcategoria){
        super();
        this.idcontas = idcontas;
        this.descricao = descricao;
        this.valor = valor;
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.tipo_conta = tipo_conta;
        this.status_contas = status_contas;
        this.idusuario = idusuario;
        this.idcategoria = idcategoria;
    }

}

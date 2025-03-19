package br.edu.ifba.demo.backend.api.dto;

import java.time.LocalDate;

import br.edu.ifba.demo.backend.api.model.ContasModel;
import br.edu.ifba.demo.backend.api.model.CategoriaModel;
import br.edu.ifba.demo.backend.api.model.UsuarioModel;
import lombok.Data;

@Data
public class ContasDTO {

    private String descricao;
    private float valor;
    private LocalDate datavencimento;
    private LocalDate datapagamento;
    private String tipoconta;
    private boolean statuscontas;
    private Long idusuario;
    private Long idcategoria;

    public ContasModel toEntity(UsuarioModel usuario, CategoriaModel categoria) {
        ContasModel conta = new ContasModel();
        conta.setDescricao(this.descricao);
        conta.setValor(this.valor);
        conta.setDatavencimento(this.datavencimento);
        conta.setDatapagamento(this.datapagamento);
        conta.setTipoconta(this.tipoconta);
        conta.setStatuscontas(this.statuscontas);
        conta.setIdusuario(usuario);
        conta.setIdcategoria(categoria);
        return conta;
    }
}

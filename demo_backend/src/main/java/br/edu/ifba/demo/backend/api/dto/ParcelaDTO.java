package br.edu.ifba.demo.backend.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.demo.backend.api.model.ParcelaModel;
import lombok.Data;

@Data
public class ParcelaDTO {

    private long idparcela;
    private LocalDate parceladatavencimento;
    private int numeroparcela;
    private double valorparcela;
    private Long idcontas;
    private String descricao;
    private float valor;
    private LocalDate  datavencimento;
    private LocalDate datapagamento;
    private String tipoconta;
    private boolean statuscontas;
    private String status_parcela;


    public static ParcelaDTO converter(ParcelaModel parcelaModel){
        var parcela = new ParcelaDTO();
        parcela.setIdparcela(parcelaModel.getIdparcela());
        parcela.setParceladatavencimento(parcelaModel.getParceladatavencimento());
        parcela.setNumeroparcela(parcelaModel.getNumeroparcela());
        parcela.setValorparcela(parcelaModel.getValorparcela());
        parcela.setDescricao(parcelaModel.getIdcontas().getDescricao());
        parcela.setValor(parcelaModel.getIdcontas().getValor());
        parcela.setDatapagamento(parcelaModel.getIdcontas().getDatapagamento());
        parcela.setDatavencimento(parcelaModel.getIdcontas().getDatavencimento());
        parcela.setTipoconta(parcelaModel.getIdcontas().getTipoconta());
        parcela.setStatuscontas(parcelaModel.getIdcontas().isStatuscontas());
        parcela.setStatus_parcela(parcelaModel.getStatus_parcela()); 
        return parcela;
    }

    public static List<ParcelaDTO> converter(List <ParcelaModel> parcela){
        List<ParcelaDTO> list = new ArrayList<>();
        for(ParcelaModel model : parcela){
            list.add(ParcelaDTO.converter(model));
        }

        return list;
    }

    public ParcelaDTO(){
        super();
    }

    public ParcelaDTO(long idparcela, LocalDate parceladatavencimento, int numeroparcela, double valorparcela, Long idcontas,
    String descricao, float valor, LocalDate  datavencimento, LocalDate  datapagamento, String tipoconta, boolean statuscontas, String status_parcela){
        super();
        this.idparcela = idparcela;
        this.parceladatavencimento = parceladatavencimento;
        this.numeroparcela = numeroparcela;
        this.valorparcela = valorparcela;
        this.idcontas = idcontas;
        this.descricao = descricao;
        this.valor = valor;
        this.datavencimento = datavencimento;
        this.datapagamento = datapagamento;
        this.tipoconta = tipoconta;
        this.statuscontas = statuscontas;
        this.status_parcela = status_parcela;
    }
    
}

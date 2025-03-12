package br.edu.ifba.demo.backend.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.demo.backend.api.model.ParcelaModel;
import lombok.Data;

@Data
public class ParcelaDTO {

    private long idparcela;
    private LocalDate  datavencimento;
    private int numeroparcela;
    private double valorparcela;
    private Long idcontas;

    public static ParcelaDTO converter(ParcelaModel parcelaModel){
        var parcela = new ParcelaDTO();
        parcela.setIdparcela(parcelaModel.getIdparcela());
        parcela.setDatavencimento(parcelaModel.getDatavencimento());
        parcela.setNumeroparcela(parcelaModel.getNumeroparcela());
        parcela.setValorparcela(parcelaModel.getValorparcela());
        parcela.setIdcontas(parcelaModel.getIdcontas().getIdcontas());
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

    public ParcelaDTO(long idparcela, LocalDate datavencimento, int numeroparcela, double valorparcela, Long idcontas){
        super();
        this.idparcela = idparcela;
        this.datavencimento = datavencimento;
        this.numeroparcela = numeroparcela;
        this.valorparcela = valorparcela;
        this.idcontas = idcontas;
    }
    
}

package br.edu.ifba.demo.backend.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.demo.backend.api.model.TelefoneModel;
import lombok.Data;

@Data
public class TelefoneDTO {

    private long idtelefone;
    private String numero;
    private String tiponumero;
    private Long idUsuario;

    public static TelefoneDTO converter(TelefoneModel telefoneModel){
        var telefone = new TelefoneDTO();
        telefone.setIdtelefone(telefoneModel.getIdtelefone());
        telefone.setNumero(telefoneModel.getNumero());
        telefone.setTiponumero(telefoneModel.getTiponumero());
        telefone.setIdUsuario(telefoneModel.getIdUsuario().getIdusuario());
        return telefone;
    }

    public static List<TelefoneDTO> converter(List<TelefoneModel> telefone){
        List<TelefoneDTO> list = new ArrayList<>();
        for(TelefoneModel model : telefone){
            list.add(TelefoneDTO.converter(model));
        }

        return list;
    }

    public TelefoneDTO(){
        super();
    }

    public TelefoneDTO(long idtelefone, String numero, String tiponumero, long idUsuario) {
        super();
        this.idtelefone = idtelefone;
        this.numero = numero;
        this.tiponumero = tiponumero;
        this.idUsuario = idUsuario;
    }
}

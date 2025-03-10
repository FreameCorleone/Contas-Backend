package br.edu.ifba.demo.backend.api.dto;

import br.edu.ifba.demo.backend.api.model.TelefoneModel;
import br.edu.ifba.demo.backend.api.model.UsuarioModel;
import lombok.Data;

@Data
public class TelefoneDTO {

    private long idtelefone;
    private String numero;
    private String tipoNumero;
    private UsuarioModel idUsuario;

    public TelefoneDTO(TelefoneModel telefoneModel) {
        this.idtelefone = telefoneModel.getIdtelefone();
        this.numero = telefoneModel.getNumero();
        this.tipoNumero = telefoneModel.getTiponumero();
        this.idUsuario = telefoneModel.getIdUsuario();
    }
}

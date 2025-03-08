package br.edu.ifba.demo.backend.api.dto;


import br.edu.ifba.demo.backend.api.model.EnderecoModel;
import lombok.Data;


@Data
public class EnderecoDTO {
    private Long id;
    private String nome;
    private boolean status;


    public static EnderecoDTO converter(EnderecoModel generoModel) {
        EnderecoDTO genero = new EnderecoDTO();
        genero.setId(generoModel.getId_genero());
        genero.setNome(generoModel.getNome());
        genero.setStatus(generoModel.isStatus());
        return genero;
    }
}
package br.edu.ifba.demo.backend.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.demo.backend.api.model.UsuarioModel;
import lombok.Data;

@Data
public class UsuarioDTO {

    private long idusuario;
    private String nome;
    private String cpf;
    private String email;
    private String login;
    private String senha;
    private Long idendereco;


    public static UsuarioDTO converter(UsuarioModel usuarioModel) {
        var usuario = new UsuarioDTO();
        usuario.setIdusuario(usuarioModel.getIdusuario());
        usuario.setNome(usuarioModel.getNome());
        usuario.setCpf(usuarioModel.getCpf());
        usuario.setEmail(usuarioModel.getEmail());
        usuario.setLogin(usuarioModel.getLogin());
        usuario.setSenha(usuarioModel.getSenha());
        usuario.setIdendereco(usuarioModel.getIdendereco().getIdendereco());
        return usuario;
    }

    public static List<UsuarioDTO> converter(List<UsuarioModel> usuarios) {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioModel model : usuarios) {
            list.add(UsuarioDTO.converter(model));
        }
        return list;
    }

    public UsuarioDTO() {
        super();
    }

    public UsuarioDTO(long idusuario, String nome, String cpf, String email, String login, String senha, long idendereco) {
        super();
        this.idusuario = idusuario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.idendereco = idendereco;

    }

}

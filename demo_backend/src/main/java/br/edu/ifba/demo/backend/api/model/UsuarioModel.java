package br.edu.ifba.demo.backend.api.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private long idusuario;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "idendereco", nullable = false)
    private EnderecoModel idendereco;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TelefoneModel> telefones;

    public UsuarioModel() {
        super();
    }

    public UsuarioModel(long idusuario, String nome, String cpf, String email, String login, String senha, EnderecoModel idendereco) {
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

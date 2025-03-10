package br.edu.ifba.demo.backend.api.model;

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
@Table(name = "telefone")
public class TelefoneModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtelefone")
    private Long idtelefone;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "tipo_numero", nullable = false)
    private String tiponumero;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel idUsuario;

    public TelefoneModel(){
        super();
    }

    public TelefoneModel(long idtelefone, String numero, String tiponumero, UsuarioModel idUsuario){
        super();
        this.idtelefone = idtelefone;
        this.numero = numero;
        this.tiponumero = tiponumero;
        this.idUsuario = idUsuario;
    }
    
}

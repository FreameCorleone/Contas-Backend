package br.edu.ifba.demo.backend.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.demo.backend.api.model.TelefoneModel;
import br.edu.ifba.demo.backend.api.model.UsuarioModel;

@Repository
public interface TelefoneRepository 
    extends JpaRepository<TelefoneModel, Long> 
{
    
    Optional<TelefoneModel> findByNumero(String numero);
    Optional<TelefoneModel> findByTiponumero(String tiponumero);
    Optional<TelefoneModel> findByIdusuario(UsuarioModel idUsuario);

}

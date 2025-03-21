package br.edu.ifba.demo.backend.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.demo.backend.api.model.EnderecoModel;



public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long>{


}
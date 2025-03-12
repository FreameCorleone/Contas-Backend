package br.edu.ifba.demo.backend.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.demo.backend.api.model.ContasModel;


@Repository
public interface ContasRepository
    extends JpaRepository<ContasModel, Long>
{

    List<ContasModel> findByDatavencimento(LocalDateTime datavencimento);
    List<ContasModel> findByDatapagamento(LocalDateTime datapagamento);
    List<ContasModel> findByTipoconta(String tipoConta);
    List<ContasModel> findByStatuscontas(Boolean statuscontas);


}
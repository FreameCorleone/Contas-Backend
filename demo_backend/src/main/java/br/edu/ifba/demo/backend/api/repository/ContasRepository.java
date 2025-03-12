package br.edu.ifba.demo.backend.api.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.demo.backend.api.model.ContasModel;


@Repository
public interface ContasRepository
    extends JpaRepository<ContasModel, Long>
{

    Optional<ContasModel> findByDatavencimento(Date datavencimento);
    Optional<ContasModel> findByDatapagamento(Date datapagamento);
    Optional<ContasModel> findByTipoconta(String tipoconta);
    Optional<ContasModel> findByStatuscontas(Boolean Statuscontas);


}
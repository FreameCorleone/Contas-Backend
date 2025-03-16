package br.edu.ifba.demo.backend.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.demo.backend.api.model.ParcelaModel;
import br.edu.ifba.demo.backend.api.repository.ParcelaRepository;

@RestController
@RequestMapping("/parcela")
public class ParcelaController {

    @Autowired
    private ParcelaRepository parcelaRepository;

    public ParcelaController (ParcelaRepository parcelaRepository){
        this.parcelaRepository = parcelaRepository;
    }

    @GetMapping
	public String teste() {
		return "Testando Rota parcela";
	}
	
	@GetMapping("/listall")
	public List<ParcelaModel> listall() {
		var parcela = parcelaRepository.findAll();
		return parcela;
	}

	@GetMapping("/buscarporvencimento/{datavencimento}")
    public ResponseEntity<List<ParcelaModel>> findByDatavencimento(@PathVariable LocalDate  datavencimento) {
		List<ParcelaModel> parcela = parcelaRepository.findByDatavencimento(datavencimento);
        return ResponseEntity.ok(parcela);
	}


}

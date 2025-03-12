package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.demo.backend.api.model.ContasModel;
import br.edu.ifba.demo.backend.api.repository.ContasRepository;

@RestController
@RequestMapping("/contas")
public class ContasController {
    
    @Autowired
    private ContasRepository contasRepository;

    public ContasController(ContasRepository contasRepository){
        this.contasRepository = contasRepository;
    }

    @GetMapping
	public String teste() {
		return "Testando Rota contas";
	}
	
	@GetMapping("/listall")
	public List<ContasModel> listall() {
		var contas = contasRepository.findAll();
		return contas;
	}

    @GetMapping("/buscarporid/{id}")
	public ContasModel findById(@PathVariable("id") Long id) {
		Optional<ContasModel> contas = contasRepository.findById(id);
		if (contas.isPresent()){
			return contas.get();
		}

		return null;
	}


}

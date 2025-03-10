package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.demo.backend.api.model.TelefoneModel;
import br.edu.ifba.demo.backend.api.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public TelefoneController (TelefoneRepository telefoneRepository){
        this.telefoneRepository = telefoneRepository;
    }

    @GetMapping
	public String teste() {
		return "Testando Rota telefone";
	}
	
	@GetMapping("/listall")
	public List<TelefoneModel> listall() {
		var telefone = telefoneRepository.findAll();
		return telefone;
	}

    @GetMapping("buscarporid/{id}")
    public TelefoneModel findById(@PathVariable("id") Long id) {
		Optional<TelefoneModel> telefone = telefoneRepository.findById(id);
		if ( telefone.isPresent() )
			return telefone.get();
        return null;
    }

	@GetMapping("buscarpornumero/{numero}")
    public TelefoneModel findByNumero(@PathVariable("numero") String numero) {
		Optional<TelefoneModel> telefone = telefoneRepository.findByNumero(numero);
		if ( telefone.isPresent() )
			return telefone.get();
        return null;
    }

	@GetMapping("buscarportiponumero/{tiponumero}")
    public List<TelefoneModel> findByTiponumero(@PathVariable("tiponumero") String tiponumero) {
        List<TelefoneModel> telefones = telefoneRepository.findByTiponumero(tiponumero);
        if (telefones.isEmpty()) {
            return null;
        }
        return telefones;
    }

    
}

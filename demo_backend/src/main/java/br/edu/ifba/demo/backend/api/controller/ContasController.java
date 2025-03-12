package br.edu.ifba.demo.backend.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/buscarportipoconta/{tipoConta}")
	public ResponseEntity<List<ContasModel>> findByTipoconta(@PathVariable String tipoConta) {
		List<ContasModel> contas = contasRepository.findByTipoconta(tipoConta);
		return ResponseEntity.ok(contas);
	}

	@GetMapping("/buscarporstatuscontas/{statuscontas}")
	public ResponseEntity<List<ContasModel>> findByStatuscontas(@PathVariable Boolean statuscontas) {
		List<ContasModel> contas = contasRepository.findByStatuscontas(statuscontas);
		return ResponseEntity.ok(contas);
	}

	@GetMapping("/buscarporvencimento/{datavencimento}")
    public ResponseEntity<List<ContasModel>> findByDatavencimento(@PathVariable LocalDate  datavencimento) {
        List<ContasModel> contas = contasRepository.findByDatavencimento(datavencimento);
        return ResponseEntity.ok(contas);
    }

	@GetMapping("/buscarporpagamento/{datapagamento}")
    public ResponseEntity<List<ContasModel>> findByDatapagamento(@PathVariable LocalDate  datapagamento) {
        List<ContasModel> contas = contasRepository.findByDatapagamento(datapagamento);
        return ResponseEntity.ok(contas);
    }

	@GetMapping("/buscarporusuario/{idusuario}")
    public ResponseEntity<List<ContasModel>> buscarPorUsuario(@PathVariable Long idusuario) {
        List<ContasModel> contas = contasRepository.findByIdusuarioIdusuario(idusuario);
        return ResponseEntity.ok(contas);
    }

	@GetMapping("/buscarporcategoria/{idcategoria}")
    public ResponseEntity<List<ContasModel>> buscarPorCategoria(@PathVariable Long idcategoria) {
        List<ContasModel> contas = contasRepository.findByIdcategoriaIdcategoria(idcategoria);
        return ResponseEntity.ok(contas);
    }

	@PostMapping("/salvar")
    public ResponseEntity<ContasModel> addTelefone(@RequestBody ContasModel contas) {
        ContasModel savedContas = contasRepository.save(contas);
        return new ResponseEntity<>(savedContas, HttpStatus.CREATED);
    }

}

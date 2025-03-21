package br.edu.ifba.demo.backend.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/buscarporconta/{idconta}")
    public ResponseEntity<List<ParcelaModel>> findByIdcontas_Idcontas(@PathVariable Long  idconta) {
		List<ParcelaModel> parcela = parcelaRepository.findByIdcontas_Idcontas(idconta);
        return ResponseEntity.ok(parcela);
	}

	@PostMapping("/salvar")
	public ResponseEntity<ParcelaModel> addParcela(@RequestBody ParcelaModel parcela){
		ParcelaModel savedParcela = parcelaRepository.save(parcela);
		return new ResponseEntity<>(savedParcela, HttpStatus.CREATED);
	}

	@PutMapping("/atualizar/{id}")
public ResponseEntity<ParcelaModel> atualizarParcela(@PathVariable Long id, @RequestBody ParcelaModel parcelaAtualizada) {

    if (!parcelaRepository.existsById(id)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    ParcelaModel parcelaExistente = parcelaRepository.findById(id).orElse(null);

    if (parcelaExistente != null) {
        parcelaExistente.setParceladatavencimento(parcelaAtualizada.getParceladatavencimento());
        parcelaExistente.setNumeroparcela(parcelaAtualizada.getNumeroparcela());
        parcelaExistente.setValorparcela(parcelaAtualizada.getValorparcela());
        parcelaExistente.setStatus_parcela(parcelaAtualizada.getStatus_parcela());
        parcelaExistente.setIdcontas(parcelaAtualizada.getIdcontas());

        ParcelaModel parcelaSalva = parcelaRepository.save(parcelaExistente);

        return ResponseEntity.ok(parcelaSalva);
    }

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteParcela(@PathVariable Long id) {
		if (!parcelaRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Parcela não encontrada!");
		}
		
		parcelaRepository.deleteById(id);
		return ResponseEntity.ok("Parcela deletada com sucesso!");
	}

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<ParcelaModel> findById(@PathVariable Long id) {

        ParcelaModel parcela = parcelaRepository.findById(id).orElse(null);
        
        if (parcela == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        return ResponseEntity.ok(parcela);
    }

}

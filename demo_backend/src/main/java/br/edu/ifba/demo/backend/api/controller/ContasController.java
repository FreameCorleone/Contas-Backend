package br.edu.ifba.demo.backend.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.demo.backend.api.dto.ContasDTO;
import br.edu.ifba.demo.backend.api.model.CategoriaModel;
import br.edu.ifba.demo.backend.api.model.ContasModel;
import br.edu.ifba.demo.backend.api.model.UsuarioModel;
import br.edu.ifba.demo.backend.api.repository.CategoriaRepository;
import br.edu.ifba.demo.backend.api.repository.ContasRepository;
import br.edu.ifba.demo.backend.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "http://localhost:8080")
public class ContasController {

    @Autowired
    private ContasRepository contasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String teste() {
        return "Testando Rota contas";
    }

    @GetMapping("/listall")
    public List<ContasModel> listAll() {
        return contasRepository.findAll();
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<ContasModel> findById(@PathVariable("id") Long id) {
        return contasRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/buscarporidusuario/{idusuario}")
    public ResponseEntity<List<ContasModel>> buscarPorUsuario(@PathVariable Long idusuario) {
        return ResponseEntity.ok(contasRepository.findByIdusuarioIdusuario(idusuario));
    }



    @PostMapping("/salvar")
public ResponseEntity<?> addContas(@RequestBody ContasDTO contasDTO) {
    UsuarioModel usuario = usuarioRepository.findById(contasDTO.getIdusuario())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    CategoriaModel categoria = categoriaRepository.findById(contasDTO.getIdcategoria())
        .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

    ContasModel contas = contasDTO.toEntity(usuario, categoria);

    ContasModel savedContas = contasRepository.save(contas);
    return new ResponseEntity<>(savedContas, HttpStatus.CREATED);
}


@PutMapping("/atualizar/{id}")
public ResponseEntity<?> updateContas(@PathVariable Long id, @RequestBody ContasDTO contasDTO) {
    if (!contasRepository.existsById(id)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
    }

    UsuarioModel usuario = usuarioRepository.findById(contasDTO.getIdusuario())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    CategoriaModel categoria = categoriaRepository.findById(contasDTO.getIdcategoria())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

    ContasModel contaExistente = contasRepository.findById(id).get();

    contaExistente.setDescricao(contasDTO.getDescricao());
    contaExistente.setValor(contasDTO.getValor());
    contaExistente.setDatavencimento(contasDTO.getDatavencimento());
    contaExistente.setDatapagamento(contasDTO.getDatapagamento());
    contaExistente.setTipoconta(contasDTO.getTipoconta());
    contaExistente.setStatuscontas(contasDTO.isStatuscontas());
    contaExistente.setIdusuario(usuario);
    contaExistente.setIdcategoria(categoria);

    ContasModel updatedConta = contasRepository.save(contaExistente);
    return ResponseEntity.ok(updatedConta);
}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if (!contasRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }

        try {
            contasRepository.deleteById(id);
            return ResponseEntity.ok("Conta excluída com sucesso!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível excluir a conta, pois há parcelas vinculadas a ela.");
        }
    }
}

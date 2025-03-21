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

    @GetMapping("/buscarportipoconta/{tipoConta}")
    public ResponseEntity<List<ContasModel>> findByTipoconta(@PathVariable String tipoConta) {
        return ResponseEntity.ok(contasRepository.findByTipoconta(tipoConta));
    }

    @GetMapping("/buscarporstatuscontas/{statuscontas}")
    public ResponseEntity<List<ContasModel>> findByStatuscontas(@PathVariable Boolean statuscontas) {
        return ResponseEntity.ok(contasRepository.findByStatuscontas(statuscontas));
    }

    @GetMapping("/buscarporvencimento/{datavencimento}")
    public ResponseEntity<List<ContasModel>> findByDatavencimento(@PathVariable LocalDate datavencimento) {
        return ResponseEntity.ok(contasRepository.findByDatavencimento(datavencimento));
    }

    @GetMapping("/buscarporpagamento/{datapagamento}")
    public ResponseEntity<List<ContasModel>> findByDatapagamento(@PathVariable LocalDate datapagamento) {
        return ResponseEntity.ok(contasRepository.findByDatapagamento(datapagamento));
    }

    @GetMapping("/buscarporidusuario/{idusuario}")
    public ResponseEntity<List<ContasModel>> buscarPorUsuario(@PathVariable Long idusuario) {
        return ResponseEntity.ok(contasRepository.findByIdusuarioIdusuario(idusuario));
    }

    @GetMapping("/buscarpornomeusuario/{nome}")
    public ResponseEntity<List<ContasModel>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(contasRepository.findByIdusuario_Nome(nome));
    }

    @GetMapping("/buscarporcategoria/{idcategoria}")
    public ResponseEntity<List<ContasModel>> buscarPorCategoria(@PathVariable Long idcategoria) {
        return ResponseEntity.ok(contasRepository.findByIdcategoriaIdcategoria(idcategoria));
    }

    @PostMapping("/salvar")
public ResponseEntity<?> addContas(@RequestBody ContasDTO contasDTO) {
    // Buscar usuário e categoria pelo ID antes de salvar
    UsuarioModel usuario = usuarioRepository.findById(contasDTO.getIdusuario())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    CategoriaModel categoria = categoriaRepository.findById(contasDTO.getIdcategoria())
        .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

    // Converter DTO para Entidade
    ContasModel contas = contasDTO.toEntity(usuario, categoria);

    // Salvar conta
    ContasModel savedContas = contasRepository.save(contas);
    return new ResponseEntity<>(savedContas, HttpStatus.CREATED);
}


@PutMapping("/atualizar/{id}")
public ResponseEntity<?> updateContas(@PathVariable Long id, @RequestBody ContasDTO contasDTO) {
    if (!contasRepository.existsById(id)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
    }

    // Buscar usuário e categoria pelo ID
    UsuarioModel usuario = usuarioRepository.findById(contasDTO.getIdusuario())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    CategoriaModel categoria = categoriaRepository.findById(contasDTO.getIdcategoria())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

    // Buscar a conta existente
    ContasModel contaExistente = contasRepository.findById(id).get();

    // Atualizar os campos
    contaExistente.setDescricao(contasDTO.getDescricao());
    contaExistente.setValor(contasDTO.getValor());
    contaExistente.setDatavencimento(contasDTO.getDatavencimento());
    contaExistente.setDatapagamento(contasDTO.getDatapagamento());
    contaExistente.setTipoconta(contasDTO.getTipoconta());
    contaExistente.setStatuscontas(contasDTO.isStatuscontas());
    contaExistente.setIdusuario(usuario);
    contaExistente.setIdcategoria(categoria);

    // Salvar a conta atualizada
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

package br.edu.ifba.demo.backend.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import br.edu.ifba.demo.backend.api.dto.EnderecoDTO;
import br.edu.ifba.demo.backend.api.model.EnderecoModel;
import br.edu.ifba.demo.backend.api.repository.EnderecoRepository;


@RestController
@RequestMapping("/endereco")
public class EnderecoController {


    @Autowired
    private EnderecoRepository enderecoRepository;


    public EnderecoController(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping("/listall")
    public List<EnderecoDTO> listall() {
        List<EnderecoModel> endereco = enderecoRepository.findAll();
        return endereco.stream().map(EnderecoDTO::converter).toList();
    }

    @GetMapping("buscarporid/{id}")
    public EnderecoModel findById(@PathVariable ("id") Long id){
        Optional<EnderecoModel> endereco = enderecoRepository.findById(id);
        if(endereco.isPresent())
            return endereco.get();

        return null;
    }

    @PostMapping("/salvar")
    public ResponseEntity<EnderecoModel> addEndereco(@RequestBody EnderecoModel endereco){
        EnderecoModel savedEndereco = enderecoRepository.save(endereco);
        return new ResponseEntity<EnderecoModel>(savedEndereco, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoModel> atualizarEndereco
    (@PathVariable Long id, @RequestBody EnderecoModel enderecoAtualizado){

        Optional<EnderecoModel> enderecoExistente = enderecoRepository.findById(id);

        if(enderecoExistente.isPresent()){
            EnderecoModel endereco = enderecoExistente.get();

            endereco.setEstado(enderecoAtualizado.getEstado());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setCep(enderecoAtualizado.getCep());

            EnderecoModel enderecoSalvo = enderecoRepository.save(endereco);
            return ResponseEntity.ok(enderecoSalvo);

        }
        else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if (!enderecoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }

        try {
            enderecoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível deletar o endereço, pois ele está associado a um ou mais usuários.");
        }
    }

}
package br.edu.ifba.demo.backend.api.controller;


import java.util.List;
import java.util.Optional;

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

import br.edu.ifba.demo.backend.api.dto.EnderecoDTO;
import br.edu.ifba.demo.backend.api.model.EnderecoModel;
import br.edu.ifba.demo.backend.api.repository.EnderecoRepository;


@RestController
@RequestMapping("/genero")
public class EnderecoController {


    @Autowired
    private EnderecoRepository generoRepository;


    public EnderecoController(EnderecoRepository generoRepository){
        this.generoRepository = generoRepository;
    }


    @GetMapping
    public String teste(){


        return "Testando rota genero";
    }


    @GetMapping("/listall")
    public List<EnderecoDTO> listall() {
        List<EnderecoModel> generos = generoRepository.findAll();
        return generos.stream().map(EnderecoDTO::converter).toList();
    }




    @GetMapping("/buscarporid/{id}")
    public EnderecoModel findById(@PathVariable ("id") Long id){
        Optional<EnderecoModel> genero = generoRepository.findById(id);
        if(genero.isPresent())
            return genero.get();
       
        return null;
    }


    @GetMapping("buscarpornome/{nome}")
    public EnderecoModel findByNome(@PathVariable ("nome") String nome){
        Optional<EnderecoModel> genero = generoRepository.findByNome(nome);
        if (genero.isPresent())
            return genero.get();
       
        return null;


    }


    @PostMapping("/salvar")
    public ResponseEntity<EnderecoModel> addGenero(@RequestBody EnderecoModel genero){
        EnderecoModel savedGenero = generoRepository.save(genero);
        return new ResponseEntity<EnderecoModel>(savedGenero, HttpStatus.CREATED);
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoModel> atualizarGenero
    (@PathVariable Long id, @RequestBody EnderecoModel generoAtualizado){
        Optional<EnderecoModel> generoExistente = generoRepository.findById(id);


        if(generoExistente.isPresent()){
            EnderecoModel genero = generoExistente.get();


            genero.setNome(generoAtualizado.getNome());


            EnderecoModel generoSalvo = generoRepository.save(genero);
            return ResponseEntity.ok(generoSalvo);
        }


        else{


            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deletargenero/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ("id") Long id){
        if(generoRepository.existsById(id)){
            generoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } 
        
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
   
}
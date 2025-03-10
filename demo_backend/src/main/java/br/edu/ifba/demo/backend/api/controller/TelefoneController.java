package br.edu.ifba.demo.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.demo.backend.api.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public TelefoneController (TelefoneRepository telefoneRepository){
        this.telefoneRepository = telefoneRepository;
    }
    
}

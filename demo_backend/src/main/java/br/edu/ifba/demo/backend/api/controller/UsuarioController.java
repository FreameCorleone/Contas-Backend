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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.demo.backend.api.model.UsuarioModel;
import br.edu.ifba.demo.backend.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuRepository;
	
	public UsuarioController(UsuarioRepository usuRepository) {
		this.usuRepository = usuRepository;
	}

	@GetMapping
	public String teste() {
		return "Testando Rota Usuario";
	}
	
	@GetMapping("/listall")
	public List<UsuarioModel> listall() {
		var usuarios = usuRepository.findAll();
		return usuarios;
	}
	
	@GetMapping("/listalldados")
	public List<Object[]> getUsuarios(){
		return usuRepository.getUsuarioDados();
	}

	@GetMapping("buscarporid/{id}")
    public UsuarioModel findById(@PathVariable("id") Long id) {
		Optional<UsuarioModel> usuario = usuRepository.findById(id);
		if ( usuario.isPresent() )
			return usuario.get();
        return null;
    }

	@GetMapping("buscarpornome/{nome}")
    public UsuarioModel findByNome(@PathVariable("nome") String nome) {
		Optional<UsuarioModel> usuario = usuRepository.findByNome(nome);
		if ( usuario.isPresent() )
			return usuario.get();
        return null;
    }

	@GetMapping("buscarporlogin/{login}")
    public UsuarioModel findByLogin(@PathVariable("login") String login) {
		Optional<UsuarioModel> usuario = usuRepository.findByLogin(login);
		if ( usuario.isPresent() )
			return usuario.get();
        return null;
    }

	@GetMapping("buscarporemail/{email}")
    public UsuarioModel findByEmail(@PathVariable("email") String email) {
		Optional<UsuarioModel> usuario = usuRepository.findByEmail(email);
		if ( usuario.isPresent() )
			return usuario.get();
        return null;
    }

	@GetMapping("buscarporcpf/{cpf}")
    public UsuarioModel findByCpf(@PathVariable("cpf") String cpf) {
		Optional<UsuarioModel> usuario = usuRepository.findByCpf(cpf);
		if ( usuario.isPresent() )
			return usuario.get();
        return null;
    }


	  @PostMapping("/salvar")
	  public ResponseEntity<UsuarioModel> addUsuario(@RequestBody UsuarioModel usuario) {
		  UsuarioModel savedUsuario = usuRepository.save(usuario);
		  return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
	  }


	  @DeleteMapping("/deleteusuario/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ("id") Long id){
        if(usuRepository.existsById(id)){
            usuRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

	
}
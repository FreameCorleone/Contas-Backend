package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifba.demo.backend.api.dto.UsuarioDTO;
import br.edu.ifba.demo.backend.api.model.EnderecoModel;
import br.edu.ifba.demo.backend.api.model.UsuarioModel;
import br.edu.ifba.demo.backend.api.repository.EnderecoRepository;
import br.edu.ifba.demo.backend.api.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:8080")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	public UsuarioController(UsuarioRepository usuRepository, EnderecoRepository enderecoRepository) {
		this.usuRepository = usuRepository;
		this.enderecoRepository = enderecoRepository;
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
	public List<UsuarioDTO> getUsuarios(){
		List<UsuarioModel> usuarios = usuRepository.findAll();
		return UsuarioDTO.converter(usuarios);
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


	@PostMapping("/criar")
	public ResponseEntity<String> addUsuario(@RequestBody UsuarioModel usuario) {
		// Verifica se já existe um usuário com o mesmo e-mail ou CPF
		boolean emailExists = usuRepository.existsByEmail(usuario.getEmail());
		boolean cpfExists = usuRepository.existsByCpf(usuario.getCpf());

		if (emailExists || cpfExists) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Erro: Já existe um usuário cadastrado com este e-mail ou CPF.");
		}

		// Primeiro, salva o endereço antes de associá-lo ao usuário
		if (usuario.getIdendereco() != null) {
			EnderecoModel endereco = usuario.getIdendereco();
			endereco = enderecoRepository.save(endereco); // Salva o endereço no banco
			usuario.setIdendereco(endereco); // Associa o endereço salvo ao usuário
		}

		// Salva o usuário
		usuRepository.save(usuario);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Usuário criado com sucesso!");
	}

	@PostMapping("/salvar")
	  public ResponseEntity<UsuarioModel> atualizarUsuario(@RequestBody UsuarioModel usuario) {
		  UsuarioModel savedUsuario = usuRepository.save(usuario);
		  return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
		if (!usuRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}

		try {
			usuRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Não é possível deletar o usuário, pois ele possui dados vinculados.");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> validarLogin(@RequestBody UsuarioDTO loginDTO, HttpSession session) {
		Optional<UsuarioModel> usuarioOpt = usuRepository.findByLogin(loginDTO.getLogin());

		if (usuarioOpt.isPresent()) {
			UsuarioModel usuario = usuarioOpt.get();
			if (usuario.getSenha().equals(loginDTO.getSenha())) {
				session.setAttribute("usuarioLogado", usuario); // Armazena na sessão
				return ResponseEntity.ok(usuario);
			}
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"erro\": \"Login ou senha inválidos\"}");
	}

	@GetMapping("/me")
	public ModelAndView getUsuarioLogado(HttpSession session) {
		UsuarioDTO usuarioLogado = (UsuarioDTO) session.getAttribute("usuarioLogado");

		if (usuarioLogado == null) {
			return new ModelAndView("redirect:/login"); // Redireciona se não estiver logado
		}

		ModelAndView model = new ModelAndView("usuario");
		model.addObject("usuario", usuarioLogado);
		return model;
	}

}
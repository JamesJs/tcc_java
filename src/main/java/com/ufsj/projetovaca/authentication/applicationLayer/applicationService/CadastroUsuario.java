package com.ufsj.projetovaca.authentication.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioInput;
import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioOutput;
import com.ufsj.projetovaca.authentication.applicationLayer.exception.NotFoundWithId;
import com.ufsj.projetovaca.authentication.assemblers.UsuarioAssembler;
import com.ufsj.projetovaca.authentication.domainLayer.models.Usuario;
import com.ufsj.projetovaca.authentication.domainLayer.repositories.UsuarioRepository;
@Service
public class CadastroUsuario {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioAssembler usuarioAssembler;
	@PostMapping
	public UsuarioOutput criar(UsuarioInput usuarioInput) {
		Usuario usuario = usuarioAssembler.converterEntidade(usuarioInput);
		usuario.setAdmin(false);
		usuario.setDesativado(false);
		Usuario novoUsuario = usuarioRepository.save(usuario);
		
		UsuarioOutput usuarioOutput  = usuarioAssembler.converterOutput(novoUsuario);
		
		return usuarioOutput;			
	}
	
	public UsuarioOutput atualizar(UsuarioInput usuarioInput, Long id) throws NotFoundWithId {
		
		Usuario usuario = existeUsuario(id);
		
		Usuario attUsuario = usuarioAssembler.converterEntidade(usuarioInput);
		
		BeanUtils.copyProperties(attUsuario, usuario,"id");
		
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(attUsuario);
		
		return usuarioOutput;
		
		
	}
	
	public List<UsuarioOutput> listar(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		List<UsuarioOutput> usuariosOutput = usuarioAssembler.converterColecaoOutput(usuarios);
		
		return usuariosOutput;
		
	}
	
	public UsuarioOutput desativar(Long id) throws NotFoundWithId {
		
		Usuario usuario = existeUsuario(id);
		
		usuario.setDesativado(true);
		
		usuarioRepository.save(usuario);
		
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(usuario);
		
		return usuarioOutput;	
		
	}
	
	public UsuarioOutput ativar(Long id) throws NotFoundWithId {
		
		Usuario usuario = existeUsuario(id);
		
		usuario.setDesativado(false);
		
		usuarioRepository.save(usuario);
		
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(usuario);
		
		return usuarioOutput;	
		
	}
	
	public UsuarioOutput isAdmin(Long id) throws NotFoundWithId {
		
		Usuario usuario = existeUsuario(id);
		
		usuario.setAdmin(true);
		
		usuarioRepository.save(usuario);
		
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(usuario);
		
		return usuarioOutput;	
		
	}
	
	public UsuarioOutput isNotAdmin(Long id) throws NotFoundWithId {
		
		Usuario usuario = existeUsuario(id);
		
		usuario.setAdmin(false);
		
		usuarioRepository.save(usuario);
		
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(usuario);
		
		return usuarioOutput;	
		
	}
	
	public UsuarioOutput deletar(Long id) throws NotFoundWithId {
		
		Usuario usuario = existeUsuario(id);
		
		usuarioRepository.delete(usuario);
		
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(usuario);
		
		return usuarioOutput;	
		
	}
	
	
	
	
	public UsuarioOutput encontrar(Long id) throws NotFoundWithId {
		
		Usuario usuario = existeUsuario(id);
		
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(usuario);
		
		return usuarioOutput;	
		
	}
	
	
	
	
	
	private Usuario existeUsuario(Long id) throws NotFoundWithId {
		Optional<Usuario> opUsuario = usuarioRepository.findById(id);
		
		if(opUsuario.isEmpty()) {
			throw new NotFoundWithId("Não foi encontrado um usuário com esse id");
		}
		return opUsuario.get();
	}
	
	
	
}

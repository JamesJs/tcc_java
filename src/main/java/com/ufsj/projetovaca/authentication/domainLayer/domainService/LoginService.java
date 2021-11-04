package com.ufsj.projetovaca.authentication.domainLayer.domainService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.Login;
import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioOutput;
import com.ufsj.projetovaca.authentication.assemblers.UsuarioAssembler;
import com.ufsj.projetovaca.authentication.domainLayer.exception.CantLogin;
import com.ufsj.projetovaca.authentication.domainLayer.exception.NotFoundUsuarioByLogin;
import com.ufsj.projetovaca.authentication.domainLayer.models.Usuario;
import com.ufsj.projetovaca.authentication.domainLayer.repositories.UsuarioRepository;
@Service
public class LoginService {
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioAssembler usuarioAssembler;
	
	public UsuarioOutput execute(Login login) throws NotFoundUsuarioByLogin, CantLogin {
		
		Optional<Usuario> opUsuario = usuarioRepository.findByLogin(login.getLogin());
		
		if(opUsuario.isEmpty() || opUsuario.get().isDesativado()) {
			throw new NotFoundUsuarioByLogin("Não foi encontrado um usuário");
		}
		
		
		Usuario usuario = opUsuario.get();
		
		if(!usuario.getSenha().equals(login.senha)) {
			throw new CantLogin("Senha ou login errado");
		}
		UsuarioOutput usuarioOutput = usuarioAssembler.converterOutput(usuario);
		
		return usuarioOutput;
		
	}
}

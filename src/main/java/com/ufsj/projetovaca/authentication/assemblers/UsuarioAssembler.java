package com.ufsj.projetovaca.authentication.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioInput;
import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioOutput;
import com.ufsj.projetovaca.authentication.domainLayer.models.Usuario;
@Service
public class UsuarioAssembler {
	
	
	public Usuario converterEntidade(UsuarioInput usuarioInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(UsuarioInput.class,Usuario.class).addMappings(mp -> {   
			mp.skip(Usuario::setId);
		});
		
		return modelMapper.map(usuarioInput, Usuario.class);
		
	}
	public UsuarioOutput converterOutput(Usuario usuario) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(usuario, UsuarioOutput.class);
		
	}
	
	public List<UsuarioOutput> converterColecaoOutput(List<Usuario> origemList){
			
			List<UsuarioOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}

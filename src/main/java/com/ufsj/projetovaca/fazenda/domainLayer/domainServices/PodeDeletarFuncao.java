package com.ufsj.projetovaca.fazenda.domainLayer.domainServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncionarioRepository;
@Service
public class PodeDeletarFuncao {
	@Autowired
	FuncionarioRepository funcionarioRepository;
	public boolean execute(Long id){
		Optional<List<Funcionario>> opFuncionarios = funcionarioRepository.findByFuncao_IdFuncao(id);
		if(opFuncionarios.get().isEmpty()) {
			return true;
		}
		return false;
	}
}

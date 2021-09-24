package com.ufsj.projetovaca.fazenda.domainLayer.domainServices;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcao;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncaoRepository;

@Service
public class VerificaSePossuiFuncaoFuncionario {
	
	@Autowired
	FuncaoRepository funcaoRepository;
	
	public boolean execute(Long idFuncao) {
		Optional<Funcao> opFuncao = funcaoRepository.findById(idFuncao);
		if(opFuncao.isEmpty() || opFuncao.get().isAtivado() == false) {
			return false;
		}
		return true;
	}
}

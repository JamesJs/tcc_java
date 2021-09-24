package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.AssemblerAdapter;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.Conversores;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcao;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncaoRepository;
@Service
public class CadastroFuncao {
	
	@Autowired
	FuncaoRepository funcaoRepository;
	
	
	Conversores<FuncaoInput, FuncaoOutput, Funcao> conversores = 
			new Conversores<FuncaoInput, FuncaoOutput, Funcao>();
	
	AssemblerAdapter<Funcao, FuncaoInput> conversorEntidade = conversores.criarConversorEntidade(Funcao.class);
	
	AssemblerAdapter<FuncaoOutput, Funcao> conversorOutput = conversores.criarConversorOutput(FuncaoOutput.class);
	
	public FuncaoOutput salvar(FuncaoInput funcaoInput) {
		Funcao funcao = conversorEntidade.converterUnitario(funcaoInput);
		
		FuncaoOutput funcaoOutput = conversorOutput.converterUnitario(funcaoRepository.save(funcao));
		
		return funcaoOutput;
	}
	
	public FuncaoOutput deletar(long id) throws NotFoundWithId {
		Optional<Funcao> opFuncao = funcaoRepository.findById(id);
		
		if(opFuncao.isEmpty()) {
			throw new NotFoundWithId("Não encontrada função com esse id");
		}
		
		Funcao funcao = opFuncao.get();
		
		funcao.setAtivado(!funcao.isAtivado());
		
		FuncaoOutput funcaoOutput = conversorOutput.converterUnitario(funcaoRepository.save(funcao));
		
		return funcaoOutput;
	}
	public FuncaoOutput atualizar(long id,FuncaoInput funcaoInput) throws NotFoundWithId {
		System.out.println(id);
		
		Optional<Funcao> opFuncao = funcaoRepository.findById(id);
		
		if(opFuncao.isEmpty()) {
			throw new NotFoundWithId("Não encontrada função com esse id");
		}
		
		Funcao novaFuncao = conversorEntidade.converterUnitario(funcaoInput);
		
		Funcao funcao = opFuncao.get();
		
		BeanUtils.copyProperties(novaFuncao,funcao);
					
		funcao.setId(id);
		
		FuncaoOutput funcaoOutput = conversorOutput.converterUnitario(funcaoRepository.save(funcao));
		
		return funcaoOutput;
	}
}

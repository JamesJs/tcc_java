package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.AssemblerAdapter;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.Conversores;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.CopiarAtributos;
import com.ufsj.projetovaca.fazenda.domainLayer.domainServices.VerificaSePossuiFuncaoFuncionario;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncionarioRepository;
@Service
public class CadastroFuncionario {
	
	
	
	@Autowired
	VerificaSePossuiFuncaoFuncionario verificaSePossuiFuncaoFuncionario;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	
	Conversores<FuncionarioInput, FuncionarioOutput, Funcionario> conversores = 
			new Conversores<FuncionarioInput, FuncionarioOutput, Funcionario>(); 
	
	AssemblerAdapter<Funcionario, FuncionarioInput> conversorEntidade = 
			conversores.criarConversorEntidade(Funcionario.class);
	
	AssemblerAdapter<FuncionarioOutput, Funcionario> conversorOutput = 
			conversores.criarConversorOutput(FuncionarioOutput.class);
	
	public FuncionarioOutput salvar(FuncionarioInput funcionarioInput) throws NotFoundWithId {
		long idFuncao = funcionarioInput.getFuncaoFuncionario().getIdFuncao();
		
		boolean possuiFuncao = verificaSePossuiFuncaoFuncionario.execute(idFuncao);
		if(!possuiFuncao) {
			throw new NotFoundWithId("Não existe função com esse id");
		}
		
		Funcionario funcionario = conversorEntidade.converterUnitario(funcionarioInput);
		
		funcionario.setIsDemitido(false);
		
		
		funcionario = funcionarioRepository.save(funcionario);
		
		FuncionarioOutput funcionarioOutput = conversorOutput.converterUnitario(funcionario);
		
		return funcionarioOutput;
	}
	
	public FuncionarioOutput deletar(long id) throws NotFoundWithId {
		Optional<Funcionario> opFuncionario = funcionarioRepository.findById(id);
		
		if(opFuncionario.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um funcionario com esse id");
		}
			
		
		Funcionario funcionario = opFuncionario.get();	
		
		funcionario.setIsDemitido(!funcionario.getIsDemitido());
		
		FuncionarioOutput funcionarioOutput = 
				conversorOutput
				.converterUnitario(funcionarioRepository.save(funcionario));
		
		return funcionarioOutput;
	}
	
	public FuncionarioOutput atualizar(FuncionarioInput funcionarioInput, long id) throws NotFoundWithId {
		
		long idFuncao = funcionarioInput.getFuncaoFuncionario().getIdFuncao();
		
		boolean possuiFuncao = verificaSePossuiFuncaoFuncionario.execute(idFuncao);
		if(!possuiFuncao) {
			throw new NotFoundWithId("Função não encontrada com o id");
		}
		
		Optional<Funcionario> opFuncionario = funcionarioRepository.findById(id);
		
		if(opFuncionario.isEmpty()) {
			throw new NotFoundWithId("Funcionário não encontrada com o id");
		}
		
		Funcionario funcionario = opFuncionario.get();
		
		Funcionario novoFuncionario = conversorEntidade.converterUnitario(funcionarioInput);
		
		CopiarAtributos.copiarAtributos(funcionario,novoFuncionario , "idFuncionario");
		
		funcionario.setId(id);
					
		FuncionarioOutput funcionarioOutput = 
				conversorOutput.converterUnitario(funcionarioRepository.save(funcionario));
		
		return funcionarioOutput;
		
	}
}

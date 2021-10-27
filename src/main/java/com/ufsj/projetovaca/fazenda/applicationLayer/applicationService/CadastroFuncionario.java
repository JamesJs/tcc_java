package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers.FuncionarioAssembler;
import com.ufsj.projetovaca.fazenda.domainLayer.domainServices.VerificaSePossuiFuncaoFuncionario;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncionarioRepository;
@Service
public class CadastroFuncionario {
	
	
	
	@Autowired
	VerificaSePossuiFuncaoFuncionario verificaSePossuiFuncaoFuncionario;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	FuncionarioAssembler funcionarioAssembler;
	
	public FuncionarioOutput salvar(FuncionarioInput funcionarioInput) throws NotFoundWithId {
		long idFuncao = funcionarioInput.getIdFuncao();
		
		boolean possuiFuncao = verificaSePossuiFuncaoFuncionario.execute(idFuncao);
		if(!possuiFuncao) {
			throw new NotFoundWithId("Não existe função com esse id");
		}
		
		Funcionario funcionario = funcionarioAssembler.converterEntidade(funcionarioInput);
		
		funcionario.setIsDemitido(false);
		
		
		funcionario = funcionarioRepository.save(funcionario);
		
		FuncionarioOutput funcionarioOutput = funcionarioAssembler.converterOutput(funcionario);
		
		return funcionarioOutput;
	}
	
	public FuncionarioOutput demitir(long id) throws NotFoundWithId {
		Optional<Funcionario> opFuncionario = funcionarioRepository.findById(id);
		
		if(opFuncionario.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um funcionario com esse id");
		}
			
		
		Funcionario funcionario = opFuncionario.get();	
		
		System.out.println(funcionario);
		
		funcionario.setIsDemitido(!funcionario.getIsDemitido());
		
		Funcionario novoFuncionario = funcionarioRepository.save(funcionario);
		
		FuncionarioOutput funcionarioOutput = 
				funcionarioAssembler.converterOutput(novoFuncionario);
		
		return funcionarioOutput;
	}
	
	public FuncionarioOutput deletar(long id) throws NotFoundWithId {
		Optional<Funcionario> opFuncionario = funcionarioRepository.findById(id);
		
		if(opFuncionario.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um funcionario com esse id");
		}
			
		
		Funcionario funcionario = opFuncionario.get();	
		
		funcionarioRepository.delete(funcionario);
		
		FuncionarioOutput funcionarioOutput = 
				funcionarioAssembler.converterOutput(funcionario);
		
		return funcionarioOutput;
	}
	
	public FuncionarioOutput atualizar(FuncionarioInput funcionarioInput, long id) throws NotFoundWithId {
		
		long idFuncao = funcionarioInput.getIdFuncao();
		
		boolean possuiFuncao = verificaSePossuiFuncaoFuncionario.execute(idFuncao);
		if(!possuiFuncao) {
			throw new NotFoundWithId("Função não encontrada com o id");
		}
		
		Optional<Funcionario> opFuncionario = funcionarioRepository.findById(id);
		
		if(opFuncionario.isEmpty()) {
			throw new NotFoundWithId("Funcionário não encontrada com o id");
		}
		
		Funcionario funcionario = opFuncionario.get();
		
		Funcionario novoFuncionario = funcionarioAssembler.converterEntidade(funcionarioInput);
		
		BeanUtils.copyProperties(novoFuncionario, funcionario,"id","isDemitido");
					
		FuncionarioOutput funcionarioOutput = 
				funcionarioAssembler.converterOutput(funcionarioRepository.save(funcionario));
		
		return funcionarioOutput;
		
	}
	public List<FuncionarioOutput> listar(){
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		
		List<FuncionarioOutput> funcionariosOutput = funcionarioAssembler.converterColecaoOutput(funcionarios);
		
		return funcionariosOutput;
		
	}
}

package com.ufsj.projetovaca.financeiro.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ContaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ContaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;
import com.ufsj.projetovaca.financeiro.applicationLayer.assembler.ContaAssembler;
import com.ufsj.projetovaca.financeiro.domainLayer.domainServices.PodeCriarConta;
import com.ufsj.projetovaca.financeiro.domainLayer.models.Conta;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.ContaRepository;



@Service
public class CadastroContaService {
	
	
	@Autowired
	ContaRepository contaRepository;
	
	
	@Autowired
	ContaAssembler contaAssembler;
	
	@Autowired
	PodeCriarConta podeCriarConta;
	
	
	public ContaOutput criar(ContaInput contaInput) throws NotFound {
		
		if(!podeCriarConta.execute(contaInput.getIdTipo())) {
			throw new NotFound("Não foi encontrado um tipo com o id informado");
		}
		
		Conta conta = contaAssembler.converterEntidade(contaInput);
		
		Conta novaConta = contaRepository.save(conta);
		
		ContaOutput contaOutput = contaAssembler.converterOutput(novaConta);
		
		return contaOutput;	
		
	}
	public ContaOutput deletar(Long idConta) throws NotFound {
		
		Optional<Conta> opConta = contaRepository.findById(idConta);
		
		if(opConta.isEmpty()) {
			throw new NotFound("Não foi encontrada uma conta com o id informado");
			
		}
		
		Conta conta = opConta.get();
		
		contaRepository.delete(conta);
		
		ContaOutput contaOutput = contaAssembler.converterOutput(conta);
		
		return contaOutput;
		
	}
	public ContaOutput atualizar(ContaInput contaInput,Long idConta) throws NotFound {
		
		
		Optional<Conta> opConta = contaRepository.findById(idConta);
		
		if(opConta.isEmpty()) {
			throw new NotFound("Não foi encontrada uma conta com o id informado.");
			
		}
		if(!podeCriarConta.execute(contaInput.getIdTipo())) {
			throw new NotFound("Não foi encontrado um tipo com o id informado.");
		}
		
		Conta conta = opConta.get();
		
		Conta contaAtt = contaAssembler.converterEntidade(contaInput);
		
		BeanUtils.copyProperties(contaAtt, conta,"id");
		
		Conta novaConta = contaRepository.save(conta);
		
		ContaOutput contaOutput = contaAssembler.converterOutput(novaConta);
		
		return contaOutput;
		
	}
	public List<ContaOutput> listar() {
		
		List<Conta> contas = contaRepository.findAll();
		
		List<ContaOutput> contasOutput = contaAssembler.converterColecaoOutput(contas);
		
		return contasOutput;
		
	}
	
	public ContaOutput encontrar(Long idConta) throws NotFound {
		
		Optional<Conta> opConta = contaRepository.findById(idConta);
		
		if(opConta.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma conta com o id informado");
			
		}
		
		Conta conta = opConta.get();
		
		ContaOutput contaOutput = contaAssembler.converterOutput(conta);
		
		return contaOutput;
		
	}
}

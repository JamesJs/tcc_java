package com.ufsj.projetovaca.financeiro.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ReceitaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ReceitaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;
import com.ufsj.projetovaca.financeiro.applicationLayer.assembler.ReceitaAssembler;
import com.ufsj.projetovaca.financeiro.domainLayer.domainServices.PodeCriarReceita;
import com.ufsj.projetovaca.financeiro.domainLayer.models.Receita;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.ReceitaRepository;
@Service
public class CadastroReceitaService {
	
	
	@Autowired
	ReceitaRepository receitaRepository;
	
	
	@Autowired
	ReceitaAssembler receitaAssembler;
	
	@Autowired
	PodeCriarReceita podeCriarReceita;
	
	public ReceitaOutput criar(ReceitaInput receitaInput) throws NotFound {
		
		
		if(!podeCriarReceita.execute(receitaInput.getIdTipo())) {
			
			throw new NotFound("Não foi encontrado um tipo com o id informado");
		}
		
		Receita receita = receitaAssembler.converterEntidade(receitaInput);
		
		Receita novaReceita = receitaRepository.save(receita);
		
		ReceitaOutput receitaOutput = receitaAssembler.converterOutput(novaReceita);
		
		return receitaOutput;	
		
	}
	public ReceitaOutput deletar(Long idReceita) throws NotFound {
		
		Optional<Receita> opReceita = receitaRepository.findById(idReceita);
		
		if(opReceita.isEmpty()) {
			throw new NotFound("Não foi encontrada uma receita com o id informado");
			
		}
		
		Receita receita = opReceita.get();
		
		receitaRepository.delete(receita);
		
		ReceitaOutput receitaOutput = receitaAssembler.converterOutput(receita);
		
		return receitaOutput;
		
	}
	public ReceitaOutput atualizar(ReceitaInput receitaInput,Long idReceita) throws NotFound {
		
		if(!podeCriarReceita.execute(receitaInput.getIdTipo())) {
			
			throw new NotFound("Não foi encontrado um tipo com o id informado");
		}
		
		Optional<Receita> opReceita = receitaRepository.findById(idReceita);
		
		if(opReceita.isEmpty()) {
			throw new NotFound("Não foi encontrada uma receita com o id informado.");
			
		}
		
		Receita receita = opReceita.get();
		
		Receita receitaAtt = receitaAssembler.converterEntidade(receitaInput);
		
		BeanUtils.copyProperties(receitaAtt, receita,"id");
		
		Receita novaReceita = receitaRepository.save(receita);
		
		ReceitaOutput receitaOutput = receitaAssembler.converterOutput(novaReceita);
		
		return receitaOutput;
		
	}
	public List<ReceitaOutput> listar() {
		
		List<Receita> receitas = receitaRepository.findAll();
		
		List<ReceitaOutput> receitasOutput = receitaAssembler.converterColecaoOutput(receitas);
		
		return receitasOutput;
		
	}
	
	public ReceitaOutput encontrar(Long idReceita) throws NotFound {
		
		Optional<Receita> opReceita = receitaRepository.findById(idReceita);
		
		if(opReceita.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma receita com o id informado");
			
		}
		
		Receita receita = opReceita.get();
		
		ReceitaOutput receitaOutput = receitaAssembler.converterOutput(receita);
		
		return receitaOutput;
		
	}
}

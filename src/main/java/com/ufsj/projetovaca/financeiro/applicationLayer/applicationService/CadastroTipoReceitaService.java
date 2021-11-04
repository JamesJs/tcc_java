package com.ufsj.projetovaca.financeiro.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoReceitaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoReceitaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;
import com.ufsj.projetovaca.financeiro.applicationLayer.assembler.TipoReceitaAssembler;
import com.ufsj.projetovaca.financeiro.domainLayer.domainServices.PodeDeletarTipoReceita;
import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoReceita;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.TipoReceitaRepository;
@Service
public class CadastroTipoReceitaService {
	@Autowired
	TipoReceitaRepository	tipoReceitaRepository;
	
	@Autowired
	TipoReceitaAssembler tipoReceitaAssembler;
	
	@Autowired
	PodeDeletarTipoReceita podeDeletarTipoReceita;
	
	
	public TipoReceitaOutput criar(TipoReceitaInput TipoReceitaInput) throws NotFound {
		
		TipoReceita tipoReceita = tipoReceitaAssembler.converterEntidade(TipoReceitaInput);
		
		TipoReceita novoTipoReceita = tipoReceitaRepository.save(tipoReceita);
		
		TipoReceitaOutput tipoReceitaOutput = tipoReceitaAssembler.converterOutput(novoTipoReceita);
		
		return tipoReceitaOutput;	
		
	}
	public TipoReceitaOutput deletar(Long idTipoReceita) throws NotFound {
		
		Optional<TipoReceita> opTipoReceita = tipoReceitaRepository.findById(idTipoReceita);
		
		if(opTipoReceita.isEmpty()) {
			throw new NotFound("Não foi encontrada um tipo de receita com o id informado");
			
		}
		
		
		TipoReceita tipoReceita = opTipoReceita.get();
		
		if(!podeDeletarTipoReceita.execute(tipoReceita.getId())) {
			throw new NotFound("Existe uma receita relacionada com o tipo informado.");
		}
		
		tipoReceitaRepository.delete(tipoReceita);
		
		TipoReceitaOutput tipoReceitaOutput = tipoReceitaAssembler.converterOutput(tipoReceita);
		
		return tipoReceitaOutput;
		
	}
	public TipoReceitaOutput atualizar(TipoReceitaInput tipoReceitaInput,Long idTipoReceita) throws NotFound {
		
		
		Optional<TipoReceita> opTipoReceita = tipoReceitaRepository.findById(idTipoReceita);
		
		if(opTipoReceita.isEmpty()) {
			throw new NotFound("Não foi encontrada um tipo de receita com o id informado.");
			
		}
		
		TipoReceita tipoReceita = opTipoReceita.get();
		
		TipoReceita tipoReceitaAtt = tipoReceitaAssembler.converterEntidade(tipoReceitaInput);
		
		BeanUtils.copyProperties(tipoReceitaAtt, tipoReceita,"id");
		
		TipoReceita novoTipoReceita = tipoReceitaRepository.save(tipoReceita);
		
		TipoReceitaOutput tipoReceitaOutput = tipoReceitaAssembler.converterOutput(novoTipoReceita);
		
		return tipoReceitaOutput;
		
	}
	public List<TipoReceitaOutput> listar() {
		
		List<TipoReceita> tiposConta = tipoReceitaRepository.findAll();
		
		List<TipoReceitaOutput> tiposContaOutput = tipoReceitaAssembler.converterColecaoOutput(tiposConta);
		
		return tiposContaOutput;
		
	}
	
	public TipoReceitaOutput encontrar(Long idTipoReceita) throws NotFound {
		
		Optional<TipoReceita> opTipoReceita = tipoReceitaRepository.findById(idTipoReceita);
		
		if(opTipoReceita.isEmpty()) {
			
			throw new NotFound("Não foi encontrada um tipo de receita com o id informado.");
			
		}
		
		TipoReceita tipoReceita = opTipoReceita.get();
		
		TipoReceitaOutput tipoReceitaOutput = tipoReceitaAssembler.converterOutput(tipoReceita);
		
		return tipoReceitaOutput;
		
	}
}

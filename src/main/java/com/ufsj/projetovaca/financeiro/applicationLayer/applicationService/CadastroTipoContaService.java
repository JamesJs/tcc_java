package com.ufsj.projetovaca.financeiro.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoContaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoContaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;
import com.ufsj.projetovaca.financeiro.applicationLayer.assembler.TipoContaAssembler;
import com.ufsj.projetovaca.financeiro.domainLayer.domainServices.PodeDeletarTipoCompra;
import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoConta;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.TipoContaRepository;
@Service
public class CadastroTipoContaService {
	@Autowired
	TipoContaRepository	tipoContaRepository;
	
	@Autowired
	TipoContaAssembler tipoContaAssembler;
	
	@Autowired
	PodeDeletarTipoCompra podeDeletarTipoCompra;
	
	
	public TipoContaOutput criar(TipoContaInput tipoContaInput) throws NotFound {
		
		TipoConta tipoConta = tipoContaAssembler.converterEntidade(tipoContaInput);
		
		TipoConta novoTipoConta = tipoContaRepository.save(tipoConta);
		
		TipoContaOutput tipoContaOutput = tipoContaAssembler.converterOutput(novoTipoConta);
		
		return tipoContaOutput;	
		
	}
	public TipoContaOutput deletar(Long idTipoConta) throws NotFound {
		
		Optional<TipoConta> opTipoConta = tipoContaRepository.findById(idTipoConta);
		
		if(opTipoConta.isEmpty()) {
			throw new NotFound("Não foi encontrada uma conta com o id informado");
			
		}
		
		
		TipoConta tipoConta = opTipoConta.get();
		
		if(!podeDeletarTipoCompra.execute(tipoConta.getId())) {
			throw new NotFound("Existe uma conta relacionada com o tipo informado.");
		}
		
		tipoContaRepository.delete(tipoConta);
		
		TipoContaOutput tipoContaOutput = tipoContaAssembler.converterOutput(tipoConta);
		
		return tipoContaOutput;
		
	}
	public TipoContaOutput atualizar(TipoContaInput tipoContaInput,Long idTipoConta) throws NotFound {
		
		
		Optional<TipoConta> opTipoConta = tipoContaRepository.findById(idTipoConta);
		
		if(opTipoConta.isEmpty()) {
			throw new NotFound("Não foi encontrada uma conta com o id informado.");
			
		}
		
		TipoConta tipoConta = opTipoConta.get();
		
		TipoConta tipoContaAtt = tipoContaAssembler.converterEntidade(tipoContaInput);
		
		BeanUtils.copyProperties(tipoContaAtt, tipoConta,"id");
		
		TipoConta novoTipoConta = tipoContaRepository.save(tipoConta);
		
		TipoContaOutput tipoContaOutput = tipoContaAssembler.converterOutput(novoTipoConta);
		
		return tipoContaOutput;
		
	}
	public List<TipoContaOutput> listar() {
		
		List<TipoConta> tiposConta = tipoContaRepository.findAll();
		
		List<TipoContaOutput> tiposContaOutput = tipoContaAssembler.converterColecaoOutput(tiposConta);
		
		return tiposContaOutput;
		
	}
	
	public TipoContaOutput encontrar(Long idTipoConta) throws NotFound {
		
		Optional<TipoConta> opTipoConta = tipoContaRepository.findById(idTipoConta);
		
		if(opTipoConta.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma conta com o id informado");
			
		}
		
		TipoConta tipoConta = opTipoConta.get();
		
		TipoContaOutput tipoContaOutput = tipoContaAssembler.converterOutput(tipoConta);
		
		return tipoContaOutput;
		
	}
}

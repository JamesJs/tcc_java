package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.applicationLayer.exceptions.ExisteAnimalPertencenteAoLote;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.LoteAssembler;
import com.ufsj.projetovaca.animal.domainLayer.domainServices.ValidaSePodeDeletarLote;
import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
import com.ufsj.projetovaca.animal.domainLayer.repositories.LoteRepository;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;


@Service
public class CadastroLote {
	
	
	@Autowired
	LoteRepository loteRepository;
	
	@Autowired
	LoteAssembler loteAssembler;
	
	@Autowired
	ValidaSePodeDeletarLote validaSePodeDeletarLote;
	
	
	
	public LoteOutput cadastrar(LoteInput loteInput) {
		
		Lote lote = loteAssembler.converterEntidade(loteInput);
		
		Lote novoLote = loteRepository.save(lote);
		
		LoteOutput loteOutput = loteAssembler.converterOutput(novoLote);
		
		return loteOutput;
		
	}
	
	public List<LoteOutput> listar() {
		
		List<Lote> lotes = loteRepository.findAll();
		
		List<LoteOutput> lotesOutput = loteAssembler.converterColecaoOutput(lotes);
		
		return lotesOutput;	
		
	}
	
	public LoteOutput deletar(long idLote) throws ExisteAnimalPertencenteAoLote, NotFoundWithId {
		
		Optional<Lote> opLote = loteRepository.findById(idLote);
		
		if(opLote.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um lote com o id informado");
		}
		
		
		if(!validaSePodeDeletarLote.execute(idLote)) {
			
			throw new ExisteAnimalPertencenteAoLote("Existe um animal que pertence a este lote");
			
		}
		
		Lote lote = opLote.get();
		
		loteRepository.delete(lote);
		
		LoteOutput loteOutput = loteAssembler.converterOutput(lote);
		
		return loteOutput;
			
	}
	public LoteOutput atualizar(Long id, LoteInput loteInput) throws NotFoundWithId {
		
		Optional<Lote> opLote = loteRepository.findById(id);
		
		if(opLote.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um lote com o id informado");
			
		}
		
		Lote lote = opLote.get();
		
		Lote loteAtt = loteAssembler.converterEntidade(loteInput);
		
		BeanUtils.copyProperties(loteAtt, lote,"id");
		
		Lote novoLote = loteRepository.save(lote);
		
		LoteOutput loteOutput = loteAssembler.converterOutput(novoLote);
		
		return loteOutput;
		
	}
}

package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.ProducaoLeiteAssembler;
import com.ufsj.projetovaca.animal.domainLayer.domainServices.CalculaValorTotalProducaoLeite;
import com.ufsj.projetovaca.animal.domainLayer.domainServices.ValidaExisteAnimal;
import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
import com.ufsj.projetovaca.animal.domainLayer.repositories.ProducaoLeiteRepository;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundWithId;

@Service
public class CadastroProducaoLeite {
	
	@Autowired
	ProducaoLeiteAssembler producaoLeiteAssembler;
	
	@Autowired
	ProducaoLeiteRepository producaoLeiteRepository;
	
	@Autowired
	CalculaValorTotalProducaoLeite calculaValorTotalProducaoLeite;
	
	@Autowired
	ValidaExisteAnimal validaExisteAnimal;
	
	public ProducaoLeiteOutput criar(ProducaoLeiteInput producaoLeiteInput) throws NotFoundWithId {
		
		if(!validaExisteAnimal.execute(producaoLeiteInput.getIdAnimal())) {
			
			throw new NotFoundWithId("Não foi encontrado animal com o id informado");
			
		}
		
		ProducaoLeite producaoLeite = producaoLeiteAssembler.converterEntidade(producaoLeiteInput);
		
		ProducaoLeite novaProducaoLeite = producaoLeiteRepository.save(producaoLeite);
		
		ProducaoLeiteOutput producaoLeiteOutput = producaoLeiteAssembler.converterOutput(novaProducaoLeite);
		
		return producaoLeiteOutput;
		
	}
	
	public HashMap<String, Object> listar(){
		
		List<ProducaoLeite> producoesLeite = producaoLeiteRepository.findAll();
		
		List<ProducaoLeiteOutput> producoesLeiteOutput = producaoLeiteAssembler.converterColecaoOutput(producoesLeite);
		
		HashMap<String, Object> retorno = new HashMap<String, Object>();
		
		float total = calculaValorTotalProducaoLeite.executar(producoesLeite);
		
		retorno.put("Total", total);
		
		retorno.put("ProducoesLeite", producoesLeiteOutput);
		
		return retorno;
		
	}
	
	public ProducaoLeiteOutput deletar(long idProducaoLeite) throws NotFoundWithId {
		
		Optional<ProducaoLeite> opProducaoLeite = producaoLeiteRepository.findById(idProducaoLeite);
		
		if(opProducaoLeite.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrada produção com o id informado");
			
		}
		
		ProducaoLeite producaoLeite = opProducaoLeite.get();
		
		producaoLeiteRepository.delete(producaoLeite);
		
		ProducaoLeiteOutput producaoLeiteOutput = producaoLeiteAssembler.converterOutput(producaoLeite);
		
		return producaoLeiteOutput;
		
	}
	
	public ProducaoLeiteOutput atualizar(Long idProducaoLeite,ProducaoLeiteInput producaoLeiteInput) throws NotFoundWithId {
		
		Optional<ProducaoLeite> opProducaoLeite = producaoLeiteRepository.findById(idProducaoLeite);
		
		if(opProducaoLeite.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado uma produção com o id informado");
			
		}
		
		if(!validaExisteAnimal.execute(producaoLeiteInput.getIdAnimal())) {
			
			throw new NotFoundWithId("Não foi encontrado animal com o id informado");
		}
		
		
		ProducaoLeite producaoLeite = opProducaoLeite.get();
		
		ProducaoLeite producaoLeiteAtt = producaoLeiteAssembler.converterEntidade(producaoLeiteInput);
		
		
		BeanUtils.copyProperties(producaoLeiteAtt, producaoLeite,"id");
		
		ProducaoLeite novaProducaoLeite = producaoLeiteRepository.save(producaoLeite);
		
		ProducaoLeiteOutput producaoLeiteOutput = producaoLeiteAssembler.converterOutput(novaProducaoLeite);
		
		return producaoLeiteOutput;
		
		
		
	}
	
	
}

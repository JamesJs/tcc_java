package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers.MateriaPrimaAssembler;
import com.ufsj.projetovaca.fazenda.domainLayer.models.MateriaPrima;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.MateriaPrimaRepository;
@Service
public class CadastroMateriaPrima {
	
	@Autowired
	ExisteTipoDeMateriaPrima existeTipoDeMateriaPrima;
	
	
	@Autowired
	MateriaPrimaRepository materiaPrimaRepository;
	
	@Autowired
	MateriaPrimaAssembler materiaPrimaAssembler;
	
	public MateriaPrimaOutput salvar(MateriaPrimaInput materiaPrimaInput) {
		
		
		
		MateriaPrima materiaPrima = existeTipoDeMateriaPrima.execute(materiaPrimaInput.getTipo());
		
		
		
		if(materiaPrima != null) {
			
			materiaPrima.setIsNaoUtilizado(false);
							
			materiaPrima = materiaPrimaRepository.save(materiaPrima);
			
			MateriaPrimaOutput materiaPrimaOutput = materiaPrimaAssembler.converterOutput(materiaPrima);
			
			return materiaPrimaOutput;
		}
		
		
		
		MateriaPrima novaMateriaPrima = materiaPrimaAssembler.converterEntidade(materiaPrimaInput);
		
		novaMateriaPrima.setIsNaoUtilizado(false);
		
		materiaPrimaRepository.save(novaMateriaPrima);
		
		MateriaPrimaOutput materiaPrimaOutput = materiaPrimaAssembler.converterOutput(novaMateriaPrima);
		
		return materiaPrimaOutput;
		
	}
	
	public MateriaPrimaOutput deletar(long id) throws NotFoundWithId {
		Optional<MateriaPrima> opMateriaPrima  =  materiaPrimaRepository.findById(id);
		
		if(opMateriaPrima.isEmpty()) {
			
			throw new NotFoundWithId("Matéria prima não encontrada para o id");
			
		}
			
		
		MateriaPrima materiaPrima = opMateriaPrima.get();
		
		materiaPrima.setIsNaoUtilizado(!materiaPrima.getIsNaoUtilizado());
		
		MateriaPrima novaMateriaPrima  = materiaPrimaRepository.save(materiaPrima);
		
		MateriaPrimaOutput materiaPrimaOutput = materiaPrimaAssembler.converterOutput(novaMateriaPrima);
		
		return materiaPrimaOutput;
		
	}
}

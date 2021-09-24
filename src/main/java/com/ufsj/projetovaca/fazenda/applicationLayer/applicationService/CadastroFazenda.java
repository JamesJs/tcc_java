package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.AssemblerAdapter;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.Conversores;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FazendaRepository;


@Service
public class CadastroFazenda {
	
	@Autowired
	FazendaRepository fazendaRepository;
	
	Conversores<FazendaInput, FazendaOutput, Fazenda> conversores = 
			new Conversores<FazendaInput, FazendaOutput, Fazenda>();
	
	AssemblerAdapter<Fazenda, FazendaInput> conversorEntidade = conversores.criarConversorEntidade(Fazenda.class);
	
	AssemblerAdapter<FazendaOutput, Fazenda> conversorOutput = conversores.criarConversorOutput(FazendaOutput.class);
	
	
	
	public FazendaOutput salvar(FazendaInput fazendaInput) {
		Fazenda fazenda = conversorEntidade.converterUnitario(fazendaInput);
		
		
		fazenda.setVendida(false);
		
		FazendaOutput fazendaOutput = conversorOutput.converterUnitario(fazendaRepository.save(fazenda));
		
		return fazendaOutput;
		
	}
	public FazendaOutput deletar(Long id) throws NotFoundWithId {
		
		Optional<Fazenda> opFazenda = fazendaRepository.findById(id);
		
		if(opFazenda.isEmpty()) {
			
			throw new NotFoundWithId("Fazenda não encontrada");
			
		}
		
		Fazenda fazenda = opFazenda.get();
		
		fazenda.setVendida(!fazenda.isVendida());
		
		FazendaOutput fazendaOutput = conversorOutput.converterUnitario(fazendaRepository.save(fazenda));
		
		return fazendaOutput;
	}
}

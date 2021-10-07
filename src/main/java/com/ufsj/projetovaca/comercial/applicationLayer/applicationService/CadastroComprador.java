package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.InvalidTipoDeComprador;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.assemblers.CompradorAssembler;
import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompradorRepository;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;


@Service
public class CadastroComprador {
	
	
	@Autowired
	CompradorAssembler compradorAssembler;
	
	@Autowired
	CompradorRepository compradorRepository;
	
	
	public List<CompradorOutput> listar(){
		
		List<Comprador> compradores = compradorRepository.findAll();
		
		return compradorAssembler.converterColecaoOutput(compradores);
		
	}
	
	public CompradorOutput criar(CompradorInput compradorInput) throws InvalidTipoDeComprador {
		
		Comprador comprador = compradorAssembler.converterEntidade(compradorInput);
		
		comprador.setAtivo(true);
		
		boolean tipoCompradorValido = comprador.definirComprador(compradorInput.getTipoComprador());
		
		if(!tipoCompradorValido) {
			
			throw new InvalidTipoDeComprador("Tipo de comprador informado inválido");
			
		}
		
		Comprador novoComprador = compradorRepository.save(comprador);
		
		CompradorOutput compradorOutput = compradorAssembler.converterOutput(novoComprador);
		
		compradorOutput.setTipoComprador(novoComprador.getTipoComprador().toString());
		
		return compradorOutput;
		
	}
	
	public CompradorOutput desativar(Long id) throws NotFoundWithId {
		
		Optional<Comprador> opComprador = compradorRepository.findById(id);
		
		if(opComprador.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado comprador com esse id");
			
		}
		
		Comprador comprador = opComprador.get();
		
		comprador.setAtivo(!comprador.isAtivo());
		
		Comprador novoComprador = compradorRepository.save(comprador);
		
		CompradorOutput compradorOutput = compradorAssembler.converterOutput(novoComprador);
		
		return compradorOutput;
		
		
	}
	
	public CompradorOutput atualizar(CompradorInput compradorInput, Long id) throws NotFoundWithId, InvalidTipoDeComprador {
		
		Optional<Comprador> opComprador = compradorRepository.findById(id);
		
		if(opComprador.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado comprador com esse id");
			
		}
		
		Comprador comprador = opComprador.get();
		
		Comprador novoComprador = compradorAssembler.converterEntidade(compradorInput);
		
		boolean isTipoCompradorValido = comprador.definirComprador(compradorInput.getTipoComprador());
		
		if(!isTipoCompradorValido) {
			
			throw new InvalidTipoDeComprador("Tipo de comprador informado inválido");
			
		}
		
		BeanUtils.copyProperties(novoComprador, comprador,"id","ativo","tipoComprador");
		
		return compradorAssembler.converterOutput(compradorRepository.save(comprador));		
		
	}
	
}

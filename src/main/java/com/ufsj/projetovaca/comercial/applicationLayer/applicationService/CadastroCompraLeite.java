package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteOutput;
import com.ufsj.projetovaca.comercial.domainLayer.domainServices.PodeCadastrarCompraLeite;
import com.ufsj.projetovaca.comercial.domainLayer.models.CompraLeite;
import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompraLeiteRepository;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompradorRepository;
import com.ufsj.projetovaca.comercial.infraLayer.assembler.AssemblerAdapter;
import com.ufsj.projetovaca.comercial.infraLayer.assembler.Conversores;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.CopiarAtributos;



@Service
public class CadastroCompraLeite {
	
	
	@Autowired
	CompraLeiteRepository compraLeiteRepository;
	
	@Autowired
	PodeCadastrarCompraLeite podeCadastrarCompraLeite;
	
	@Autowired
	CompradorRepository compradorRepository;
	
	
	
	Conversores<CompraLeiteInput, CompraLeiteOutput, CompraLeite> conversores = 
			new Conversores<CompraLeiteInput, CompraLeiteOutput, CompraLeite>();
	
	AssemblerAdapter<CompraLeite, CompraLeiteInput> conversorEntidade = 
			conversores.criarConversorEntidade(CompraLeite.class);
	
	AssemblerAdapter<CompraLeiteOutput, CompraLeite> conversorOutput = 
			conversores.criarConversorOutput(CompraLeiteOutput.class);
	
	public List<CompraLeiteOutput> listar(){
		
		List<CompraLeite> comprasLeite = compraLeiteRepository.findAll();
		
		List<CompraLeiteOutput> comprasLeiteOutput = conversorOutput.converterColecao(comprasLeite);
		
		return comprasLeiteOutput;
		
	}
	
	public CompraLeiteOutput criar(CompraLeiteInput compraLeiteInput) throws NotFoundWithId {
		
		CompraLeite compraLeite = conversorEntidade.converterUnitario(compraLeiteInput);
		
		if(!podeCadastrarCompraLeite.execute(compraLeiteInput.getIdComprador())) {
			
			throw new NotFoundWithId("Não foi encontrado comprador com esse id");
			
		}
		
		compraLeite.setCancelado(false);
		
		CompraLeite novaCompraLeite = compraLeiteRepository.save(compraLeite);
		
		CompraLeiteOutput compraLeiteOutput = conversorOutput.converterUnitario(novaCompraLeite);
		
		return compraLeiteOutput;
		
	}
	
	public CompraLeiteOutput desativar(long idCompraLeite) throws NotFoundWithId {
		
		Optional<CompraLeite> opCompraLeite = compraLeiteRepository.findById(idCompraLeite);
		
		if(opCompraLeite.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrada uma compra com esse id");
		
		}
		
		CompraLeite compraLeite = opCompraLeite.get();
		
		compraLeite.setCancelado(!compraLeite.isCancelado());
		
		CompraLeite novaCompraLeite = compraLeiteRepository.save(compraLeite);
		
		CompraLeiteOutput compraLeiteOutput = conversorOutput.converterUnitario(novaCompraLeite);
		
		return compraLeiteOutput;
		
	}
	
	public CompraLeiteOutput atualizar(long idCompraLeite,CompraLeiteInput compraLeiteInput) throws NotFoundWithId {
		
		CompraLeite attCompraLeite = conversorEntidade.converterUnitario(compraLeiteInput);
		
		Optional<CompraLeite> opCompraLeite = compraLeiteRepository.findById(idCompraLeite);
		
		if(opCompraLeite.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrada uma compra com esse id");
			
		}
		
		CompraLeite compraLeite = opCompraLeite.get();
		
		if(compraLeite.getCompradorId() != attCompraLeite.getCompradorId()) {
			
			long idComprador = attCompraLeite.getCompradorId();
			
			Optional<Comprador> opComprador = compradorRepository.findById(idComprador);
			
			if(opComprador.isEmpty()) {
				
				throw new NotFoundWithId("Comprador não foi encontrado com o id informado");
				
			}
			
		}	
		
		BeanUtils.copyProperties(attCompraLeite, compraLeite,"id");
		
		CompraLeite novaCompraLeite = compraLeiteRepository.save(compraLeite);
		
		CompraLeiteOutput compraLeiteOutput = conversorOutput.converterUnitario(novaCompraLeite);
		
		return compraLeiteOutput;	
		
	}
}

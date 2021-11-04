package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendaGadoInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendaGadoOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.assemblers.VendaGadoAssembler;
import com.ufsj.projetovaca.comercial.domainLayer.models.VendaGado;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.VendaGadoRepository;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;

@Service
public class CadastroVendaGado {
	
	@Autowired
	VendaGadoRepository vendaGadoRepository; 
	
	@Autowired
	VendaGadoAssembler vendaGadoAssembler;
	
	@Transactional("comercialTransactionManager")
	
	public List<VendaGadoOutput> listar(){
		
		List<VendaGado> vendasGado = vendaGadoRepository.findAll();
		List<VendaGadoOutput> vendasGadoOutput = vendaGadoAssembler.converterColecaoOutput(vendasGado);
		return vendasGadoOutput;
	
	}
	
	public VendaGadoOutput encontrar(Long id) throws NotFound{
		
		Optional<VendaGado> opVendaGado = vendaGadoRepository.findById(id);
		
		if(opVendaGado.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma venda com o id informado");
			
		}
		
		VendaGado vendaGado = opVendaGado.get();
		
		VendaGadoOutput vendaGadoOutput = vendaGadoAssembler.converterOutput(vendaGado);
		return vendaGadoOutput;
	
	}
	
	public VendaGadoOutput atualizar(Long id, VendaGadoInput vendaGadoInput) throws NotFound {
		
		Optional<VendaGado> opVendaGado = vendaGadoRepository.findById(id);
		
		if(opVendaGado.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma venda com o id informado");
			
		}
		
		VendaGado vendaGado = opVendaGado.get();
		
		VendaGado vendaGadoAtt = vendaGadoAssembler.converterEntidade(vendaGadoInput);
		
		BeanUtils.copyProperties(vendaGadoAtt, vendaGado,"id","isCancelado","cancelado");
		
		VendaGadoOutput vendaGadoOutput = vendaGadoAssembler.converterOutput(vendaGado);
		
		return vendaGadoOutput;
		
		
	}
	
	public VendaGadoOutput deletar(Long id) throws NotFound {
		
		Optional<VendaGado> opVendaGado = vendaGadoRepository.findById(id);
		
		if(opVendaGado.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma venda com o id informado");
			
		}
		
		VendaGado vendaGado = opVendaGado.get();
		
		vendaGadoRepository.delete(vendaGado);
		
		VendaGadoOutput vendaGadoOutput = vendaGadoAssembler.converterOutput(vendaGado);
		
		return vendaGadoOutput;	
		
	}
	public VendaGadoOutput cancelar(Long id) throws NotFound {
		
		Optional<VendaGado> opVendaGado = vendaGadoRepository.findById(id);
		
		if(opVendaGado.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma venda com o id informado");
			
		}
		
		VendaGado vendaGado = opVendaGado.get();
		
		vendaGado.setCancelado(true);
		
		VendaGadoOutput vendaGadoOutput = vendaGadoAssembler.converterOutput(vendaGado);
		
		return vendaGadoOutput;	
		
	}
	public VendaGadoOutput ativar(Long id) throws NotFound {
		
		Optional<VendaGado> opVendaGado = vendaGadoRepository.findById(id);
		
		if(opVendaGado.isEmpty()) {
			
			throw new NotFound("Não foi encontrada uma venda com o id informado");
			
		}
		
		VendaGado vendaGado = opVendaGado.get();
		
		vendaGado.setCancelado(false);
		
		VendaGadoOutput vendaGadoOutput = vendaGadoAssembler.converterOutput(vendaGado);
		
		return vendaGadoOutput;	
		
	}
	
	public VendaGadoOutput criar(VendaGadoInput vendaGadoInput) throws NotFound {
		
		VendaGado vendaGado = vendaGadoAssembler.converterEntidade(vendaGadoInput);
		
		vendaGado.setCancelado(false);
		
		System.out.println(vendaGado);
		
		VendaGado novaVendaGado = vendaGadoRepository.save(vendaGado);
		
		VendaGadoOutput vendaGadoOutput = vendaGadoAssembler.converterOutput(novaVendaGado);
		
		return vendaGadoOutput;	
		
	}
}

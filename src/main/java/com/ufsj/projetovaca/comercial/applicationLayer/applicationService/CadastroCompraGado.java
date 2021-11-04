package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraGadoInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraGadoOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.assemblers.CompraGadoAssembler;
import com.ufsj.projetovaca.comercial.domainLayer.domainServices.PodeCadastrarCompraGado;
import com.ufsj.projetovaca.comercial.domainLayer.models.CompraGado;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompraGadoRepository;
import com.ufsj.projetovaca.config.Events;
@Service
public class CadastroCompraGado {
		
		@Autowired
		Events events;
	
	
		@Autowired
		CompraGadoRepository compraGadoRepository;
		
		@Autowired
		CompraGadoAssembler compraGadoAssembler;
		
		@Autowired
		PodeCadastrarCompraGado podeCadastrarCompraGado;
		
		public List<CompraGadoOutput> listar(){ 
			List<CompraGado> comprasGado = compraGadoRepository.findAll();
			List<CompraGadoOutput> comprasGadoOutput = compraGadoAssembler.converterColecaoOutput(comprasGado);
			return comprasGadoOutput;
		}
		@Transactional("comercialTransactionManager")
		public CompraGadoOutput criar(CompraGadoInput compraGadoInput) throws NotFoundWithId {
			
			if(!podeCadastrarCompraGado.execute(compraGadoInput.getIdComprador())) {
				
				throw new NotFoundWithId("Não foi encontrado um comprador com o id informado");
				
			}
			
			CompraGado compraGado = compraGadoAssembler.converterEntidade(compraGadoInput);
			
			compraGado.adicionaAnimaisCompra(compraGadoInput.getIdsAnimais());

			
			
			CompraGado novaCompraGado = compraGadoRepository.save(compraGado);
			
			
			CompraGadoOutput compraGadoOutput = compraGadoAssembler.converterOutput(novaCompraGado);
			
			events.vendeAnimaisGeraReceita(compraGadoInput.getIdsAnimais(),compraGado);
			
			return compraGadoOutput;
			
			
		}
		public CompraGadoOutput atualizar(CompraGadoInput compraGadoInput,Long idCompra) throws NotFoundWithId {
			if(podeCadastrarCompraGado.execute(compraGadoInput.getIdComprador())) {
				
				throw new NotFoundWithId("Não foi encontrado um comprador com o id informado");
				
			}
			
			CompraGado compraGado =  existeCompraGado(idCompra);
			
			CompraGado attCompraGado = compraGadoAssembler.converterEntidade(compraGadoInput);
			
			BeanUtils.copyProperties(attCompraGado, compraGado);
			
			CompraGado novaCompraGado = compraGadoRepository.save(compraGado);
			
			CompraGadoOutput compraGadoOutput = compraGadoAssembler.converterOutput(novaCompraGado);
			
			return compraGadoOutput;
			
			
		}
		
		public CompraGadoOutput deletar(Long idCompraGado) throws NotFoundWithId {
			
			CompraGado compraGado =  existeCompraGado(idCompraGado);
			
			compraGadoRepository.delete(compraGado);
			
			CompraGadoOutput compraGadoOutput = compraGadoAssembler.converterOutput(compraGado);
			
			return compraGadoOutput;
			
			
		}
		
		public CompraGadoOutput encontrar(Long idCompraGado) throws NotFoundWithId {
			
			CompraGado compraGado = existeCompraGado(idCompraGado);
			
			CompraGadoOutput compraGadoOutput = compraGadoAssembler.converterOutput(compraGado);
			
			return compraGadoOutput;
			
		}
		
		public CompraGadoOutput ativar(Long idCompraGado) throws NotFoundWithId {
			
			CompraGado compraGado = existeCompraGado(idCompraGado);
			
			compraGado.setCancelada(false);
			
			CompraGadoOutput compraGadoOutput = compraGadoAssembler.converterOutput(compraGado);
			
			return compraGadoOutput;
			
		}
		
		public CompraGadoOutput cancelar(Long idCompraGado) throws NotFoundWithId {
			
			CompraGado compraGado = existeCompraGado(idCompraGado);
			
			compraGado.setCancelada(true);
			
			CompraGadoOutput compraGadoOutput = compraGadoAssembler.converterOutput(compraGado);
			
			return compraGadoOutput;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		private CompraGado existeCompraGado(Long idCompra) throws NotFoundWithId {
			Optional<CompraGado> opCompraGado = compraGadoRepository.findById(idCompra);
			
			if(opCompraGado.isEmpty()) {
				
				throw new NotFoundWithId("Não foi encontrada uma compra com o id informado"); 
			
			}
			return opCompraGado.get();
		}
		
		
}

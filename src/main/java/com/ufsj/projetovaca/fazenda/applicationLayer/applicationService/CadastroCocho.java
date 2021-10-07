package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundCochoType;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.CochoNaMesmaLocalizacao;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.CochoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.CochoOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers.CochoAssembler;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Cocho;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;
import com.ufsj.projetovaca.fazenda.domainLayer.models.MateriaPrima;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FazendaRepository;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.MateriaPrimaRepository;
@Service
public class CadastroCocho {
	
	
	@Autowired
	CochoAssembler cochoAssembler;
	
	@Autowired
	FazendaRepository fazendaRepository;
	
	@Autowired
	MateriaPrimaRepository materiaPrimaRepository;
	
	@Autowired
	CochosDeUmaFazenda cochosDeUmaFazenda;
	
	@Transactional("fazendaTransactionManager")
	public CochoOutput salvar(long idFazenda,CochoInput cochoInput) throws NotFoundWithId,CochoNaMesmaLocalizacao, NotFoundCochoType {
		
		Cocho cocho = cochoAssembler.converterEntidade(cochoInput);

		if(!cocho.definirTipoCocho(cochoInput.getTipo())) {
			
			throw new NotFoundCochoType("Tipo de cocho inválido");
			
		}
		
		Optional<Fazenda> opFazenda  = fazendaRepository.findById(idFazenda);
		
		if(opFazenda.isEmpty()) {
		
			throw new NotFoundWithId("Fazenda não encontrada com o id");
		
		}
		
		Optional<MateriaPrima> opMateriaPrima = materiaPrimaRepository.findById(cocho.getCochoMateriaPrima().getIdMateriaPrima());
		
		if(opMateriaPrima.isEmpty() || !(opMateriaPrima.get().disponivelParaUso())) {
		
			throw new NotFoundWithId("Materia prima não encontrada");
		
		}	
		
		Fazenda fazenda = opFazenda.get();
		
		if(!(fazenda.podeAdicionarCocho(cocho.getLocalizacao()))) {
			
			throw new CochoNaMesmaLocalizacao("Não é possível cadastrar dois cochos na mesma localização");
		
		}
		
		Cocho novoCocho = new Cocho();
		
		BeanUtils.copyProperties(cocho, novoCocho,"id");
		
		fazenda.addCocho(novoCocho);
		
		fazendaRepository.save(fazenda);
		
		CochoOutput cochoOutput = cochoAssembler.converterOutput(fazenda.getCochos().get(fazenda.getCochos().size() - 1));
		
		cochoOutput.setIdFazenda(idFazenda);
		
		return cochoOutput;
	
		
	}
	
	public List<CochoOutput> listarCochosPorFazenda(long idFazenda) throws NotFoundWithId{
		List<CochoOutput> cochosOutput = cochosDeUmaFazenda.execute(idFazenda);
		
		if(cochosOutput == null) {
		
			throw new NotFoundWithId("Não foi encontrada fazenda com esse id");
		}
		
		return cochosOutput;
		
	}
	@Transactional("fazendaTransactionManager")
	public CochoOutput removerCocho(long idFazenda,long idCocho) throws NotFoundWithId {
		
		Optional<Fazenda> opFazenda = fazendaRepository.findById(idFazenda);
		
		if(opFazenda.isEmpty()) {
			throw new NotFoundWithId("Fazenda não encontrada para o id");
		}
		
		Fazenda fazenda = opFazenda.get();
		
		Cocho cocho = fazenda.removerCocho(idCocho);
		
		if(cocho == null) {
			throw new NotFoundWithId("Cocho não encontrado com o id");
		}
		
		fazendaRepository.save(fazenda);
		
		CochoOutput cochoOutput = cochoAssembler.converterOutput(cocho);
		
		return cochoOutput;
		
		
	}
	@Transactional("fazendaTransactionManager")
	public CochoOutput atualizarCocho(long idFazenda,long idCocho,Cocho attCocho) throws NotFoundWithId {
		
		Optional<Fazenda> opFazenda = fazendaRepository.findById(idFazenda);
		
		
		
		if(opFazenda.isEmpty()) {
			
			throw new NotFoundWithId("não foi encontrada fazenda com esse id");
		
		}
		
		Fazenda fazenda = opFazenda.get();
		
		Cocho cocho = fazenda.alterarCocho(idCocho, attCocho);
		
		if(cocho == null) {
			
			throw new NotFoundWithId("não foi encontrado cocho com esse id");
		
		}
		
		fazendaRepository.save(fazenda);
		
		CochoOutput cochoOutput = cochoAssembler.converterOutput(cocho);
		
		return cochoOutput;	
		
	}
}

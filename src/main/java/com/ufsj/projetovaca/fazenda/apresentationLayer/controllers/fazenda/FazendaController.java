package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.fazenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroFazenda;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FazendaRepository;

@RequestMapping("/fazenda")
@RestController
public class FazendaController extends ICrudController<FazendaInput, FazendaOutput, Fazenda> {
	
	
	Class<FazendaOutput> Output = FazendaOutput.class;
	
	@Autowired
	FazendaRepository fazendaRepository;
	
	@Autowired
	CadastroFazenda cadastroFazenda;
	
	public FazendaController() {
		super();
		criarConversores(Output);
	}
	
	
	@GetMapping
	@Override
	public ResponseEntity<?> listar() {
		
		return listarTodas(fazendaRepository);
		
		
	}
	
	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody FazendaInput fazendaInput) {
			
		try {
			
			
			FazendaOutput fazendaOutput = cadastroFazenda.salvar(fazendaInput);
			
			return RespostaStatus(HttpStatus.OK, fazendaOutput);
		
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

	
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		try {
			
			FazendaOutput fazendaOutput = cadastroFazenda.deletar(id);
			
			return RespostaStatus(HttpStatus.OK, fazendaOutput);
			
		
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}
	
	
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<?> atualizar(@RequestBody FazendaInput fazendaInput,@PathVariable Long id) {
		try {
		
			return atualizar(fazendaRepository, id, fazendaInput, "idFazenda");
		
		}catch(Exception e) {
		
			return erroServidor(e);
		
		}
		
		
	}

}

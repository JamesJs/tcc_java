package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.materiaPrima;

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

import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroMateriaPrima;
import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.ExisteTipoDeMateriaPrima;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.models.MateriaPrima;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.MateriaPrimaRepository;



@RestController
@RequestMapping("materiaPrima")
public class MateriaPrimaController extends ICrudController<MateriaPrimaInput, MateriaPrimaOutput,MateriaPrima> {
	
	
	Class<MateriaPrimaOutput> Output = MateriaPrimaOutput.class;
		
	public MateriaPrimaController() {
		
		super();
		
		super.criarConversores(Output);
	}
	
	
	@Autowired
	MateriaPrimaRepository materiaPrimaRepository;
	
	
	@Autowired
	ExisteTipoDeMateriaPrima existeMateriaPrima;
	
	@Autowired
	CadastroMateriaPrima cadastroMateriaPrima;
	
	@GetMapping
	@Override
	public ResponseEntity<?> listar() {
		try {
			
			return listarTodas(materiaPrimaRepository);
		
		}catch(Exception e) {
			return erroServidor(e);
			
		}
		
	}

	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody MateriaPrimaInput materiaPrimaInput) {
		
		try {
		
			MateriaPrimaOutput materiaPrimaOutput = cadastroMateriaPrima.salvar(materiaPrimaInput);
				
			return RespostaStatus(HttpStatus.OK, materiaPrimaOutput);
		
		}catch(Exception e) {
			
			return erroServidor(e);
		
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		
		try {
			
			MateriaPrimaOutput materiaPrimaOutput = cadastroMateriaPrima.deletar(id);
			
			return RespostaStatus(HttpStatus.OK, materiaPrimaOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
		
		}
		
	
	}
	
	
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<?> atualizar(@RequestBody MateriaPrimaInput materiaPrimaInput,@PathVariable Long id) {
				
		try {
			
			return atualizar(materiaPrimaRepository, id, materiaPrimaInput, "idMateriaPrima");
		
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}
	
}

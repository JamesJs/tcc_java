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

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroMateriaPrima;
import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.ExisteTipoDeMateriaPrima;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.models.MateriaPrima;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.MateriaPrimaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping(path = "materiaPrima",produces = "application/json")
@Api(tags = {"Materia Prima"},description = "Endpoints relacionados às matérias primas")
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
	@ApiOperation(value = "Lista todas as matérias primas cadastradas no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna as matérias primas cadastradas. É um array.", response = MateriaPrimaOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {
		try {
			
			return listarTodas(materiaPrimaRepository);
		
		}catch(Exception e) {
			return erroServidor(e);
			
		}
		
	}

	@PostMapping
	@Override
	@ApiOperation(value = "Cadastra uma nova matéria prima no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a matéria prima criada", response = MateriaPrimaOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> criar(@RequestBody MateriaPrimaInput materiaPrimaInput) {
		
		try {
		
			MateriaPrimaOutput materiaPrimaOutput = cadastroMateriaPrima.salvar(materiaPrimaInput);
				
			return RespostaStatus(HttpStatus.OK, materiaPrimaOutput);
		
		}catch(Exception e) {
			
			return erroServidor(e);
		
		}
		
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a matéria prima não mais utilizada", response = MateriaPrimaOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Declara que uma matéria prima não será mais utilizada para criação de cochos.")
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
	
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a matéria prima atualizada.", response = MateriaPrimaOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza atualização a todos os parâmetros de uma matéria prima.")
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

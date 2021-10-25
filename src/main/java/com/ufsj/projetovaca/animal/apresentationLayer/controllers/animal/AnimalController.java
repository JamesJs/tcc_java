package com.ufsj.projetovaca.animal.apresentationLayer.controllers.animal;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.AcrescentarIdaAoCochoAnimal;
import com.ufsj.projetovaca.animal.applicationLayer.applicationService.CadastroAnimal;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundAtributo;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundCochoType;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInputAtualizacao;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundWithId;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path="/animal",produces="application/json")
@Api(tags = {"Animal"},description = "Endpoints relacionados aos animais")
public class AnimalController extends ACrudController<AnimalInput, AnimalOutput, Animal> {
	
	
	@Autowired
	CadastroAnimal cadastroAnimal;
	
	@Autowired
	AcrescentarIdaAoCochoAnimal acrescentarIdaAoCocho;
	
	@GetMapping(produces = "application/json")
	@Override
	@ApiOperation(value = "Lista todos os animais cadastrados no sistema.",tags = {"Animal"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna os animais cadastrados. É um array.", response = AnimalOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {
		try {
			
			List<AnimalOutput> animaisOutput = cadastroAnimal.listar();
			
			return respostaStatus(HttpStatus.OK, animaisOutput);		
		
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}

		
	}
	@PostMapping(produces = "application/json")
	@Override
	@ApiOperation(value = "Cadastra um novo animal no sistema.",tags = {"Animal"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o animal criado", response = AnimalOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })

	public ResponseEntity<?> criar(@RequestBody AnimalInput AnimalInput) {
		try {
			
			AnimalOutput animalOutput = cadastroAnimal.cadastrar(AnimalInput);
			
			return respostaStatus(HttpStatus.OK,animalOutput);
			
		}catch(Exception e) {

			return erroServidor(e);
			
		}
	}
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o animal deletado", response = AnimalOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Deleta animal do sistema.",tags = {"Animal"})
	@Override
	@DeleteMapping(path = "/{id}",produces = "application/json")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			
			AnimalOutput animalOutput = cadastroAnimal.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o animal vendido", response = AnimalOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Vende um animal.",tags = {"Animal"})
	@PatchMapping(produces = "application/json",path = "/{id}/vendido")
	public ResponseEntity<?> vender(@PathVariable Long id){
		
		try {
			
			AnimalOutput animalOutput = cadastroAnimal.vender(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}	
		
	}
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o animal com o cocho atualizado", response = AnimalOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza uma ida a um cocho podendo ser de sal, água ou ração",tags = {"Animal"})
	@PatchMapping(path = "/{id}/cocho",params = {"tipo"},produces = "application/json")
	public ResponseEntity<?> idaAoCocho(@RequestParam String tipo,@PathVariable long id) {
		
		try {
			
			AnimalOutput animalOutput = acrescentarIdaAoCocho.executar(id, tipo);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(NotFoundCochoType e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
		
		
		
		
	}
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o animal atualizado.", response = AnimalOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza atualização a todos os parâmetros de um animal.",tags = {"Animal"})
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> atualizar(@RequestBody AnimalInputAtualizacao animalInput,@PathVariable long id) {
		try {
			
			AnimalOutput animalOutput = cadastroAnimal.atualizar(id, animalInput);
			
			return  ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
			
		}
	}
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o animal atualizado.", response = AnimalOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=400, message = "Algum atributo enviado não é válido")
	        
	 })
	@ApiOperation(
			value = "Realiza atualização parcial de um animal. Pode ser enviado somente o campo que quer atualizar",
			tags = {"Animal"})
	@PatchMapping(path = "/{id}")
	public ResponseEntity<?> atualizarAtributos(@RequestBody HashMap<String,Object> atributos,@PathVariable long id) {
		
		try {
			
			AnimalOutput animalOutput = cadastroAnimal.atualizarAtributos(id, atributos);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundAtributo e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err","Argumento inválido");
			}});
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
			
		}
	}

}

package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.funcao;

import java.util.List;
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

import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroFuncao;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = {"Função"},description = "Endpoints relacionados às funções")
@RequestMapping("/funcao")
public class FuncaoController extends ICrudController<FuncaoInput,FuncaoOutput,Funcao> {
	
	
	
	
	//public FuncaoController() {
	//	super();
	//	super.criarConversores(FuncaoOutput.class);
	//}
	
	@Autowired
	private CadastroFuncao cadastroFuncao;
	
	
	@GetMapping
	@ApiOperation(value = "Lista todas as funções cadastradas no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna as funções cadastradas. É um array.", response = FuncaoOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<List<FuncaoOutput>> listar(){
		try {
					
			List<FuncaoOutput> funcoesOutput = cadastroFuncao.listar();
			
			
			return ResponseEntity.status(HttpStatus.OK).body(funcoesOutput);
		
		}catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Override
	@PostMapping
	@ApiOperation(value = "Cadastra uma nova função no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a função criada", response = FuncaoOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<FuncaoOutput> criar(@RequestBody FuncaoInput funcaoInput ) {
		try {
			
			FuncaoOutput funcaoOutput = cadastroFuncao.salvar(funcaoInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcaoOutput);
			
		}catch (Exception e){
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	@DeleteMapping("/{id}")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a função desativada", response = FuncaoOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Desativa uma função no sistema.")
	public ResponseEntity<FuncaoOutput> deletar(@PathVariable Long id) {
		try {
			
			FuncaoOutput funcaoOutput = cadastroFuncao.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcaoOutput);
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	
	@Override
	@PutMapping("/{id}")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a função atualizada.", response = FuncaoOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza atualização a todos os parâmetros de uma função.")
	public ResponseEntity<FuncaoOutput> atualizar(@RequestBody FuncaoInput funcaoInput,@PathVariable Long id) {
		try {
			
			FuncaoOutput funcaoOutput = cadastroFuncao.atualizar(id, funcaoInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcaoOutput);
		
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}  
}

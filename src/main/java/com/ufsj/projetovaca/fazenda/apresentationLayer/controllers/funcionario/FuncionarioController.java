package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.funcionario;
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
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroFuncionario;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.domainServices.VerificaSePossuiFuncaoFuncionario;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncionarioRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcionario")
@Api(tags = {"Funcionário"},description = "Endpoints relacionados aos funcionários")
public class FuncionarioController extends ICrudController<FuncionarioInput,FuncionarioOutput,Funcionario> {
	
	
	Class<FuncionarioOutput> Output = FuncionarioOutput.class;

	
	
	public FuncionarioController() {
		
		super();
		
		super.criarConversores(Output);
	}
	
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	
	@Autowired
	VerificaSePossuiFuncaoFuncionario verificaSePossuiFuncaoFuncionario; 
	
	@Autowired
	CadastroFuncionario cadastroFuncionario;
	
	
	@GetMapping
	@Override
	@ApiOperation(value = "Lista todos os funcionários cadastrados no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna os animais cadastrados. É um array.", response = FuncionarioOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {
		
		try {
			
			List<FuncionarioOutput> funcionariosOutput = cadastroFuncionario.listar();
			
			return RespostaStatus(HttpStatus.OK, funcionariosOutput);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
		
		
	}
	
	@PostMapping
	@Override
	@ApiOperation(value = "Cadastra um novo funcionário no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o animal criado", response = FuncionarioOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado")
	        
	 })
	public ResponseEntity<?> criar(@RequestBody FuncionarioInput funcionarioInput) {
		try {
			
			FuncionarioOutput funcionarioOutput = cadastroFuncionario.salvar(funcionarioInput);
			
			return RespostaStatus(HttpStatus.OK, funcionarioOutput);
			
		}catch(NotFoundWithId e) {
		
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,Object>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(Exception e) {
			
			return erroServidor(e);
		}
		
		
	}
	
	@PatchMapping("/{id}/demitido")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o funcionário demitido", response = FuncionarioOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Demite um funcionário do sistema.")
	public ResponseEntity<?> demitir(@PathVariable Long id) {
		try {
			
			FuncionarioOutput funcionarioOutput = cadastroFuncionario.demitir(id);
			
			return RespostaStatus(HttpStatus.OK, funcionarioOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
	}

	@PutMapping("/{id}")
	@Override
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o funcionário atualizado.", response = FuncionarioOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        
	 })
	@ApiOperation(
			value = "Realiza atualização de um funcionário.")
	public ResponseEntity<?> atualizar(@RequestBody FuncionarioInput funcionarioInput,@PathVariable Long id) {
		try {
			
			FuncionarioOutput funcionarioOutput = cadastroFuncionario.atualizar(funcionarioInput, id);
			
			
			
			return RespostaStatus(HttpStatus.OK, funcionarioOutput);
			
		}catch(NotFoundWithId e) {
		
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,Object>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			
			FuncionarioOutput funcionarioOutput = cadastroFuncionario.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcionarioOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}
	}
}

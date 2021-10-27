package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.fazenda;

import java.util.HashMap;

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

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroFazenda;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.FoundCochoInFazenda;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FazendaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(path = "/fazenda",produces = "application/json")
@RestController
@Api(tags = {"Fazenda"},description = "Endpoints relacionados à fazenda")
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
	@ApiOperation(value = "Lista todas as fazendas cadastradas no sistema. Por enquanto usar somente uma.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna as fazendas cadastradas. É um array.", response = FazendaOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {
		
		return listarTodas(fazendaRepository);
		
		
	}
	
	@PostMapping
	@Override
	@ApiOperation(value = "Cadastra uma nova fazenda no sistema. Por enquanto cadastrar somente uma.",notes = "Cadastrar somente uma fazenda no primeiro sprint. Porém cadastrar mais de uma não irá prejudicar a aplicação.")
	@ApiResponses(value = {
	        @ApiResponse(code=201, message = "Retorna a fazenda criada", response = FazendaOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> criar(@RequestBody FazendaInput fazendaInput) {
			
		try {
			
			
			FazendaOutput fazendaOutput = cadastroFazenda.salvar(fazendaInput);
			
			return RespostaStatus(HttpStatus.CREATED, fazendaOutput);
		
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

	
	@PatchMapping("/{id}/vendida")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a fazenda que foi vendida", response = FazendaOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza a venda de uma fazenda.")
	public ResponseEntity<?> vender(@PathVariable Long id) {
		
		try {
			
			FazendaOutput fazendaOutput = cadastroFazenda.vender(id);
			
			return RespostaStatus(HttpStatus.OK, fazendaOutput);
			
		
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}
	
	
	@PutMapping("/{id}")
	@Override
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a fazenda atualizada.", response = FazendaOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza uma atualização a todos os parâmetros de uma fazenda.")
	public ResponseEntity<?> atualizar(@RequestBody FazendaInput fazendaInput,@PathVariable Long id) {
		try {
		
			return atualizar(fazendaRepository, id, fazendaInput, "idFazenda");
		
		}catch(Exception e) {
		
			return erroServidor(e);
		
		}
		
		
	}

	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a fazenda deletada.", response = FazendaOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=400, message = "Retorna uma mensagem de erro de parâmetro"),
	        
	 })
	@ApiOperation(value = "Realiza a remoção de uma fazenda.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id,@RequestParam(required = false) String force) {
		try {
			
			if(force == null || !force.equals("yes")) {
				force = "no";
			}
			
			FazendaOutput fazendaOutput = cadastroFazenda.deletar(id, force);
			
			return ResponseEntity.status(HttpStatus.OK).body(fazendaOutput);
			
		}catch(FoundCochoInFazenda e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
	}

	@Override
	public ResponseEntity<?> deletar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

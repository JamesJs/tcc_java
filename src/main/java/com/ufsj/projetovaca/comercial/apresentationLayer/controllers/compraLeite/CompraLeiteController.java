package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.compraLeite;

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

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroCompraLeite;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.comercial.domainLayer.models.CompraLeite;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RequestMapping(path ="/compraLeite",produces = "application/json")
@RestController
@Api(tags = {"Compra de Leite"},description = "Endpoints relacionados à realização de compras de leite. As compras são realizadas por compradores cadastrados no sistema.")
public class CompraLeiteController extends ACrudController<CompraLeiteInput, CompraLeiteOutput, CompraLeite> {

	
	@Autowired
	CadastroCompraLeite cadastroCompraLeite;
	
	@GetMapping
	@Override
	@ApiOperation(value = "Lista todos as compras realizadas.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna as compras cadastradas. É um array.", response = CompraLeiteOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {
		
		
		try {
		
			List<CompraLeiteOutput> comprasLeiteOutput = cadastroCompraLeite.listar();
			
			return respostaStatus(HttpStatus.OK, comprasLeiteOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}
	
	@PostMapping
	@Override
	@ApiOperation(value = "Cadastra uma nova compra.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a compra criada", response = CompraLeiteOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> criar(@RequestBody CompraLeiteInput compraLeiteInput) {
		
		try {
			
			CompraLeiteOutput compraLeiteOutput = cadastroCompraLeite.criar(compraLeiteInput);
			
			return respostaStatus(HttpStatus.OK, compraLeiteOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
			
			
		}
		
		
	}
	@DeleteMapping("/{id}")
	@Override
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a compra cancelada", response = CompraLeiteOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Cancela uma compra.")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		try {
			
			CompraLeiteOutput compraLeiteOutput = cadastroCompraLeite.desativar(id);
			
			return respostaStatus(HttpStatus.OK, compraLeiteOutput);
			
		}catch(NotFoundWithId e) {
			
			return erroUsuarioNaoEncontrado(e.getMessage());
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
		
	}
	@PutMapping("/{id}")
	@Override
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna uma compra atualizada.", response = CompraLeiteOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza atualização a todos os parâmetros de uma compra.",tags = {"Animal"})
	public ResponseEntity<?> atualizar(@RequestBody CompraLeiteInput compraLeiteInput,@PathVariable Long id) {
		try {
			
			CompraLeiteOutput compraLeiteOutput = cadastroCompraLeite.atualizar(id, compraLeiteInput);
			
			return respostaStatus(HttpStatus.OK, compraLeiteOutput);
			
		}catch(NotFoundWithId e) {
			
			return erroUsuarioNaoEncontrado(e.getMessage());
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
	}

}

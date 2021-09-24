package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.comprador;

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

import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroComprador;
import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.InvalidTipoDeComprador;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;

@RequestMapping("/comprador")
@RestController
public class CompradorController extends ACrudController<CompradorInput, CompradorOutput, Comprador> {
	
	@Autowired
	CadastroComprador cadastroComprador;
	
	
	@GetMapping
	@Override
	public ResponseEntity<?> listar() {	
		
		try {
			
			List<CompradorOutput> compradoresOutput = cadastroComprador.listar();
			
			return respostaStatus(HttpStatus.OK, compradoresOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e); 
			
		}
	}

	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody CompradorInput compradorInput) {
		
		try {
			
			CompradorOutput compradorOutput = cadastroComprador.criar(compradorInput);
			
			return respostaStatus(HttpStatus.OK, compradorOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		try {
			
			CompradorOutput compradorOutput = cadastroComprador.desativar(id);
			
			return respostaStatus(HttpStatus.OK, compradorOutput);		
			
		}catch(NotFoundWithId e){
			
			return erroUsuarioNaoEncontrado();
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<?> atualizar(@RequestBody CompradorInput CompradorInput,@PathVariable Long id) {
		
		try {
			
			CompradorOutput compradorOutput = cadastroComprador.atualizar(CompradorInput, id);
			
			return respostaStatus(HttpStatus.OK, compradorOutput);
			
		}catch(NotFoundWithId e) {
			
			return erroUsuarioNaoEncontrado();
			
		}catch(InvalidTipoDeComprador e) {
			
			return erroUsuarioNaoEncontrado();
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

}

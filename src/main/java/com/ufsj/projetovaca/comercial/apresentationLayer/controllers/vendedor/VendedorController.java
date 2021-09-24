package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.vendedor;

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

import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroVendedor;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.comercial.domainLayer.models.Vendedor;


@RequestMapping("/vendedor")
@RestController
public class VendedorController extends ACrudController<VendedorInput, VendedorOutput, Vendedor> {

	
	@Autowired
	CadastroVendedor cadastroVendedor;
	
	
	
	public VendedorController() {
		super();
		criarConversores(VendedorOutput.class);
	}
	
	
	
	@Override
	@GetMapping
	public ResponseEntity<?> listar() {
		
		try {
		
			List<VendedorOutput> vendedoresOutput = cadastroVendedor.listar();
		
			return respostaStatus(HttpStatus.OK,vendedoresOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
			
	}
	
	
	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody VendedorInput vendedorInput) {
		
		try {
		
			VendedorOutput vendedorOutput = cadastroVendedor.salvar(vendedorInput);
			
			return respostaStatus(HttpStatus.OK, vendedorOutput);
		
		}catch(Exception e){
			
			return erroServidor(e);
		
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		try {
		
			VendedorOutput vendedorOutput = cadastroVendedor.desativar(id);
		
			return respostaStatus(HttpStatus.OK, vendedorOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
	
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody VendedorInput vendedorInput,@PathVariable Long id) {
		
		try {
			
			VendedorOutput vendedorOutput = cadastroVendedor.atualizar(id, vendedorInput);
			
			return respostaStatus(HttpStatus.OK, vendedorOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

}

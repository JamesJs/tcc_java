package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.compraLeite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroCompraLeite;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.comercial.domainLayer.models.CompraLeite;


@RequestMapping("/compraLeite")
@RestController
public class CompraLeiteController extends ACrudController<CompraLeiteInput, CompraLeiteOutput, CompraLeite> {

	
	@Autowired
	CadastroCompraLeite cadastroCompraLeite;
	
	@GetMapping
	@Override
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
	public ResponseEntity<?> criar(@RequestBody CompraLeiteInput compraLeiteInput) {
		
		try {
			
			CompraLeiteOutput compraLeiteOutput = cadastroCompraLeite.criar(compraLeiteInput);
			
			return respostaStatus(HttpStatus.OK, compraLeiteOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
			
			
		}
		
		
	}

	@Override
	public ResponseEntity<?> deletar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> atualizar(CompraLeiteInput compraLeiteInput, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.ufsj.projetovaca.animal.apresentationLayer.controllers.lote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.CadastroLote;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
@RestController
@RequestMapping("/lote")
public class LoteController extends ACrudController<LoteInput, LoteOutput, Lote> {
	
	@Autowired
	CadastroLote cadastroLote;

	@Override
	public ResponseEntity<?> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody LoteInput LoteInput) {
		
		try {
			
			LoteOutput loteOutput = cadastroLote.cadastrar(LoteInput);
			
			return respostaStatus(HttpStatus.OK, loteOutput);
			
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
	public ResponseEntity<?> atualizar(LoteInput Input, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

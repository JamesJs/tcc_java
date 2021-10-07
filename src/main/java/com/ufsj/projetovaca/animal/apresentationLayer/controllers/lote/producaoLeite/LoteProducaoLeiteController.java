package com.ufsj.projetovaca.animal.apresentationLayer.controllers.lote.producaoLeite;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.EncontrarProducaoLeitePorLote;

@RestController
@RequestMapping("/lote")
public class LoteProducaoLeiteController {
	
	
	@Autowired
	EncontrarProducaoLeitePorLote encontraProducaoLeitePorLote;
	
	@GetMapping("/{idLote}/producaoLeite")
	public ResponseEntity<?> producaoLeitePorLote(@PathVariable Long idLote){
		System.out.println("oi");
		
		HashMap<String, Object> retorno = encontraProducaoLeitePorLote.executar(idLote);
		
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
		
	}
	
	
}

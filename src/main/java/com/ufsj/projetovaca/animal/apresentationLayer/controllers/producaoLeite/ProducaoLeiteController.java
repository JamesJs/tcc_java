package com.ufsj.projetovaca.animal.apresentationLayer.controllers.producaoLeite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.CadastroProducaoLeite;
import com.ufsj.projetovaca.animal.applicationLayer.applicationService.EncontrarProducaoDeLeiteEntreDatas;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
@RestController
@RequestMapping("/producaoLeite")
public class ProducaoLeiteController 
						extends ACrudController<ProducaoLeiteInput, ProducaoLeiteOutput, ProducaoLeite> {
	
	@Autowired
	CadastroProducaoLeite cadastroProducaoLeite;
	
	@Autowired
	EncontrarProducaoDeLeiteEntreDatas encontrarProducaoDeLeiteEntreDatas;
	@GetMapping
	@Override
	public ResponseEntity<?> listar() {
		try {
			
			HashMap<String, Object> retorno = cadastroProducaoLeite.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
	}
	
	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody ProducaoLeiteInput CadastroProducaoLeiteInput) {
		
		try {
			
			ProducaoLeiteOutput producaoLeiteOutput = cadastroProducaoLeite.criar(CadastroProducaoLeiteInput);
			
			return respostaStatus(HttpStatus.OK, producaoLeiteOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
		catch(Exception e) {
			
			return erroServidor(e);
			
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		try {
			
			ProducaoLeiteOutput producaoLeiteOutput = cadastroProducaoLeite.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(producaoLeiteOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}

	@Override
	public ResponseEntity<?> atualizar(ProducaoLeiteInput Input, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@GetMapping(params = {"dataInicial","dataFinal"})
	public ResponseEntity<?> 
		listarEntreDatas(@RequestParam String dataInicial,@RequestParam String dataFinal){
		
		try {
			
			Date dataInicialConvert = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataInicial);
			
			Date dataFinalConvert = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataFinal);			
			
			HashMap<String, Object> retorno = 
					encontrarProducaoDeLeiteEntreDatas.executar(dataInicialConvert,dataFinalConvert);
			
			return respostaStatus(HttpStatus.OK, retorno);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}	
		
	}
	@GetMapping(params = "teste")
	public ResponseEntity<?> 
		teste(@RequestParam String teste){
		
		try {

			System.out.println(teste);
			
			return respostaStatus(HttpStatus.OK, "oi");
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}	
		
	}

}

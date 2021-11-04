package com.ufsj.projetovaca.financeiro.apresentationLayer.controllers.conta;

import java.util.HashMap;
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

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ContaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ContaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.CadastroContaService;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;



@RestController()
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	CadastroContaService cadastroContaService;
	
	@PostMapping()
	public ResponseEntity<?> criar(@RequestBody ContaInput contaInput){
		try {
			
			
			ContaOutput contaOutput = cadastroContaService.criar(contaInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(contaOutput);
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}	
	}
	@GetMapping()
	public ResponseEntity<?> listar(){
		try {
			
			List<ContaOutput> contasOutput = cadastroContaService.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(contasOutput);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}	
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody ContaInput contaInput,@PathVariable Long id){
		try {
			
			ContaOutput contaOutput = cadastroContaService.atualizar(contaInput, id);
			
			return ResponseEntity.status(HttpStatus.OK).body(contaOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> encontrar(@PathVariable Long id){
		try {
			
			ContaOutput contaOutput = cadastroContaService.encontrar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(contaOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			
			ContaOutput contaOutput = cadastroContaService.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(contaOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
	}
}

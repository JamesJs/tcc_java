package com.ufsj.projetovaca.financeiro.apresentationLayer.controllers.receita;

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
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ReceitaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ReceitaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.CadastroContaService;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.CadastroReceitaService;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;
@RestController
@RequestMapping("receita")
public class ReceitaController {
	
	@Autowired
	CadastroReceitaService cadastroReceitaService;
	
	@PostMapping()
	public ResponseEntity<?> criar(@RequestBody ReceitaInput receitaInput){
		try {
				
			ReceitaOutput receitaOutput = cadastroReceitaService.criar(receitaInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(receitaOutput);
			
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
			
			List<ReceitaOutput> receitasOutput = cadastroReceitaService.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(receitasOutput);
			
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
	public ResponseEntity<?> atualizar(@RequestBody ReceitaInput receitaInput,@PathVariable Long id){
		try {
			
			ReceitaOutput receitaOutput = cadastroReceitaService.atualizar(receitaInput, id);
			
			return ResponseEntity.status(HttpStatus.OK).body(receitaOutput);
			
			
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
			
			ReceitaOutput receitaOutput = cadastroReceitaService.encontrar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(receitaOutput);
			
			
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
			
			ReceitaOutput receitaOutput = cadastroReceitaService.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(receitaOutput);
			
			
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

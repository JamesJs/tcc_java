package com.ufsj.projetovaca.financeiro.apresentationLayer.controllers.tipoReceita;

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

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoReceitaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoReceitaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.CadastroTipoReceitaService;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;

@RestController
@RequestMapping("tipoReceita")
public class TipoReceitaController {
	
	@Autowired
	CadastroTipoReceitaService cadastroTipoReceitaService;
	
	@PostMapping()
	public ResponseEntity<?> criar(@RequestBody TipoReceitaInput tipoReceitaInput){
		try {
			
			
			TipoReceitaOutput tipoReceitaOutput = cadastroTipoReceitaService.criar(tipoReceitaInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoReceitaOutput);
			
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
			
			List<TipoReceitaOutput> tiposReceitaOutput = cadastroTipoReceitaService.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(tiposReceitaOutput);
			
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
	public ResponseEntity<?> atualizar(@RequestBody TipoReceitaInput tipoReceitaInput,@PathVariable Long id){
		try {
			
			TipoReceitaOutput tipoReceitaOutput = cadastroTipoReceitaService.atualizar(tipoReceitaInput, id);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoReceitaOutput);
			
			
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
			
			TipoReceitaOutput tipoContaOutput = cadastroTipoReceitaService.encontrar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoContaOutput);
			
			
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
			
			TipoReceitaOutput tipoReceitaOutput = cadastroTipoReceitaService.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoReceitaOutput);
			
			
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

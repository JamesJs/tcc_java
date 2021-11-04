package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.compraGado;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroCompraGado;
import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraGadoInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraGadoOutput;
@RestController
@RequestMapping("compraGado")
public class CompraGadoController {
	
	
	@Autowired
	CadastroCompraGado cadastroCompraGado;
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			
			List<CompraGadoOutput> compraGadoOutput = cadastroCompraGado.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(compraGadoOutput);
			
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
	public ResponseEntity<?> deletar(@PathVariable Long id){
		
		try {
			
			CompraGadoOutput compraGadoOutput = cadastroCompraGado.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(compraGadoOutput);
			
			
		}catch(NotFoundWithId e) {
			
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
			
			CompraGadoOutput compraGadoOutput = cadastroCompraGado.encontrar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(compraGadoOutput);
			
			
		}catch(NotFoundWithId e) {
			
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
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody CompraGadoInput compraGadoInput, @PathVariable Long id){
		try {
			
			
			CompraGadoOutput compraGadoOutput = cadastroCompraGado.atualizar(compraGadoInput, id);
			
			return ResponseEntity.status(HttpStatus.OK).body(compraGadoOutput);			
			
		}catch(NotFoundWithId e) {
			
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
	@PatchMapping("/{id}/cancelada")
	public ResponseEntity<?> cancelar(@PathVariable Long id){
		try {
			
			CompraGadoOutput compraGadoOutput = cadastroCompraGado.cancelar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(compraGadoOutput);	
			
			
		}catch(NotFoundWithId e) {
			
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
	@PatchMapping("/{id}/ativada")
	public ResponseEntity<?> ativar(@PathVariable Long id){
		try {
			
			CompraGadoOutput compraGadoOutput = cadastroCompraGado.ativar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(compraGadoOutput);	
			
			
		}catch(NotFoundWithId e) {
			
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
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody CompraGadoInput compraGadoInput){
		try {
			
			CompraGadoOutput compraGadoOutput = cadastroCompraGado.criar(compraGadoInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(compraGadoOutput);	
			
			
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

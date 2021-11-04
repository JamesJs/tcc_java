package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.vendaGado;


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

import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroVendaGado;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendaGadoInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendaGadoOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;


@RestController
@RequestMapping("vendaGado")
public class VendaGadoController {
	
	@Autowired
	CadastroVendaGado cadastroVendaGado;
	
	@GetMapping()
	public ResponseEntity<?> listar(){
		
		try {
			
			List<VendaGadoOutput> vendasGadoOutput = cadastroVendaGado.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(vendasGadoOutput);
			
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){/**
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
			
			VendaGadoOutput vendaGadoOutput = cadastroVendaGado.encontrar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(vendaGadoOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){/**
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
			
			VendaGadoOutput vendaGadoOutput = cadastroVendaGado.cancelar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(vendaGadoOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){/**
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
			
			VendaGadoOutput vendaGadoOutput = cadastroVendaGado.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(vendaGadoOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){/**
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
			
			VendaGadoOutput vendaGadoOutput = cadastroVendaGado.ativar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(vendaGadoOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody  VendaGadoInput vendaGadoInput){
		
		try {
			
			VendaGadoOutput vendaGadoOutput = cadastroVendaGado.atualizar(id,vendaGadoInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(vendaGadoOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
		
	}
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody VendaGadoInput vendaGadoInput){
		
		try {
			
			VendaGadoOutput vendaGadoOutput = cadastroVendaGado.criar(vendaGadoInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(vendaGadoOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
		
	}
}

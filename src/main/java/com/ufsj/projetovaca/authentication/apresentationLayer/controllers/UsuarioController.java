package com.ufsj.projetovaca.authentication.apresentationLayer.controllers;

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

import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioInput;
import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioOutput;
import com.ufsj.projetovaca.authentication.applicationLayer.applicationService.CadastroUsuario;
import com.ufsj.projetovaca.authentication.applicationLayer.exception.NotFoundWithId;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	CadastroUsuario cadastroUsuario;
	
	
	
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			
			List<UsuarioOutput> usuarioOutput = cadastroUsuario.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
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
			
			UsuarioOutput usuarioOutput = cadastroUsuario.encontrar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
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
	public ResponseEntity<?> criar(@RequestBody UsuarioInput usuarioInput){
		try {	
			
			UsuarioOutput usuarioOutput = cadastroUsuario.criar(usuarioInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				
				put("err",e.getMessage());
				
			}});
			
		}	
	}
	@PatchMapping("/{id}/desativado")
	public ResponseEntity<?> desativar(@PathVariable Long id){
		
		try {
			
			UsuarioOutput usuarioOutput = cadastroUsuario.desativar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}
		
	}
	@PatchMapping("/{id}/isAdmin")
	public ResponseEntity<?> isAdmin(@PathVariable Long id){
		
		try {
			
			UsuarioOutput usuarioOutput = cadastroUsuario.isAdmin(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}
		
	}
	
	@PatchMapping("/{id}/isNotAdmin")
	public ResponseEntity<?> isNotAdmin(@PathVariable Long id){
		
		try {
			
			UsuarioOutput usuarioOutput = cadastroUsuario.isNotAdmin(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}
		
	}
	@PatchMapping("/{id}/ativado")
	public ResponseEntity<?> ativar(@PathVariable Long id){
		
		try {
			
			UsuarioOutput usuarioOutput = cadastroUsuario.ativar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}catch(Exception e){
			
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
			
			UsuarioOutput usuarioOutput = cadastroUsuario.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}catch(Exception e){
			
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
	public ResponseEntity<?> atualizar(@RequestBody UsuarioInput usuarioInput,@PathVariable Long id){
		try {
			
			UsuarioOutput usuarioOutput = cadastroUsuario.atualizar(usuarioInput,id);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				put("err",e.getMessage());
				
			}});
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				
				
				put("err",e.getMessage());
				
			}});
			
		}	
	}
	
	
}

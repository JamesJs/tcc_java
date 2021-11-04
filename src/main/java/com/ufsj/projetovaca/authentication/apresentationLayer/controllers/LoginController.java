package com.ufsj.projetovaca.authentication.apresentationLayer.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.Login;
import com.ufsj.projetovaca.authentication.applicationLayer.DTOs.UsuarioOutput;
import com.ufsj.projetovaca.authentication.domainLayer.domainService.LoginService;
import com.ufsj.projetovaca.authentication.domainLayer.exception.CantLogin;
import com.ufsj.projetovaca.authentication.domainLayer.exception.NotFoundUsuarioByLogin;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	LoginService loginService;
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping
	public ResponseEntity<?> login(@RequestBody Login login) {
		try {
			
			UsuarioOutput usuarioOutput = loginService.execute(login);
			
			return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
			
			
		}catch(NotFoundUsuarioByLogin e) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(CantLogin e) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){/**
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

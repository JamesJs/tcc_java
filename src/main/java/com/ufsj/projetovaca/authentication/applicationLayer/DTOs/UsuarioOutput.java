package com.ufsj.projetovaca.authentication.applicationLayer.DTOs;

import lombok.Data;

@Data
public class UsuarioOutput {
	long id;
	String nome;
	String login;
	boolean isDesativado;
	boolean isAdmin;
}


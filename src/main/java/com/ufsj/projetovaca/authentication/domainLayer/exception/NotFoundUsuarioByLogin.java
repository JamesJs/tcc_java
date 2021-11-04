package com.ufsj.projetovaca.authentication.domainLayer.exception;

public class NotFoundUsuarioByLogin extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundUsuarioByLogin(String msg) {
		super(msg);
	}

}

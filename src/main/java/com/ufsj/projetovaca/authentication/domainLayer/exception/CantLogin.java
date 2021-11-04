package com.ufsj.projetovaca.authentication.domainLayer.exception;

public class CantLogin extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CantLogin(String msg) {
		super(msg);
	}

}

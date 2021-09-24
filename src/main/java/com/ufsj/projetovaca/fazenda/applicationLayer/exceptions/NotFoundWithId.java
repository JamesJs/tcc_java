package com.ufsj.projetovaca.fazenda.applicationLayer.exceptions;

public class NotFoundWithId extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundWithId(String errorMessage) {
		super(errorMessage);
	}
}

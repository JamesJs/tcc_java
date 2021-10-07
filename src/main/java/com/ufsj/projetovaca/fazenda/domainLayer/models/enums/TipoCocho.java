package com.ufsj.projetovaca.fazenda.domainLayer.models.enums;

public enum TipoCocho {
	
	RACAO("Ração"),
	SAL("Sal"),
	AGUA("Água");
	
	final public String label;
	
	TipoCocho(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return this.label;
	}
	
}

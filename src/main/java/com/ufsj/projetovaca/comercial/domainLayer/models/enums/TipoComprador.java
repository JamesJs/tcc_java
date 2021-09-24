package com.ufsj.projetovaca.comercial.domainLayer.models.enums;

public enum TipoComprador {
	
	LEITE("Leite"),
	GADO("Gado"),
	AMBOS("Ambos");
	
	final public String label;
	
	TipoComprador(String label) {
		this.label = label;
	}
	@Override
    public String toString() {
        return this.label;
    }
}

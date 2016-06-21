package br.com.template.domain;



public enum Cargo {

	COZINHEIRO("Cozinheiro"),
	GARCOM("Garçom"),
	CAIXA("Caixa"),
	GERENTE("Gerente");
	
	private String label;
	
	private Cargo(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName(){
		return this.name();
	}

	public static Cargo cargoPorRoleUsuario(Role role) {
		
		Cargo cargo = null;
		
		if (Role.COZINHEIRO.equals(role)){
			
			cargo = COZINHEIRO;
		
		}else if (Role.GARCOM.equals(role)){
			
			cargo = GARCOM;
			
		}else if (Role.GERENTE.equals(role)){
			
			cargo = GERENTE;
		}
		
		return cargo;
	}
}
package br.com.template.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.template.util.InitMessageProperties;

public enum Role {

	ADMIN(InitMessageProperties.getValue(Mensagem.MSI043)),
	USUARIO(InitMessageProperties.getValue(Mensagem.MSI003)), 
	DIRETOR(InitMessageProperties.getValue(Mensagem.MSI044));
	
	private String label;
	
	private Role(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static List<String> getLabelRoles(){
		
		List<String> listLabel = new ArrayList<String>();
		
		for (Role role : values()){
			
			listLabel.add(role.getLabel());
		}
		
		return listLabel;
	}
	
	public static Set<Role> getRolesPorLabel(String[] roles){
		
		Set<Role> listRoles = new HashSet<Role>();
		
		for (Role role : values()){
			
			for (String roleLabel : roles){
				
				if (role.getLabel().equals(roleLabel)){
					
					listRoles.add(role);
				}
			}
		}
		
		return listRoles;
	}
}
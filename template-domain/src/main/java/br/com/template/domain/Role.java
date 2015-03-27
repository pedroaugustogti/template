package br.com.template.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Role {

	ADMIN("Administrador"),
	USUARIO("Usuário");
	
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
			
			listLabel.add(role.label);
		}
		
		return listLabel;
	}
	
	public static Set<Role> getRolesPorLabel(String[] roles){
		
		Set<Role> listRoles = new HashSet<Role>();
		
		for (Role role : values()){
			
			for (String roleLabel : roles){
				
				if (role.label.equals(roleLabel)){
					
					listRoles.add(role);
				}
			}
		}
		
		return listRoles;
	}
}
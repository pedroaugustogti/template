package br.com.template.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.template.util.InitMessageProperties;

public enum Role {

	ADMIN(Mensagem.MSI003),
	USUARIO(Mensagem.MSI043);
	
	private Mensagem msg;
	private String label;
	
	private Role(Mensagem msg){
		this.msg = msg;
		this.label = InitMessageProperties.getValue(msg);
	}

	public String getLabel() {
		return label;
	}
	
	public Mensagem getMsg() {
		return msg;
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
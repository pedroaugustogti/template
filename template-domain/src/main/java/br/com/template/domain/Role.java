package br.com.template.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import br.com.template.util.InitMessageProperties;

public enum Role {

	ADMIN(InitMessageProperties.getValue(Mensagem.MSA001), Empresa.BALADAPP, Empresa.LOCAL_ONE, Empresa.FABRICA_SOFTWARE),
	GARCOM(InitMessageProperties.getValue(Mensagem.MSA002), Empresa.LOCAL_ONE),
	COZINHEIRO(InitMessageProperties.getValue(Mensagem.MSA003), Empresa.LOCAL_ONE),
	GERENTE(InitMessageProperties.getValue(Mensagem.MSA004), Empresa.LOCAL_ONE);
	
	private String label;
	private Empresa[] empresas;
	
	private Role(String label, Empresa...empresas){
		this.label = label;
		this.empresas = empresas;
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

	public static Set<Role> getRolesPorCargo(Cargo cargo) {
		
		Set<Role> listRoles = new HashSet<Role>();
		
		for (Role role : values()){
			
			if (role.name().equals(cargo.name())){
				
				listRoles.add(role);
			}
		}
		
		return listRoles;
	}

	public static List<SelectItem> getRolePorEmpresa(Empresa empresa) {
		
		List<SelectItem> listRoles = new ArrayList<>();
		
		for (Role role : Role.values()){
			
			if (role.empresas != null){
				
				for (Empresa emp : role.empresas){
					
					if (emp.equals(empresa)){
						
						SelectItem selectItem = new SelectItem();
						selectItem.setLabel(role.getLabel());
						selectItem.setValue(role);
						
						listRoles.add(selectItem);
					}
				}
			}
		}
		
		return listRoles;
	}
}
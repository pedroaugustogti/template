package br.com.template.entidades;
 
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.template.generics.EntidadeBasica;
 
@Entity
@Table(name = "tb_usuario")
public class Usuario extends EntidadeBasica{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7181106172249020200L;

	@Id
	@Column(name = "usuario", unique = true, nullable = false, length = 45)
	private String usuario;
	
	@Column(name = "senha", nullable = false, length = 60)
	private String senha;
	
	@Column(name = "ativo", nullable = false)
	private boolean ativo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<UsuarioRole> usuarioRole = new HashSet<UsuarioRole>(0);

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<UsuarioRole> getUsuarioRole() {
		return usuarioRole;
	}

	public void setUsuarioRole(Set<UsuarioRole> usuarioRole) {
		this.usuarioRole = usuarioRole;
	}

	@Override
	public Serializable getId() {
		return usuario;
	}
}
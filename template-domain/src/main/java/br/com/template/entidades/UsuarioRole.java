package br.com.template.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.template.generics.EntidadeBasica;
 
@Entity
@Table(name = "tb_usuario_role", 
	uniqueConstraints =	@UniqueConstraint(columnNames = { "role", "usuario" })
)
public class UsuarioRole extends EntidadeBasica{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -313604385404595537L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name = "role", nullable = false, length = 45)
	private String role;
 
	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
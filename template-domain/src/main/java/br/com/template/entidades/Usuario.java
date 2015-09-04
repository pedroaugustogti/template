package br.com.template.entidades;
 
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.template.domain.Role;
import br.com.template.domain.Situacao;
import br.com.template.generics.EntidadeBasica;
 
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBasica{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7181106172249020200L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "usario", unique = true, nullable = false, length = 45)
	private String usuario;
	
	@Column(name = "senha", nullable = false, length = 60)
	private String senha;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@ElementCollection(targetClass=Role.class)
    @Enumerated(EnumType.STRING) 
    @CollectionTable(name="tb_usuario_role", joinColumns = @JoinColumn(name = "id_usuario"))
    @Column(name="role", nullable = false) 
	private Set<Role> roles;
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Funcionario funcionario;
	
	public Usuario(){
		roles = new HashSet<Role>(BigInteger.ZERO.intValue());
		situacao = Situacao.ATIVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
package br.com.template.entidades;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.template.domain.Empresa;
import br.com.template.domain.Role;
import br.com.template.domain.Situacao;
import br.com.template.generics.EntidadeBasica;
 
@Entity
@Table(name = "tb_usuario")
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
	
    @Enumerated(EnumType.STRING) 
    @Column(name="role", nullable = false) 
	private Role role;
    
    @Enumerated(EnumType.STRING) 
    @Column(name="empresa", nullable = false) 
	private Empresa empresa;
    
    @ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
    
    @ManyToOne
	@JoinColumn(name="id_receita")
	private Receita receita;
    
    @ManyToOne
   	@JoinColumn(name="id_despesa")
   	private Despesa despesa;
    
    @Transient
    private int indexSocio;
	
	public Usuario(){
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public int getIndexSocio() {
		return indexSocio;
	}

	public void setIndexSocio(int indexSocio) {
		this.indexSocio = indexSocio;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
}
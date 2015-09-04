package br.com.template.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.template.domain.Cargo;
import br.com.template.domain.Situacao;
import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="funcionario")
public class Funcionario extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_funcionario")
	private Long id;
	
	@Column(name="cargo")
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	@Column(name="data_admissao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAdmissao;
	
	@Column(name="data_desligamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesligamento;
	
	@Column(name="motivo_desligamento")
	private String motivoDesligamento;
	
	@Column(name="telefone_fixo")
	private String telFixo;
	
	@Column(name="celular")
	private String celular;
	
	@Column(name="salario")
	private Double salario;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@OneToOne
	@JoinColumn(name="id_funcionario")
	private Usuario usuario;
	
	@Transient
	private String salarioFormat;
	
	public Funcionario(){
		
		situacao = Situacao.ATIVO;
	}
	
	public String getSalarioFormat() {
		
		salarioFormat = DinheiroUtil.doubleEmRealSemSimbolo(getSalario());
		return salarioFormat;
	}

	public void setSalarioFormat(String salarioFormat) {
		this.salarioFormat = salarioFormat;
		setSalario(DinheiroUtil.realParaDouble(salarioFormat));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public String getMotivoDesligamento() {
		return motivoDesligamento;
	}

	public void setMotivoDesligamento(String motivoDesligamento) {
		this.motivoDesligamento = motivoDesligamento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
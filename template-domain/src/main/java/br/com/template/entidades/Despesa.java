/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.entidades;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.template.domain.Empresa;
import br.com.template.domain.Situacao;
import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="tb_despesa")
public class Despesa extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_despesa")
	private Long id;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@Column(name="empresa")
	@Enumerated(EnumType.STRING)
	private Empresa empresa;
	
	@Column(name="descricao", unique=true)
	private String descricao;
	
	@OneToMany(mappedBy="despesa", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<DespesaSocio> listDespesaSocio;
	
	@Column(name="horario_solicitacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioSolicitacao;
	
	@Column(name="valor")
	private Double valor;
	
	@Transient
	private String valorFormat;
	
	public Despesa(){
		situacao = Situacao.ATIVO;
	}
	
	public String getValorFormat() {
		
		valorFormat = DinheiroUtil.doubleEmRealSemSimbolo(getValor());
		return valorFormat;
	}

	public void setValorFormat(String valorFormat) {
		this.valorFormat = valorFormat;
		setValor(DinheiroUtil.realParaDouble(valorFormat));
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<DespesaSocio> getListDespesaSocio() {
		return listDespesaSocio;
	}

	public void setListDespesaSocio(List<DespesaSocio> listDespesaSocio) {
		this.listDespesaSocio = listDespesaSocio;
	}

	public Calendar getHorarioSolicitacao() {
		return horarioSolicitacao;
	}

	public void setHorarioSolicitacao(Calendar horarioSolicitacao) {
		this.horarioSolicitacao = horarioSolicitacao;
	}
}
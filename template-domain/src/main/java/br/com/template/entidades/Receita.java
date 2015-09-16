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
@Table(name="tb_receita")
public class Receita extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_receita")
	private Long id;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@Column(name="empresa")
	@Enumerated(EnumType.STRING)
	private Empresa empresa;
	
	@Column(name="descricao", unique=true)
	private String descricao;
	
	@OneToMany(mappedBy="receita", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<Bem> listBem;
	
	@OneToMany(mappedBy="receita", orphanRemoval=true)
	private List<Usuario> listSocio;
	
	@Column(name="valor_dinheiro")
	private Double valorEmDinheiro;
	
	@Column(name="horario_solicitacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioSolicitacao;
	
	@Transient
	private String valorEmDinheiroFormat;
	
	public String getValorEmDinheiroFormat() {
		
		valorEmDinheiroFormat = DinheiroUtil.doubleEmRealSemSimbolo(getValorEmDinheiro());
		return valorEmDinheiroFormat;
	}

	public void setValorEmDinheiroFormat(String valorEmDinheiroFormat) {
		this.valorEmDinheiroFormat = valorEmDinheiroFormat;
		setValorEmDinheiro(DinheiroUtil.realParaDouble(valorEmDinheiroFormat));
	}
	
	public Receita(){
		situacao = Situacao.ATIVO;
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

	public List<Bem> getListBem() {
		return listBem;
	}

	public void setListBem(List<Bem> listBem) {
		this.listBem = listBem;
	}

	public List<Usuario> getListSocio() {
		return listSocio;
	}

	public void setListSocio(List<Usuario> listSocio) {
		this.listSocio = listSocio;
	}

	public Double getValorEmDinheiro() {
		return valorEmDinheiro;
	}

	public void setValorEmDinheiro(Double valorEmDinheiro) {
		this.valorEmDinheiro = valorEmDinheiro;
	}
}
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

import br.com.template.domain.Situacao;
import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="tb_bem")
public class Bem extends EntidadeBasica{

	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_bem")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="valor_vendido")
	private Double valorVendido;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name="id_receita")
	private Receita receita;
	
	@Transient
	private String valorFormat;
	
	@Transient
	private String valorVendidoFormat;
	
	@Transient
	private int index;
	
	public String getValorFormat() {
		
		valorFormat = DinheiroUtil.doubleEmRealSemSimbolo(getValor());
		return valorFormat;
	}

	public void setValorFormat(String valorFormat) {
		this.valorFormat = valorFormat;
		setValor(DinheiroUtil.realParaDouble(valorFormat));
	}

	public String getValorVendidoFormat() {
		
		valorVendidoFormat = DinheiroUtil.doubleEmRealSemSimbolo(getValorVendido());
		
		return valorVendidoFormat;
	}

	public void setValorVendidoFormat(String valorVendidoFormat) {
		this.valorVendidoFormat = valorVendidoFormat;
		setValorVendido(DinheiroUtil.realParaDouble(valorVendidoFormat));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Double getValorVendido() {
		return valorVendido;
	}

	public void setValorVendido(Double valorVendido) {
		this.valorVendido = valorVendido;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
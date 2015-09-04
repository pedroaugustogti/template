/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="despesa_entrada")
public class DespesaEntrada extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_despesa_entrada")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_despesa")
	private Despesa despesa;
	
	@Column(name="preco")
	private Double preco;
	
	@Column(name="data")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Transient
	private String precoFormat;
	
	public String getPrecoFormat() {
		
		precoFormat = DinheiroUtil.doubleEmRealSemSimbolo(getPreco());
		return precoFormat;
	}

	public void setPrecoFormat(String precoFormat) {
		this.precoFormat = precoFormat;
		setPreco(DinheiroUtil.realParaDouble(precoFormat));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
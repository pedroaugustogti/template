/**
 * Disclaimer: this code is only for demo no production use
 */
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

import br.com.template.domain.Medida;
import br.com.template.domain.TipoProduto;
import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="tb_produto")
public class Produto extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Long id;
	
	@Column(name="tipo")
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;
	
	@Column(name="marca")
	private String marca;
	
	@Column(name="medida")
	@Enumerated(EnumType.STRING)
	private Medida medida;
	
	@Column(name="qnt_medida")
	private Integer quantidadeMedida;
	
	@Column(name="descricao")
	private String descricao;
	
	@ManyToOne
   	@JoinColumn(name="id_fornecedor")
   	private Fornecedor fornecedor;
	
	@Transient
	private int index;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public Integer getQuantidadeMedida() {
		return quantidadeMedida;
	}

	public void setQuantidadeMedida(Integer quantidadeMedida) {
		this.quantidadeMedida = quantidadeMedida;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
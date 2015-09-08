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
import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="tb_estoque")
public class Estoque extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_estoque")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	@Column(name="medida")
	@Enumerated(EnumType.STRING)
	private Medida medida;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@Column(name="quantidade_reposicao")
	private Integer qntReposicao;
	
	@Column(name="medida_reposicao")
	@Enumerated(EnumType.STRING)
	private Medida medidaReposicao;
	
	@Column(name="preco")
	private Double preco;
	
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getQntReposicao() {
		return qntReposicao;
	}

	public void setQntReposicao(Integer qntReposicao) {
		this.qntReposicao = qntReposicao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Medida getMedidaReposicao() {
		return medidaReposicao;
	}

	public void setMedidaReposicao(Medida medidaReposicao) {
		this.medidaReposicao = medidaReposicao;
	}
}
/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.template.domain.CategoriaMenu;
import br.com.template.domain.Situacao;
import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="tb_cardapio")
public class Cardapio extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cardapio")
	private Long id;
	
	@Column(name="categoria")
	@Enumerated(EnumType.STRING)
	private CategoriaMenu categoriaMenu;
	
	@Column(name="descricao", unique=true)
	private String descricao;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_cardapio_tempo_preparo")
	private CardapioTempoPreparo tempoPreparo;
	
	@OneToMany(mappedBy="cardapio", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<CardapioIngrediente> listIngredientes;
	
	@Column(name="preco")
	private Double preco;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@Transient
	private boolean pedidoClienteMenu;
	
	@Transient
	private String precoFormat;
	
	public Cardapio(){
		situacao = Situacao.INATIVO;
	}
	
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

	public CategoriaMenu getCategoriaMenu() {
		return categoriaMenu;
	}

	public void setCategoriaMenu(CategoriaMenu categoriaMenu) {
		this.categoriaMenu = categoriaMenu;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CardapioTempoPreparo getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(CardapioTempoPreparo tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	public List<CardapioIngrediente> getListIngredientes() {
		return listIngredientes;
	}

	public void setListIngredientes(List<CardapioIngrediente> listIngredientes) {
		this.listIngredientes = listIngredientes;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public boolean isPedidoClienteMenu() {
		return pedidoClienteMenu;
	}

	public void setPedidoClienteMenu(boolean pedidoClienteMenu) {
		this.pedidoClienteMenu = pedidoClienteMenu;
	}
}
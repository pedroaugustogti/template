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

@Entity
@Table(name="tb_ingrediente_cardapio")
public class CardapioIngrediente extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ingrediente_cardapio")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_estoque")
	private Estoque estoque;
	
	@Column(name="medida")
	@Enumerated(EnumType.STRING)
	private Medida medida;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name="id_cardapio")
	private Cardapio cardapio;
	
	@Transient
	private transient int index;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}
}
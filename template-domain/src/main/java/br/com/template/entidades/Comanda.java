/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="tb_comanda")
public class Comanda extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comanda")
	private Long id;
	
	@OneToMany(mappedBy="comanda", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<Pedido> listPedido;
	
	@OneToOne
	@JoinColumn(name="id_mesa")
	private Mesa mesa;
	
	@Column(name="parcial")
	private Double parcial;
	
	@Transient
	private String parcialFormat;
	
	public String getParcialFormat() {
		
		parcialFormat = DinheiroUtil.doubleEmRealSemSimbolo(getParcial());
		return parcialFormat;
	}

	public void setParcialFormat(String parcialFormat) {
		this.parcialFormat = parcialFormat;
		setParcial(DinheiroUtil.realParaDouble(parcialFormat));
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pedido> getListPedido() {
		return listPedido;
	}

	public void setListPedido(List<Pedido> listPedido) {
		this.listPedido = listPedido;
	}

	public Double getParcial() {
		return parcial;
	}

	public void setParcial(Double parcial) {
		this.parcial = parcial;
	}
}
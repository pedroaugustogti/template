package br.com.template.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="tb_despesa_socio")
public class DespesaSocio extends EntidadeBasica{

	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_despesa_socio")
	private Long id;
	
	@Column(name="valor")
	private Double valor;
	
	@ManyToOne
   	@JoinColumn(name="id_usuario")
   	private Usuario socio;
	
	@ManyToOne
   	@JoinColumn(name="id_despesa")
   	private Despesa despesa;
	
	@Transient
	private int index;
	
	@Transient
	private String valorFormat;
	
	public String getValorFormat() {
		
		valorFormat = DinheiroUtil.doubleEmRealSemSimbolo(getValor());
		return valorFormat;
	}

	public void setValorFormat(String valorFormat) {
		this.valorFormat = valorFormat;
		setValor(DinheiroUtil.realParaDouble(valorFormat));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Usuario getSocio() {
		return socio;
	}

	public void setSocio(Usuario socio) {
		this.socio = socio;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
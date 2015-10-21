/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.template.generics.EntidadeBasica;
import br.com.template.util.DataUtil;

@Entity
@Table(name="tb_cardapio_tempo_preparo")
public class CardapioTempoPreparo extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cardapio_tempo_preparo")
	private Long id;
	
	@Column(name="hora")
	private Integer hora;
	
	@Column(name="minuto")
	private Integer minuto;
	
	@Column(name="segundo")
	private Integer segundo;
	
	@OneToOne
	@JoinColumn(name="id_cardapio")
	private Cardapio cardapio;
	
	@Transient
	private String horaFormat;
	
	@Transient
	private String minutoFormat;
	
	@Transient
	private String segundoFormat;

	public String getHoraFormat() {
		
		horaFormat = DataUtil.minimoDoisDigitos(getHora());
		
		return horaFormat;
	}

	public String getMinutoFormat() {
		
		minutoFormat = DataUtil.minimoDoisDigitos(getMinuto());
		
		return minutoFormat;
	}
	
	public String getSegundoFormat() {
		segundoFormat = DataUtil.minimoDoisDigitos(getSegundo());
		
		return segundoFormat;
	}

	public void setSegundoFormat(String segundoFormat) {
		this.segundoFormat = segundoFormat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	public Integer getSegundo() {
		return segundo;
	}

	public void setSegundo(Integer segundo) {
		this.segundo = segundo;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}
}
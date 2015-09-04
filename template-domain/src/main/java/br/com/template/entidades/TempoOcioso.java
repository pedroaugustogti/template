package br.com.template.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
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

import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="tempo_ocio_cozinha")
public class TempoOcioso extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tempo_ocio_cozinha")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_cozinheiro", referencedColumnName="id_funcionario")
	private Funcionario cozinheiro;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_ocio")
	private Ocio ocio;
	
	@Column(name="horario")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getCozinheiro() {
		return cozinheiro;
	}

	public void setCozinheiro(Funcionario cozinheiro) {
		this.cozinheiro = cozinheiro;
	}

	public Ocio getOcio() {
		return ocio;
	}

	public void setOcio(Ocio ocio) {
		this.ocio = ocio;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}
}
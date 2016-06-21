package br.com.template.entidades;


import javax.persistence.CascadeType;
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

@Entity
@Table(name="tb_quota_socio")
public class QuotaSocio extends EntidadeBasica{

	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_quota_socio")
	private Long id;
	
	@Column(name="quota")
	private Integer quota;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@ManyToOne(cascade=CascadeType.MERGE)
   	@JoinColumn(name="id_usuario")
   	private Usuario socio;
	
	@ManyToOne
   	@JoinColumn(name="id_conf_socio")
   	private ConfigurarSocio configurarSocio;
	
	@Transient
	private int index;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Usuario getSocio() {
		return socio;
	}

	public void setSocio(Usuario socio) {
		this.socio = socio;
	}

	public ConfigurarSocio getConfigurarSocio() {
		return configurarSocio;
	}

	public void setConfigurarSocio(ConfigurarSocio configurarSocio) {
		this.configurarSocio = configurarSocio;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}
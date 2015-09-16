package br.com.template.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@ManyToOne
   	@JoinColumn(name="id_usuario")
   	private Usuario socio;
	
	@ManyToOne
   	@JoinColumn(name="id_conf_socio")
   	private ConfigurarSocio configurarSocio;
	
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
}
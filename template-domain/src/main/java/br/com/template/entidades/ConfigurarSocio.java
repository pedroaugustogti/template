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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.template.domain.Empresa;
import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="tb_conf_socio")
public class ConfigurarSocio extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_conf_socio")
	private Long id;
	
	@Column(name="empresa",unique=true)
	@Enumerated(EnumType.STRING)
	private Empresa empresa;
	
	@OneToMany(mappedBy="configurarSocio", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<QuotaSocio> listQuotaSocio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<QuotaSocio> getListQuotaSocio() {
		return listQuotaSocio;
	}

	public void setListQuotaSocio(List<QuotaSocio> listQuotaSocio) {
		this.listQuotaSocio = listQuotaSocio;
	}
}
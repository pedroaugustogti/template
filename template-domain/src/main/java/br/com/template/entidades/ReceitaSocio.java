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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="tb_receita_socio")
public class ReceitaSocio extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_receita_socio")
	private Long id;
	
	@ManyToOne
   	@JoinColumn(name="id_usuario")
   	private Usuario socio;
	
	@ManyToOne
   	@JoinColumn(name="id_receita")
   	private Receita receita;
	
	@Transient
    private int indexSocio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getSocio() {
		return socio;
	}

	public void setSocio(Usuario socio) {
		this.socio = socio;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public int getIndexSocio() {
		return indexSocio;
	}

	public void setIndexSocio(int indexSocio) {
		this.indexSocio = indexSocio;
	}
}
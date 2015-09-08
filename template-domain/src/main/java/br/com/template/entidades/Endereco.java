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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.template.domain.Cidade;
import br.com.template.domain.Estado;
import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="tb_endereco")
public class Endereco extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_endereco")
	private Long id;
	
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="conjunto")
	private String conjunto;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="complemento")
	private String complemento;
	
	@Column(name="rua")
	private String rua;
	
	@Column(name="numero")
	private String nmero;
	
	@Column(name="cidade")
	@Enumerated(EnumType.STRING)
	private Cidade cidade;
	
	@OneToOne
	@JoinColumn(name="id_endereco")
	private Pessoa pessoa;
	
	public Long getId() {
		return id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getConjunto() {
		return conjunto;
	}

	public void setConjunto(String conjunto) {
		this.conjunto = conjunto;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNmero() {
		return nmero;
	}

	public void setNmero(String nmero) {
		this.nmero = nmero;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
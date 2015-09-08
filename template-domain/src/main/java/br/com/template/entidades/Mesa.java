/**
 * Disclaimer: this code is only for demo no production use
 */
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.template.domain.SituacaoMesa;
import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="tb_mesa")
public class Mesa extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mesa")
	private Long id;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private SituacaoMesa situacaoMesa;
	
	@Column(name="num_mesa")
	private Integer numMesa;
	
	@Column(name="codigo_temporario")
	private String codigo;
	
	@Column(name="cpf_cliente")
	private String cpfCliente;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="id_comanda")
	private Comanda comanda;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	public Mesa(){
		
		situacaoMesa = SituacaoMesa.LIVRE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public SituacaoMesa getSituacaoMesa() {
		return situacaoMesa;
	}

	public void setSituacaoMesa(SituacaoMesa situacaoMesa) {
		this.situacaoMesa = situacaoMesa;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
}
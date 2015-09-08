package br.com.template.entidades;

import java.util.Date;

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
@Table(name="tb_balanco")
public class Balanco extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_balanco")
	private Long id;
	
	@Column(name="num_mesa")
	private Integer numMesa;
	
	@Column(name="cpf_cliente")
	private String cpf;
	
	@ManyToOne
	@JoinColumn(name="id_garcom", referencedColumnName="id_funcionario")
	private Funcionario garcom;
	
	@ManyToOne
	@JoinColumn(name="id_cozinheiro", referencedColumnName="id_funcionario")
	private Funcionario cozinheiro;
	
	@ManyToOne
	@JoinColumn(name="id_cardapio")
	private Cardapio cardapio;
	
	@Column(name="valor_conta")
	private Double valorConta;
	
	@Column(name="taxa_servico")
	private Double taxaServico;
	
	@Column(name="horario_solicitacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioSolicitacao;
	
	@Column(name="horario_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioConclusao;
	
	@Column(name="horario_fecha_conta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechamentoConta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public Funcionario getGarcom() {
		return garcom;
	}

	public void setGarcom(Funcionario garcom) {
		this.garcom = garcom;
	}

	public Funcionario getCozinheiro() {
		return cozinheiro;
	}

	public void setCozinheiro(Funcionario cozinheiro) {
		this.cozinheiro = cozinheiro;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Double getValorConta() {
		return valorConta;
	}

	public void setValorConta(Double valorConta) {
		this.valorConta = valorConta;
	}

	public Date getHorarioSolicitacao() {
		return horarioSolicitacao;
	}

	public void setHorarioSolicitacao(Date horarioSolicitacao) {
		this.horarioSolicitacao = horarioSolicitacao;
	}

	public Date getHorarioConclusao() {
		return horarioConclusao;
	}

	public void setHorarioConclusao(Date horarioConclusao) {
		this.horarioConclusao = horarioConclusao;
	}

	public Date getFechamentoConta() {
		return fechamentoConta;
	}

	public void setFechamentoConta(Date fechamentoConta) {
		this.fechamentoConta = fechamentoConta;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getTaxaServico() {
		return taxaServico;
	}

	public void setTaxaServico(Double taxaServico) {
		this.taxaServico = taxaServico;
	}
}
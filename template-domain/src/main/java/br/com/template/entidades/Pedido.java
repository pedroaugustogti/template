package br.com.template.entidades;

import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.template.domain.SituacaoPedido;
import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="tb_pedido")
public class Pedido extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Long id;
	
	@Column(name="motivo_cancelamento")
	private String motivoCancelamento;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private SituacaoPedido situacao;
	
	@OneToOne
	@JoinColumn(name="id_cardapio")
	private Cardapio cardapio;
	
	@OneToOne
	@JoinColumn(name="id_cozinheiro",referencedColumnName="id_funcionario")
	private Funcionario cozinheiro;
	
	@OneToOne
	@JoinColumn(name="id_comanda")
	private Comanda comanda;
	
	@Column(name="horario_solicitacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioSolicitacao;
	
	@Column(name="horario_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioConclusao;
	
	@Transient
	private int quantidade;
	
	public Pedido(){
		
		quantidade = 1;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituacaoPedido getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPedido situacao) {
		this.situacao = situacao;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Calendar getHorarioSolicitacao() {
		return horarioSolicitacao;
	}

	public void setHorarioSolicitacao(Calendar horarioSolicitacao) {
		this.horarioSolicitacao = horarioSolicitacao;
	}

	public Calendar getHorarioConclusao() {
		return horarioConclusao;
	}

	public void setHorarioConclusao(Calendar horarioConclusao) {
		this.horarioConclusao = horarioConclusao;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public Funcionario getCozinheiro() {
		return cozinheiro;
	}

	public void setCozinheiro(Funcionario cozinheiro) {
		this.cozinheiro = cozinheiro;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
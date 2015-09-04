package br.com.template.entidades;

import java.util.Date;

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
import br.com.template.util.DinheiroUtil;

@Entity
@Table(name="pedido")
public class Pedido extends EntidadeBasica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767706364216917735L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Long id;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
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
	@JoinColumn(name="id_gerente",referencedColumnName="id_funcionario")
	private Funcionario gerente;
	
	@OneToOne
	@JoinColumn(name="id_comanda")
	private Comanda comanda;
	
	@Column(name="horario_solicitacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioSolicitacao;
	
	@Column(name="horario_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioConclusao;
	
	@Transient
	private String subTotal;
	
	public String getSubTotal() {

		double subTotalDouble = 0.0;
		
		if (getCardapio() != null){
			
			subTotalDouble = getCardapio().getPreco() * getQuantidade();
			
			subTotal = DinheiroUtil.doubleEmReal(subTotalDouble);
		}
		
		return subTotal;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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

	public Funcionario getGerente() {
		return gerente;
	}

	public void setGerente(Funcionario gerente) {
		this.gerente = gerente;
	}
}
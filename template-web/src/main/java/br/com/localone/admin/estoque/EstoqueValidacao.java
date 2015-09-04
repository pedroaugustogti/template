package br.com.localone.admin.estoque;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.localone.negocio.EstoqueRegraNegocio;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class EstoqueValidacao extends AbstractValidacao{
	
	@EJB
	private EstoqueRegraNegocio estoqueRegraNegocio;
	
	public void validacao(Estoque estoque) throws NegocioException {
		
		camposObrigatorios(estoque);
		
		quantidadeReposicaoMaiorQntEmEstoque(estoque);
		
		proibeCadastroDoMesmoProduto(estoque);
	}

	private void proibeCadastroDoMesmoProduto(Estoque estoque) throws NegocioException{
		
		estoqueRegraNegocio.proibeCadastroDoMesmoProduto(estoque);
	}

	private void quantidadeReposicaoMaiorQntEmEstoque(Estoque estoque)throws NegocioException {
		
		if (estoque.getQntReposicao() > estoque.getQuantidade()){
			throw new NegocioException(Mensagem.MNG015);
		}
	}

	private void camposObrigatorios(Estoque estoque) throws NegocioException {
		
		if (estoque.getProduto().getId() == null){
			
			throw new NegocioException(Mensagem.MNG010);
		}
		
		if (estoque.getMedida() == null){
			throw new NegocioException(Mensagem.MNG011);
		}
		
		if (decimalNaoInformado(estoque.getPreco())){
			throw new NegocioException(Mensagem.MNG012);
		}
		
		if (inteiroNaoInformado(estoque.getQuantidade())){
			throw new NegocioException(Mensagem.MNG013);
		}
		
		if (inteiroNaoInformado(estoque.getQuantidade())){
			throw new NegocioException(Mensagem.MNG014);
		}
	}
}
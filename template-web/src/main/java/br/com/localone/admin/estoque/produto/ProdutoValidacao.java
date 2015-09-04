package br.com.localone.admin.estoque.produto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.negocio.ProdutoRegraNegocio;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class ProdutoValidacao {
	
	@EJB
	private ProdutoRegraNegocio produtoRegraNegocio;
	
	public void validacao(Produto produto) throws NegocioException {
		
		camposObrigatorios(produto);
		
		proibeCadastroComMesmaDescricao(produto);
	}

	private void proibeCadastroComMesmaDescricao(Produto produto) throws NegocioException {
		
		produtoRegraNegocio.proibeCadastroComMesmaDescricao(produto);
	}

	private void camposObrigatorios(Produto produto) throws NegocioException {
		
		if (produto.getTipoProduto() == null){
			
			throw new NegocioException(Mensagem.MNG008);
		}
		
		if (StringUtils.isBlank(produto.getDescricao())){
			throw new NegocioException(Mensagem.MNG009);
		}
	}
}
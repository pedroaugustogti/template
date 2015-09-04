package br.com.template.framework;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.localone.autorizacao.AutorizacaoEnum;
import br.com.localone.autorizacao.TipoFuncionalidadeEnum;
import br.com.template.controller.login.AuthenticationProviderCustom;
import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;
import br.com.template.util.criptografia.CriptografiaUtil;

/**
 * 
 * @author pedro.oliveira
 * 
 * <p>Classe utilizada para dar acesso a páginas e funcionalidades a partir das roles do usuário.</p>
 * 
 */
public abstract class AutorizacaoManageBean {
	
	/**
	 * Método utilizado para dar acesso a funcionalidades a partir da autorização do usuario logado.
	 * 
	 * @param tipoFuncionalidade chamado do xhtml nos componentes renderizaveis
	 * 
	 * 	<p>Exemplo de implementação no arquivo xhtml:</p>
	 *	
	 *	<ul>
	 *		<li>rendered="#{manageBean.autorizaFuncionalidade('TIPO_FUNCIONALIDADE_ENUM')}"</li>
	 *	</ul>
	 *
	 * @return true para acesso liberado e false no contrário
	 */
	public abstract boolean autorizaFuncionalidade(TipoFuncionalidadeEnum tipoFuncionalidade);
	
	/**
	 * Método vérifica se a ROLE do usuário logado está com acesso pré-definido no {@link AutorizacaoEnum}, caso contrário deve-se invalidar a sessão do usuário.
	 * 
	 */
	protected abstract void validaPermissionamento() throws IOException, NegocioException;
	
	/**
	 * Método obtém o HttpSession a partir do contexto JSF, usado principamente para setar e obter atributos.
	 * 
	 */
	protected abstract HttpSession getHttpSession();
	
	/**
	 * <p>Método verifica se as permissões estão no atributo de sessão({@link AtributoSessao.PERMISSOES_USUARIO} setado quando finaliza a autenticação)
	 * 	 na classe {@link AuthenticationProviderCustom}	</p>
	 * 
	 * 
	 * <p>Descriptografa o valor para a lista de autorizações do Spring Security {@code List<SimpleGrantedAuthority>}</p>
	 * 
	 * @return {@code List<SimpleGrantedAuthority>}
	 * 
	 */
	protected List<SimpleGrantedAuthority> permissoesUsuarioLogado() {
    	
    	List<SimpleGrantedAuthority> permissoesUsuarioLogado = null;
    	Object atributoSessaoPermissoes = getHttpSession().getAttribute(AtributoSessao.PERMISSOES_USUARIO.name());
    	
    	if (atributoSessaoPermissoes != null){
    		
    		permissoesUsuarioLogado = CriptografiaUtil.descriptografarList(atributoSessaoPermissoes, SimpleGrantedAuthority.class);
    	}
    	
    	return permissoesUsuarioLogado;
	}
	
}

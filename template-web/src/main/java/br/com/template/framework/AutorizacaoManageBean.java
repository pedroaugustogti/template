package br.com.template.framework;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;
import br.com.template.util.criptografia.CriptografiaUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class AutorizacaoManageBean {

	protected abstract void validaPermissionamento() throws IOException, NegocioException;
	
	protected abstract HttpSession getHttpSession();
	
	protected List<SimpleGrantedAuthority> permissoesUsuarioLogado() {
    	
    	List<SimpleGrantedAuthority> permissoesUsuarioLogado = null;
    	Object atributoSessaoPermissoes = getHttpSession().getAttribute(AtributoSessao.PERMISSOES_USUARIO.name());
    	
    	if (atributoSessaoPermissoes != null){
    		
    		String permissoesCriptografada = atributoSessaoPermissoes.toString();
    		
    		String permissoesDescriptografada = CriptografiaUtil.descriptografar(permissoesCriptografada);
        	
    		permissoesUsuarioLogado = new Gson().fromJson(permissoesDescriptografada, new TypeToken<List<SimpleGrantedAuthority>>(){}.getType());
    	}
    	
    	return permissoesUsuarioLogado;
	}
}

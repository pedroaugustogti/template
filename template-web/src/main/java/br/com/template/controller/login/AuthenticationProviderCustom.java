package br.com.template.controller.login;

import javax.ejb.EJBException;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import br.com.template.domain.Mensagem;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.InterceptionViewMenssage;
import br.com.template.login.service.AutorizacaoService;
import br.com.template.util.container.AtributoSessao;
import br.com.template.util.criptografia.CriptografiaUtil;

import com.google.gson.Gson;

public class AuthenticationProviderCustom implements AuthenticationProvider {
	
	@Autowired
	private AutorizacaoService usuarioService;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
    	Authentication auth = null;
    	
		try {
			
			auth = isValid(authentication);
			
			if (!auth.isAuthenticated()) {
				
	            throw new BadCredentialsException(Mensagem.MNG002.name());
	        }
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
        
        return auth;
    }

    private Authentication isValid(Authentication authentication) throws NegocioException {
    	
    	Authentication auth = null;
        
        if (autenticacaoComSucesso(authentication)) {
        	
        	auth = createSuccessAuthentication(authentication);
        }
        
        return auth;
    }

    @Interceptors(InterceptionViewMenssage.class)
	private boolean autenticacaoComSucesso(Authentication authentication) throws NegocioException {
		
		UserDetails userDetails = null;
		
		try{
			userDetails = usuarioService.loadUserByUsername(authentication.getName());
		}catch(EJBException e){
			throw new NegocioException(Mensagem.MNG003, e);
		}
		
		
		if (!userDetails.getPassword().equals(authentication.getCredentials().toString())){
			
			throw new NegocioException(Mensagem.MNG002);
		}
		
		return adicionaPermissoesUsuarioNaSessao(userDetails);
	}

    private boolean adicionaPermissoesUsuarioNaSessao(UserDetails userDetails) throws NegocioException {
    	
    	Boolean isUsuarioNaSessao = Boolean.FALSE;
    	
    	try{
    		
    		Gson gson = new Gson();
    		String permissoesUsuario = CriptografiaUtil.criptografar(gson.toJson(userDetails.getAuthorities()));
    		RequestAttributes mapAtributos = RequestContextHolder.currentRequestAttributes();
    		
    		mapAtributos.setAttribute(AtributoSessao.PERMISSOES_USUARIO.name(), permissoesUsuario, RequestAttributes.SCOPE_SESSION);
    		
    		isUsuarioNaSessao = Boolean.TRUE;
    		
    	}catch(Exception ex){
    		throw new NegocioException(Mensagem.MEI011, ex);
    	}
    	
		return isUsuarioNaSessao;
	}

	@Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    protected Authentication createSuccessAuthentication(Authentication authentication) {
    	
        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
        result.setDetails(authentication.getDetails());

        return result;
    }
}
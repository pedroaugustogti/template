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

import br.com.template.domain.MensagemNegocio;
import br.com.template.excecao.NegocioException;
import br.com.template.interceptors.InterceptionViewMenssage;
import br.com.template.login.service.AutorizacaoService;
import br.com.template.util.criptografia.CriptografiaUtil;

import com.google.gson.Gson;

public class AuthenticationProviderCustom implements AuthenticationProvider {
	
	@Autowired
	private AutorizacaoService usuarioService;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
        Authentication res = null;
        
		try {
			res = isValid(authentication);
			
			if (!res.isAuthenticated()) {
				
	            throw new BadCredentialsException("Bad credentials");
	        }
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
        
        return res;
    }

    private Authentication isValid(Authentication authentication) throws NegocioException {
    	
        Authentication res = authentication;
        
        if (autenticacaoComSucesso(authentication)) {
        	
            res = createSuccessAuthentication(authentication);
        }
        
        return res;
    }

    @Interceptors(InterceptionViewMenssage.class)
	private boolean autenticacaoComSucesso(Authentication authentication) throws NegocioException {
		
		UserDetails userDetails = null;
		
		try{
			userDetails = usuarioService.loadUserByUsername(authentication.getName());
		}catch(EJBException e){
			throw new NegocioException(MensagemNegocio.MNG003, e);
		}
		
		
		if (!userDetails.getPassword().equals(authentication.getCredentials().toString())){
			
			throw new NegocioException(MensagemNegocio.MNG002);
		}
		
		return adicionaPermissoesUsuarioNaSessao(userDetails);
	}

    private boolean adicionaPermissoesUsuarioNaSessao(UserDetails userDetails) {
    	
    	Boolean isUsuarioNaSessao = Boolean.FALSE;
    	
    	try{
    		
    		Gson gson = new Gson();
    		String permissoesUsuario = CriptografiaUtil.criptografar(gson.toJson(userDetails.getAuthorities()));
    		RequestAttributes mapAtributos = RequestContextHolder.currentRequestAttributes();
    		
    		mapAtributos.setAttribute(AtributoSessao.PERMISSOES_USUARIO.getChave(), permissoesUsuario, RequestAttributes.SCOPE_SESSION);
    		
    		isUsuarioNaSessao = Boolean.TRUE;
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
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
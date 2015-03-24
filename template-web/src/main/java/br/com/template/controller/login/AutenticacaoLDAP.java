package br.com.template.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AutenticacaoLDAP extends WebAuthenticationDetails {

    private static final long serialVersionUID = 1L;
    private static final String AUTENTICACAO_LDAP = "LDAP";
    
    private final String LDAP;

    public AutenticacaoLDAP(final HttpServletRequest request) {
        super(request);
        LDAP = request.getParameter(AUTENTICACAO_LDAP);
    }

    public String getLDAP() {
        return LDAP;
    }
}

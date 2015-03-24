package br.com.template.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AutenticacaoDetailLDAP implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(final HttpServletRequest context) {
        return new AutenticacaoLDAP(context);
    }
}
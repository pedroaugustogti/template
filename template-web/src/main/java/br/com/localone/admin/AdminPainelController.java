package br.com.localone.admin;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.localone.autorizacao.AutorizacaoEnum;
import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.EmailEnum;
import br.com.template.domain.EmailRemetenteEnum;
import br.com.template.domain.Medida;
import br.com.template.entidades.Estoque;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.EmailDTO;
import br.com.template.util.EmailParametro;
import br.com.template.util.EmailProperties;
import br.com.template.util.EmailUtils;

@ManagedBean(name="painelAdmin")
@ViewScoped
public class AdminPainelController extends AbstractManageBean{
	
	@Inject
	private Event<EmailDTO> eventEmail;
	
	@PostConstruct
	public void inicio() throws NegocioException, IOException{
		
		if (AutorizacaoEnum.GARCOM.equals(getAutorizacao())){
			
			redirecionaPagina(Pagina.PAINEL_MESA);
			
		}else if (AutorizacaoEnum.COZINHEIRO.equals(getAutorizacao())){
			
			redirecionaPagina(Pagina.PAINEL_COZINHA);
		}
		
		Produto produto = new Produto();
		produto.setDescricao("Cigarro - Lucky Strike");
		
		Estoque estoque = new Estoque();
		estoque.setMedida(Medida.UNID);
		estoque.setQuantidade(15);
		estoque.setProduto(produto);
		
		enviaNotificacaoParaReporEstoque(estoque);
	}
	
	private void enviaNotificacaoParaReporEstoque(Estoque estoque) throws NegocioException {
		
		EmailDTO emailDTO = new EmailDTO();
		String corpoEmail = montaCorpoEmail(estoque);
		
		emailDTO.setPara(EmailProperties.getValue(EmailRemetenteEnum.REMETENTE_EMAIL_ESTOQUE)); 
		emailDTO.setAssunto(montaAssunto(estoque));
		emailDTO.setMensagem(corpoEmail);
		
		eventEmail.fire(emailDTO);
	}

	private String montaAssunto(Estoque estoque) throws NegocioException {
		
		EmailParametro emailParametro = new EmailParametro();
		
		emailParametro.addParametro("{produto}", estoque.getProduto().getDescricao());
		
		return EmailEnum.EMAIL_ESTOQUE_ACABANDO.assunto(emailParametro);
	}

	private String montaCorpoEmail(Estoque estoque) throws NegocioException {
		
		EmailParametro emailParametro = new EmailParametro();
		
		emailParametro.addParametro("{produto}", estoque.getProduto().getDescricao());
		emailParametro.addParametro("{quantidade}", estoque.getQuantidade().toString());
		emailParametro.addParametro("{medida}", estoque.getMedida().getLabel());
		return EmailUtils.formataEmail(EmailEnum.EMAIL_ESTOQUE_ACABANDO, emailParametro);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_ADMIN;
	}
}
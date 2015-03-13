package br.com.template.excecao;

import br.com.template.domain.MensagemNegocio;
import br.com.template.domain.TipoMensagem;
import br.com.template.util.InitMessageProperties;

public class NegocioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6019618454815377751L;
	
	private String valor;
	private TipoMensagem tipo;
	
	public NegocioException(MensagemNegocio msg) {
		super(InitMessageProperties.getValue(msg));
		
		valor = InitMessageProperties.getValue(msg);
		tipo = msg.getTipo();
		
		verificaErroInterno(msg);
	}
	
	private void verificaErroInterno(MensagemNegocio msg) {
		
		if (TipoMensagem.INTERNO.equals(msg.getTipo())){
			super.printStackTrace();
		}
	}

	public String getMensagem() {
		return valor;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}
}
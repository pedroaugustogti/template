package br.com.template.mensagem;

public enum MensagemNegocio {
	
	MSN001(TipoMensagem.ALERTA);
	
	private TipoMensagem tipo;
	
	private MensagemNegocio(TipoMensagem tipoMensagem) {
		
		this.tipo = tipoMensagem;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}
}
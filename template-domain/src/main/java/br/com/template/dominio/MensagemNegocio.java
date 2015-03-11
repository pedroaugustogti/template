package br.com.template.dominio;

public enum MensagemNegocio {
	
	MNG001(TipoMensagem.ALERTA), 
	
	MEI001(TipoMensagem.INTERNO),
	
	MDF001(TipoMensagem.DEFAULT);
	
	private TipoMensagem tipo;
	
	private MensagemNegocio(TipoMensagem tipoMensagem) {
		
		this.tipo = tipoMensagem;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}
}
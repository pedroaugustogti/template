package br.com.template.domain;

public enum MensagemNegocio {
	
	MNG001(TipoMensagem.ALERTA),
	MNG002(TipoMensagem.ALERTA), 
	
	MEI001(TipoMensagem.INTERNO),
	MEI002(TipoMensagem.INTERNO),
	MEI003(TipoMensagem.INTERNO),
	MEI004(TipoMensagem.INTERNO),
	MEI005(TipoMensagem.INTERNO),
	MEI006(TipoMensagem.INTERNO),
	MEI007(TipoMensagem.INTERNO),
	MEI008(TipoMensagem.INTERNO),
	MEI009(TipoMensagem.INTERNO),
	
	MDF001(TipoMensagem.DEFAULT);
	
	private TipoMensagem tipo;
	
	private MensagemNegocio(TipoMensagem tipoMensagem) {
		
		this.tipo = tipoMensagem;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}
}
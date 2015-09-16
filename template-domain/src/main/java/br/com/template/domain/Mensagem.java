package br.com.template.domain;

/**
 * 
 * @author pedro.oliveira
 *	
 *	<p>Enum reponsável por representar o arquivo de mensagens de propriedade (messages.properties)</p>
 */
public enum Mensagem {
	
	//REGRAS DE NEGOCIO
	//MNG = Mensagem de Negócio
	
	MNG001(TipoMensagem.ALERTA),
	MNG002(TipoMensagem.ALERTA), 
	MNG003(TipoMensagem.ALERTA),
	MNG004(TipoMensagem.ALERTA),
	MNG005(TipoMensagem.ALERTA),
	MNG006(TipoMensagem.ALERTA),
	MNG007(TipoMensagem.ALERTA),
	
	//Produto
	MNG008(TipoMensagem.ALERTA),
	MNG009(TipoMensagem.ALERTA),
	
	//estoque
	MNG010(TipoMensagem.ALERTA),
	MNG011(TipoMensagem.ALERTA),
	MNG012(TipoMensagem.ALERTA),
	MNG013(TipoMensagem.ALERTA),
	MNG014(TipoMensagem.ALERTA),
	MNG015(TipoMensagem.ALERTA),
	MNG016(TipoMensagem.ALERTA),
	MNG017(TipoMensagem.ALERTA),
	MNG018(TipoMensagem.ALERTA),
	MNG019(TipoMensagem.ALERTA),
	MNG020(TipoMensagem.ALERTA),
	MNG021(TipoMensagem.ALERTA),
	MNG022(TipoMensagem.ALERTA),
	MNG023(TipoMensagem.ALERTA),
	MNG024(TipoMensagem.ALERTA),
	MNG025(TipoMensagem.ALERTA),
	MNG026(TipoMensagem.ALERTA),
	MNG027(TipoMensagem.ALERTA),
	MNG028(TipoMensagem.ALERTA),
	MNG029(TipoMensagem.ALERTA),
	MNG030(TipoMensagem.ALERTA),
	MNG031(TipoMensagem.ALERTA),
	MNG032(TipoMensagem.ALERTA),
	MNG033(TipoMensagem.ALERTA),
	MNG034(TipoMensagem.ALERTA),
	MNG035(TipoMensagem.ALERTA),
	MNG036(TipoMensagem.ALERTA),
	MNG037(TipoMensagem.ALERTA),
	MNG038(TipoMensagem.ALERTA),
	MNG039(TipoMensagem.ALERTA),
	MNG040(TipoMensagem.ALERTA),
	MNG041(TipoMensagem.ALERTA),
	MNG042(TipoMensagem.ALERTA),
	MNG043(TipoMensagem.ALERTA),
	MNG044(TipoMensagem.ALERTA),
	MNG045(TipoMensagem.ALERTA),
	MNG046(TipoMensagem.ALERTA),
	
	//ERROS INTERNOS DO SISTEMA
	//MEI = Mensagem de Erro Interno
	
	MEI001(TipoMensagem.INTERNO),
	MEI002(TipoMensagem.INTERNO),
	MEI003(TipoMensagem.INTERNO),
	MEI004(TipoMensagem.INTERNO),
	MEI005(TipoMensagem.INTERNO),
	MEI006(TipoMensagem.INTERNO),
	MEI007(TipoMensagem.INTERNO),
	MEI008(TipoMensagem.INTERNO),
	MEI009(TipoMensagem.INTERNO),
	MEI010(TipoMensagem.INTERNO),
	MEI011(TipoMensagem.INTERNO),
	MEI012(TipoMensagem.INTERNO),
	MEI013(TipoMensagem.INTERNO),
	
	//MENSAGENS PADRÃO (DEFAULT)
	//MDF = Mensagem Default
	
	MDF001(TipoMensagem.DEFAULT), 
	
	//MENSAGENS ESTATICAS NO XHTML
	//MSI = Mensagens de Interface
	
	MSI003(TipoMensagem.ESTATICO),
	MSI043(TipoMensagem.ESTATICO), 
	MSI044(TipoMensagem.ESTATICO), 
	
	MSA001(TipoMensagem.ESTATICO),
	MSA002(TipoMensagem.ESTATICO),
	MSA003(TipoMensagem.ESTATICO),
	MSA004(TipoMensagem.ESTATICO);
	
	private TipoMensagem tipo;
	
	private Mensagem(TipoMensagem tipoMensagem) {
		
		this.tipo = tipoMensagem;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}
}
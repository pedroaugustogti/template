package br.com.localone.mesa;

import java.math.BigInteger;
import java.util.UUID;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Cargo;
import br.com.template.domain.Mensagem;
import br.com.template.domain.Situacao;
import br.com.template.entidades.Funcionario;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="painelMesaAtendimento")
@ViewScoped
public class MesaPainelAtendimento extends MesaPainelSuperController{
	
	private static final String WIDGET_VAR_DIALOG_GARCOM = "garcom";
	private static final String WIDGET_VAR_DIALOG_CODIGO_MESA = "codigoMesa";
	
	@EJB
	private GenericServiceController<Funcionario, Long> serviceFuncionario;
	
	private Long idFuncionario;
	
	public void gerarCodigoMesa() {
		
		if (StringUtils.isBlank(mesa.getCodigo())){
			
			UUID uuid = UUID.randomUUID();  
			String myRandom = uuid.toString();  
			myRandom =  myRandom.substring(BigInteger.ZERO.intValue(),3);
			
			mesa.setCodigo(myRandom);
			
			service.salvar(mesa);
		}
		
		abrirModal(WIDGET_VAR_DIALOG_CODIGO_MESA);
		fecharModal(WIDGET_VAR_DIALOG_GARCOM);
	}
	
	public void vinculaGarcomMesa(){
		
		Funcionario funcionario = serviceFuncionario.getById(Funcionario.class, idFuncionario, "pessoa");
		
		if (!isGarcom(funcionario)){
			contextPrimefaces().showMessageInDialog(facesMensagem(Mensagem.MNG007));
			return;
		}
		
		mesa.setFuncionario(funcionario);
		gerarCodigoMesa();
	}

	private boolean isGarcom(Funcionario funcionario) {
		return funcionario != null && 
				funcionario.getSituacao().equals(Situacao.ATIVO) && 
				funcionario.getCargo().equals(Cargo.GARCOM) ;
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_MESA_ADMIN;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
}
package br.com.localone.admin.funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import br.com.template.domain.Cidade;
import br.com.template.domain.Estado;
import br.com.template.dto.FiltroFuncionarioDTO;
import br.com.template.entidades.Endereco;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Pessoa;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class FuncionarioSuperController extends AbstractManageBean{
	
	protected FiltroFuncionarioDTO filtroFuncionarioDTO;
	protected Funcionario funcionario;
	
	@EJB
	protected GenericServiceController<Funcionario, Long> service;
	
	@EJB
	protected FuncionarioValidacao validacao;
	
	private List<SelectItem> cidades;
	
	protected void inicio(){
		
		funcionario = new Funcionario();
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		pessoa.setEndereco(endereco);
		funcionario.setPessoa(pessoa);
		filtroFuncionarioDTO = new FiltroFuncionarioDTO();
	}
	
	public void montaCidadesPorEstado(){
		
		Estado estado = funcionario.getPessoa().getEndereco().getEstado();
		
		if (estado != null){
			
			cidades = Cidade.selectItemsPorEstado(estado);
		}else{
			cidades = new ArrayList<SelectItem>();
		}
	}
	
	public FiltroFuncionarioDTO getFiltroFuncionarioDTO() {
		return filtroFuncionarioDTO;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public List<SelectItem> getCidades() {
		return cidades;
	}
}
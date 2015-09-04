package br.com.localone.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.service.FuncionarioService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroFuncionarioDTO;
import br.com.template.entidades.Funcionario;
import br.com.template.excecao.NegocioException;

@Stateless
public class FuncionarioRegraNegocioImpl implements FuncionarioRegraNegocio{
	
	@EJB
	private FuncionarioService service;

	@Override
	public void proibeCadastroDoMesmoCPF(Funcionario funcionario) throws NegocioException {
		
		if (funcionario.getId() == null){
			
			regraNegocioCasoCadastro(funcionario);
			
		}else{
			
			regraNegocioCasoAlteracao(funcionario);
		}
	}

	private void regraNegocioCasoAlteracao(Funcionario funcionario) throws NegocioException {
		
		List<Funcionario> listFuncionario = getFuncionarioPorCPF(funcionario);
		
		if (!listFuncionario.isEmpty()){
			
			Funcionario funcionarioBancoDados = listFuncionario.iterator().next();
			
			if (!funcionario.getId().equals(funcionarioBancoDados.getId())){
				
				throw new NegocioException(Mensagem.MNG036);
			}
		}
	}

	private void regraNegocioCasoCadastro(Funcionario funcionario) throws NegocioException {
		
		List<Funcionario> listFuncionario = getFuncionarioPorCPF(funcionario);
		
		if (!listFuncionario.isEmpty()){
			
			throw new NegocioException(Mensagem.MNG036);
		}
	} 

	private List<Funcionario> getFuncionarioPorCPF(Funcionario funcionario) {
		
		FiltroFuncionarioDTO filtro = new FiltroFuncionarioDTO();
		filtro.setCpf(funcionario.getPessoa().getCpf());
		List<Funcionario> listFuncionario = service.pesquisar(filtro);
		
		return listFuncionario;
	}
}
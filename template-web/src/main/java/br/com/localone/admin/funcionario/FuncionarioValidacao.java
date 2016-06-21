package br.com.localone.admin.funcionario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.negocio.FuncionarioRegraNegocio;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Pessoa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class FuncionarioValidacao extends AbstractValidacao{
	
	@EJB
	private FuncionarioRegraNegocio funcionarioRegraNegocio;
	
	public void validacao(Funcionario funcionario) throws NegocioException {
		
		camposObrigatorios(funcionario);
		
		proibeCadastroDaMesmaDespesa(funcionario);
	}

	private void proibeCadastroDaMesmaDespesa(Funcionario funcionario) throws NegocioException{
		
		funcionarioRegraNegocio.proibeCadastroDoMesmoCPF(funcionario);
	}

	private void camposObrigatorios(Funcionario funcionario) throws NegocioException {
		
		if (funcionario.getEmpresa() == null){
			
			throw new NegocioException(Mensagem.MNG046);
		}
		
		if (funcionario.getCargo() == null){
			
			throw new NegocioException(Mensagem.MNG030);
		}
		
		if (decimalNaoInformado(funcionario.getSalario())){
			throw new NegocioException(Mensagem.MNG031);
		}
		
		if (funcionario.getDataAdmissao() == null){
			throw new NegocioException(Mensagem.MNG032);
		}
		
		if (StringUtils.isBlank(funcionario.getCelular())){
			throw new NegocioException(Mensagem.MNG033);
		}
		
		camposObrigatoriosPessoa(funcionario.getPessoa());
	}

	private void camposObrigatoriosPessoa(Pessoa pessoa) throws NegocioException {
		
		if (pessoa != null){
			
			if (StringUtils.isBlank(pessoa.getCpf())){
				throw new NegocioException(Mensagem.MNG034);
			}
			
			if (StringUtils.isBlank(pessoa.getNome())){
				throw new NegocioException(Mensagem.MNG035);
			}
			
		}else{
			throw new NegocioException(Mensagem.MEI012);
		}
		
	}
}
package br.com.template.util.relatorio;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.template.domain.MensagemNegocio;
import br.com.template.domain.relatorio.RelatorioEnum;
import br.com.template.domain.relatorio.RelatorioPath;
import br.com.template.excecao.NegocioException;

public abstract class AbstractRelatorioUtil {
	
	protected static final RelatorioPath PATH_RAIZ = RelatorioEnum.TEMPLATE;
	
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	
	protected AbstractRelatorioUtil(HttpServletRequest request, HttpServletResponse response) throws NegocioException{
		this.request = request;
		this.response = response;
		
		initPathRelatorio();
	}
	
	protected String recuperaContexto() {
		return request.getServletContext().getRealPath(File.separator).concat(PATH_RAIZ.getPathRaiz());
	}
	
	private void initPathRelatorio() throws NegocioException{
		
		File file = new File(recuperaContexto());
		
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new NegocioException(MensagemNegocio.MEI002);
			}
		}
	}
}

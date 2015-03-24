package br.com.template.domain.relatorio;

import java.io.File;

public interface RelatorioPath {

	String PATH_RAIZ = "WEB-INF".concat(File.separator).concat("classes").concat(File.separator +"relatorios").concat(File.separator);
	String PATH_RELATORIO = "PATH_RELATORIO";
	
	String getPathRaiz();
}

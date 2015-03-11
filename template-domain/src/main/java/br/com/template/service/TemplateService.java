package br.com.template.service;

import java.util.List;

import br.com.template.entidades.EntidadeExemplo;

public interface TemplateService {

	List<EntidadeExemplo> pesquisar(EntidadeExemplo filtro);
}

package br.com.template.generics.service;

import java.io.Serializable;
import java.util.List;

import br.com.template.generics.EntidadeBasica;

public interface GenericService<T extends EntidadeBasica, ID extends Serializable> {

	void salvar(T t) ;

	T getById(Class<T> clazz, ID id);
	
	List<T> listarTodos(Class<T> clazz);

	T getById(Class<T> clazz, ID id, String... camposInitialize);

}

package br.com.template.framework;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.template.generics.EntidadeBasica;
import br.com.template.generics.service.GenericService;

@Stateless
public class GenericServiceController<T extends EntidadeBasica, ID extends Serializable> {
	
	@EJB
	private GenericService<T, Serializable> genericService;

	@Interceptors(InterceptionDefaultMenssage.class)
	public void salvar(T t) {
		genericService.salvar(t);
	}

	public EntidadeBasica getById(Class<T> clazz, Serializable id) {
		return genericService.getById(clazz, id);
	}

	public List<T> listarTodos(Class<T> clazz) {
		return genericService.listarTodos(clazz);
	}

	public EntidadeBasica getById(Class<T> clazz, Serializable id, String... camposInitialize) {
		return genericService.getById(clazz, id, camposInitialize);
	}

	@Interceptors(InterceptionDefaultMenssage.class)
	public void excluir(T t) {
		genericService.excluir(t);
	}
}
package br.com.template.generics.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.hibernate.LockOptions;

import br.com.template.generics.ConsultasDaoJpa;
import br.com.template.generics.EntidadeBasica;
import br.com.template.model.AbstractModel;

@Stateless
public class GenericPersistenceImpl<T extends EntidadeBasica, ID extends Serializable>
					extends AbstractModel implements GenericPersistence<T, ID> {
	
	@EJB
	private ConsultasDaoJpa<T> consultaReposiroty;

	@Override
	public void salvar(T t) {
		
		if (t.getId() == null) {
			em.persist(t);
		} else {
			em.merge(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Class<T> entityName, ID id) {
		return (T) consultaReposiroty.getSession().get(entityName.getName(), id, LockOptions.READ);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Class<T> entityName, ID id, String... camposInitialize) {
		return (T) consultaReposiroty.inicializaCampo(camposInitialize, getById(entityName, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarTodos(Class<T> clazz) {
		return consultaReposiroty.getSession().createCriteria(clazz).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarTodos(Class<T> clazz, String...camposInitialize) {
		return (List<T>) consultaReposiroty.inicializaCampo(camposInitialize, consultaReposiroty.getSession().createCriteria(clazz).list());
	}

	@Override
	public void excluir(T t) {
		em.remove(em.merge(t));
	}
}
package br.com.template.generics.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.LockOptions;

import br.com.template.generics.ConsultasDaoJpa;
import br.com.template.generics.EntidadeBasica;

@Stateless
public class GenericRepositoryImpl<T extends EntidadeBasica, ID extends Serializable> implements GenericRepository<T, ID> {
	
	@PersistenceContext
	protected EntityManager em;

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
}
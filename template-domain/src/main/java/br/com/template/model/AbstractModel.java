package br.com.template.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractModel {
	
	@PersistenceContext
	protected EntityManager em;
	
}

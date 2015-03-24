package br.com.template.login.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.template.entidades.Usuario;

@Stateless
public class UsuarioDaoImpl implements UsuarioDao {
	
	@PersistenceContext
	protected EntityManager em;

	public Usuario findByUserName(String username) {
		
		Query query = em.createNativeQuery(montaQuery(), Usuario.class);
		
		query.setParameter("usuario", username);

		return (Usuario) query.getSingleResult();
	}

	private String montaQuery() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM TB_USUARIO WHERE usuario = :usuario");
		
		return sql.toString();
	}
}
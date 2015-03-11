package br.com.template.util;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;



/**
 * The Class HibernateUtil.
 */
public final class HibernateUtil {

	
	/**
	 * Instantiates a new hibernate util.
	 */
	private HibernateUtil(){
	}
	
	/**
	 * Gets the objeto inicializado.
	 *
	 * @param proxy the proxy
	 * @return the objeto inicializado
	 */
	public static Object getObjetoInicializado(Object proxy){
		
		if (proxy instanceof  HibernateProxy){
			return (( HibernateProxy ) proxy).getHibernateLazyInitializer().getImplementation();
		}else{
			Hibernate.initialize(proxy);
		}
		
		return proxy;
	}
}
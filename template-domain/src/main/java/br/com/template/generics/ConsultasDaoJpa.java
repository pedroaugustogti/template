package br.com.template.generics;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;
import org.springframework.core.GenericTypeResolver;

import br.com.template.anotations.EntityProperty;
import br.com.template.model.AbstractModel;
import br.com.template.util.HibernateUtil;
import br.com.template.util.UtilReflexao;

@Stateless
public class ConsultasDaoJpa<T> extends AbstractModel{

	protected Session session;
	
	public Criteria adicionarCriterioConsultar(Criteria criteria, Object entidade, String alias, boolean isPesquisaExata) {
        Map<?, ?> mapa = UtilReflexao.obterAtributos(entidade);

        for (Map.Entry<?, ?> entry : mapa.entrySet()) {
            String propriedade = (String) entry.getKey();
            Object valor = entry.getValue();

            if (!"class".equalsIgnoreCase(propriedade) && valor != null &&
                    fieldComAnotacao(entidade.getClass(), propriedade, Transient.class)) {

                if (valor instanceof EntidadeBasica) {
                    StringBuilder novoAlias = new StringBuilder();
                    novoAlias.append(entry.getKey());
                    novoAlias.append(".");

                    criteria.createAlias(propriedade, propriedade);

                    adicionarCriterioConsultar(criteria, valor, novoAlias.toString(), isPesquisaExata);
                } else {
                    adicionarFiltro(criteria, propriedade, valor, alias, isPesquisaExata);
                }
            }
        }

        return criteria;
    }
	
	private boolean fieldComAnotacao(Class<?> entidade, String propriedade, Class<?> anotacaoTransient) {

        return UtilReflexao.getCampoComAnotacao(entidade, anotacaoTransient, propriedade) == null;
    }
	
	 private void adicionarFiltro(Criteria criteria, String propriedade, Object valor, String alias, boolean isPesquisaExata) {
	        StringBuilder propriedadeAlias = new StringBuilder();
	        MatchMode MATCH = null;

	        if (isPesquisaExata) {
	            MATCH = MatchMode.EXACT;
	        } else {
	            MATCH = MatchMode.ANYWHERE;
	        }

	        if (alias != null) {
	            propriedadeAlias.append(alias);
	        }

	        propriedadeAlias.append(propriedade);

	        if (valor instanceof String && !StringUtils.isNotBlank((String) valor)) {
	            criteria.add(Restrictions.ilike(propriedadeAlias.toString(), valor.toString().toLowerCase(), MATCH));
	        } else if (!(valor instanceof String)) {
	            criteria.add(Restrictions.eq(propriedadeAlias.toString(), valor));
	        }
	    }
	 
	 public List<T> filtrarPesquisa(Object filtroDTO, Class<T> entidade, String... camposInitialize) {
		 
		 return inicializaCampos(filtrarPesquisa(filtroDTO, entidade), camposInitialize);
	 }
	 
    public T primeiroRegistroPorFiltro(Object filtroDTO, Class<T> entidade, String...camposInitialize) {
    	
    	List<T> list = null;
    	
    	if (camposInitialize ==null){
    		list = filtrarPesquisa(filtroDTO, entidade);
    	}else{
    		list = filtrarPesquisa(filtroDTO, entidade, camposInitialize);
    	}
    	
    	T primeiroRegistro = null;
    	
    	if (!list.isEmpty()){
    		primeiroRegistro = list.get(0);
    	}
		 
		return primeiroRegistro;
	}
    
	@SuppressWarnings("unchecked")
    public List<T> filtrarPesquisa(Object filtroDTO, Class<T> entidade) {
		
		Criteria criteria = getSession().createCriteria(entidade);

		List<String> todosAlias = new ArrayList<String>();
		
		Map<?, ?> mapa = UtilReflexao.obterAtributos(filtroDTO);
		
		for (Map.Entry<?, ?> entry : mapa.entrySet()) {

			String propriedade = (String) entry.getKey();
			Object valor = entry.getValue();

			Field field = UtilReflexao.getCampoComAnotacao(filtroDTO.getClass(), EntityProperty.class, propriedade);

			if (field != null && valor != null) {

				String join = field.getAnnotation(EntityProperty.class).value();
				boolean isPesquisaExata = field.getAnnotation(EntityProperty.class).pesquisaExata();
				boolean ignoraCaseSensitive = field.getAnnotation(EntityProperty.class).ignoraCaseSensitive();

				montaJoinsParaOrdenacao(criteria, todosAlias, join);

				montaWhere(criteria, valor, join, isPesquisaExata, ignoraCaseSensitive);
			}
		}

		return (List<T>) criteria.list();
    }

	private void montaWhere(Criteria criteria, Object valor, String join, boolean isPesquisaExata, boolean ignoraCaseSensitive) {
		
		if (isPesquisaExata && ignoraCaseSensitive && valor instanceof String){
			
			criteria.add(Restrictions.ilike(join, valor.toString().toLowerCase() , MatchMode.EXACT));
			
		}else if (isPesquisaExata && !ignoraCaseSensitive){
			
			criteria.add(Restrictions.eq(join, valor));
			
		}else if (valor != null && valor instanceof String && StringUtils.isNotBlank(valor.toString())){
			criteria.add(Restrictions.ilike(join, valor.toString().toLowerCase() , MatchMode.ANYWHERE));
		}else if (valor != null && !(valor instanceof String)){
			criteria.add(Restrictions.eqOrIsNull(join, valor));
		}
	}

	private void montaJoinsParaOrdenacao(Criteria criteria, List<String> todosJoins, String join) {
		
		String[] joins = StringUtils.split(join, ".");
		
		if (joins.length > 0) {

			int posicaoDaPropriedade = joins.length - 1;

			StringBuilder joinsJaCriados = new StringBuilder();
			List<String> todosAlias = new ArrayList<String>();
			
			for (int i = 0; i < posicaoDaPropriedade; i++) {

				joinsJaCriados.append(joins[i]);

				if (!todosJoins.contains(joinsJaCriados.toString())) {
					
					StringBuilder alias = new StringBuilder(joinsJaCriados);
					String aliasCompleto = retiraPontos(joinsJaCriados.toString());
					String[] divideJoins = splitPorPonto(joinsJaCriados.toString());
					
 					String joinSemUltimo = divideJoins[0];
 					String ultimo = joinSemUltimo;
 					
 					if (divideJoins.length > 1){
 						
 						StringBuilder joinSemUltimoTemp = new StringBuilder();
 						
 						for (int ind = 0;  ind < divideJoins.length - 1; ind++){
 							
 							joinSemUltimoTemp.append(divideJoins[ind]);
 							joinSemUltimoTemp.append(".");
 						}
 						
 						if (joinSemUltimoTemp.length() > 0){
 							
 							joinSemUltimoTemp = new StringBuilder(joinSemUltimoTemp.replace(joinSemUltimoTemp.length()-1, joinSemUltimoTemp.length(), ""));
 							
 							joinSemUltimo = joinSemUltimoTemp.toString();
 						}
 						
 						ultimo = divideJoins[divideJoins.length - 1];
 					}
 					
 					if (todosAlias.contains(retiraPontos(joinSemUltimo))){
 						
 						alias = new StringBuilder();
 						alias.append(retiraPontos(joinSemUltimo));
 						alias.append(".");
 						alias.append(ultimo);
 					}
					
					todosAlias.add(aliasCompleto);

					criteria.createAlias(alias.toString(), aliasCompleto);

					todosJoins.add(joinsJaCriados.toString());
				} 
				
				joinsJaCriados.append(".");
			}
		}
	}
	
	private String[] splitPorPonto(String str) {
		return StringUtils.split(str,".");
	}

	private String retiraPontos(String str) {
		return str.replace(".", "");
	}

	public List<T> inicializaCampos(List<T> list, String[] camposInitialize) {

		if (camposInitialize.length > 0 && StringUtils.isNotBlank(camposInitialize[0])){
			
			for (T t : list) {
	
				inicializaCampo(camposInitialize, t);
			}
		}

		return list;
	}

	public Object inicializaCampo(String[] camposInitialize, Object t) {
		
		for (int index = 0; index < camposInitialize.length; index ++) {
			
			String campo = camposInitialize[index];
			
			String[] campos = StringUtils.split(campo,".");
			int nivelHierarquia = campos.length;
			
			if (t != null){
				
				if (nivelHierarquia == 1){
					
					setProperty(t, campo);
					
				} else if (nivelHierarquia > 1){
					
					Object valueProp = t;
					
					for (int i=0; i < campos.length;i++){
						
						if (valueProp instanceof Collection){
							
							for (Object obj : (Collection<?>)valueProp){
								
								inicializaCampo(campos, obj);
							}
							
						}else{
							
							valueProp = getValorPropriedadeInicializado(campos[i], valueProp);
							
							if (valueProp != null){
								setProperty(valueProp, campos[i]);
							}
						}
					}
				}
			}
		}

		return t;
	}

	private void setProperty(Object t, String campo) {
		Object valuePropertie = getValorPropriedadeInicializado(campo, t);

		setProperty(t, campo, valuePropertie);
	}

	private void setProperty(Object t, String campo, Object valuePropertie) {
		try {
			
			if (valuePropertie != null){
				
				BeanUtils.setProperty(t, campo, valuePropertie);
			}
			
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private Object getValorPropriedadeInicializado(String campo, Object t) {
		
		if (t == null){
			return null;
		}
		
	   Map<?, ?> mapa = UtilReflexao.obterAtributos(t);
       	
       for (Map.Entry<?, ?> entry : mapa.entrySet()) {
    	   
    	   if (entry.getKey().toString().equals(campo)){
    		   
    		   if (entry.getValue() instanceof Collection<?>){
    			   
    			   for (Object obj : (Collection<?>)entry.getValue()){
						
						for (Entry<?, ?> props : UtilReflexao.obterAtributos(obj).entrySet()){
							
							 if (props.getValue() instanceof EntidadeBasica){
								 
			    			   setProperty(obj, props.getKey().toString(), HibernateUtil.getObjetoInicializado(props.getValue()));
				    			   
						 	 }else if (props.getValue() instanceof JavassistLazyInitializer){
						 		 
						 		setProperty(obj, props.getKey().toString(), ((JavassistLazyInitializer)props.getValue()).getImplementation());
						 	 }else if (props.getValue() instanceof Date){
						 		 
						 		setProperty(obj, props.getKey().toString(), ((Date)props.getValue()));
						 	 }else{
						 		 
						 		setProperty(obj, props.getKey().toString(), props.getValue());
						 	 }
						}
					}
    		   }
    		   
    		   if (entry.getValue() instanceof JavassistLazyInitializer){
    			   
    			   return ((JavassistLazyInitializer)entry.getValue()).getImplementation();
    		   }
    		   
    		   return HibernateUtil.getObjetoInicializado(entry.getValue());
    	   }
       }
		
		return null;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntidade() {
		return (Class<T>) GenericTypeResolver.resolveTypeArguments(getClass(), ConsultasDaoJpa.class)[0];
	}
	
	 protected Criteria novoCriterio() {
        return getSession().createCriteria(getEntidade());
    }
	 
	 public Session getSession(){
		 
		 return em.unwrap(Session.class);
	 }
}

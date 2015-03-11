/*
* Projeto: ouvidorsus-web
* Arquivo: SqlFileLocation.java
* 
* Copyright @ Ministério da Saúde.
*
* Este software é confidencial e de propriedade do Ministério da Saúde.
* Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
* expressa autorização do mesmo.
*/
package br.com.template.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityProperty {
	
	/**
	 * Value.
	 *
	 */
	String value();
}

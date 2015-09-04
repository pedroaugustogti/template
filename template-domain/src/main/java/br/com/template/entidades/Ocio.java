package br.com.template.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.template.generics.EntidadeBasica;

@Entity
@Table(name="ocio_cozinha")
public class Ocio extends EntidadeBasica{

	private static final long serialVersionUID = -767706364216917735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ocio")
	private Long id;
	
	@Column(name="minuto_ocioso")
	private Long minutoOcioso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMinutoOcioso() {
		return minutoOcioso;
	}

	public void setMinutoOcioso(Long minutoOcioso) {
		this.minutoOcioso = minutoOcioso;
	}
}
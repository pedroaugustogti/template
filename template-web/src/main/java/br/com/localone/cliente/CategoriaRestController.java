package br.com.localone.cliente;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.template.domain.CategoriaMenu;
import br.com.template.framework.AbstractRestController;

@Controller
@PermitAll
public class CategoriaRestController extends AbstractRestController {
	
	@RequestMapping(value="/categoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> categorias() {
		
		List<CategoriaMenu> categoriaMenu = CategoriaMenu.categorias();
		
		return toJson(categoriaMenu);
	}
}
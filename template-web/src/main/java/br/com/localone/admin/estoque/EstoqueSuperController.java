package br.com.localone.admin.estoque;

import br.com.template.domain.Medida;
import br.com.template.dto.FiltroEstoqueDTO;
import br.com.template.entidades.Estoque;
import br.com.template.entidades.Produto;
import br.com.template.framework.AbstractManageBean;

public abstract class EstoqueSuperController extends AbstractManageBean{
	
	protected FiltroEstoqueDTO filtroEstoqueDTO;
	protected Estoque estoque;
	
	public void inicio(){
		
		estoque = new Estoque();
		Produto produto = new Produto();
		estoque.setProduto(produto);
		filtroEstoqueDTO = new FiltroEstoqueDTO();
	}
	
	public void selecionaProduto(Produto produto){
		
		estoque.setProduto(produto);
	}
	
	public void montaSubMedida(){
		
		Medida medida = estoque.getMedida();
		Medida subMedida = estoque.getMedidaReposicao();
		
		if (medida != null){
			
			if (subMedida == null && !Medida.isSubMedida(medida, subMedida)){
				
				estoque.setMedidaReposicao(medida);
			}
			
			filtroEstoqueDTO.subMedida(medida);
		}else{
			estoque.setMedidaReposicao(null);
		}
	}

	public FiltroEstoqueDTO getFiltroEstoqueDTO() {
		return filtroEstoqueDTO;
	}

	public Estoque getEstoque() {
		return estoque;
	}
}
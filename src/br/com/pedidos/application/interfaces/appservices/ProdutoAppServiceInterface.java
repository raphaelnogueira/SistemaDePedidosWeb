package br.com.pedidos.application.interfaces.appservices;

import java.util.List;
import br.com.pedidos.domain.entities.Produto;

public interface ProdutoAppServiceInterface {
	public void salvar(Produto produto);
	public void atualizar(Produto produto);
	public List<Produto> obterTodos();
	public Produto obterPorId(Long id);
	public void deletar(Long id);
}

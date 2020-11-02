package br.com.pedidos.domain.intefaces.repositories;

import java.util.List;

import br.com.pedidos.domain.entities.Produto;

public interface ProdutoRepositoryInterface {
	public Produto obterPorId(Long id);
	public List<Produto> obterTodos();
	public void salvar(Produto produto);
	public void atualizar(Produto produto);
	public void deletar(Produto produto);
	public Boolean estaVinculadoPedido(Produto produto);
}

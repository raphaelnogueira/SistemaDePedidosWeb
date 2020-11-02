package br.com.pedidos.domain.interfaces.services;

import br.com.pedidos.domain.entities.Produto;

public interface ProdutoServiceInterface {
	public void salvar(Produto produto);
	public void atualizar(Produto produto);
	public void deletar(Produto produto);
}

package br.com.pedidos.domain.intefaces.repositories;

import java.util.List;

import br.com.pedidos.domain.entities.Cliente;

public interface ClienteRepositoryInterface {
	Cliente obterPorId(Long id);
	List<Cliente> obterTodos();
	void salvar(Cliente cliente);
	void atualizar(Cliente cliente);
	void deletar(Cliente cliente);
}

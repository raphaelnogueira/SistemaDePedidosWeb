package br.com.pedidos.domain.interfaces.services;

import br.com.pedidos.domain.entities.Cliente;

public interface ClienteServiceInterface {
	public void salvar(Cliente cliente);
	public void atualizar(Cliente cliente);
	public void deletar(Cliente cliente);
}

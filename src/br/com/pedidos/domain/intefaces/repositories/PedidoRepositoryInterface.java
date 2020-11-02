package br.com.pedidos.domain.intefaces.repositories;

import java.util.List;
import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.entities.Pedido;

public interface PedidoRepositoryInterface {
	Pedido obterPorId(long id);
	List<Pedido> obterTodos();
	List<Pedido> obterPorCliente(Cliente cliente);
	void salvar(Pedido pedido);
}

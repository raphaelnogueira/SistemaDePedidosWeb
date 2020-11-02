package br.com.pedidos.application.interfaces.appservices;

import java.util.List;

import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.entities.ItemDoPedido;
import br.com.pedidos.domain.entities.Pedido;

public interface PedidoAppServiceInterface {
	List<ItemDoPedido> obterItens();
	List<Pedido> obterTodos();
	List<Cliente> obterClientes();
	void salvar(Pedido pedido);
}

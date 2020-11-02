package br.com.pedidos.domain.intefaces.repositories;

import java.util.List;
import br.com.pedidos.domain.entities.ItemDoPedido;
import br.com.pedidos.domain.entities.Pedido;
import br.com.pedidos.domain.entities.Produto;

public interface ItemDoPedidoRepositoryInterface {
	List<ItemDoPedido> obterPorProduto(Produto produto);
	List<ItemDoPedido> obterPorPedido(Pedido pedido);
}

package br.com.pedidos.infra.data.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.pedidos.domain.entities.ItemDoPedido;
import br.com.pedidos.domain.entities.Pedido;
import br.com.pedidos.domain.entities.Produto;
import br.com.pedidos.domain.intefaces.repositories.ItemDoPedidoRepositoryInterface;
import br.com.pedidos.infra.data.config.JpaUtil;

public class ItemDoPedidoRepository implements ItemDoPedidoRepositoryInterface {
	
	private EntityManager entityManager;
	
	public ItemDoPedidoRepository() {
		entityManager = JpaUtil.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemDoPedido> obterPorProduto(Produto produto) {
		Query query = entityManager.createQuery("select i from ItemDoPedido i where i.produto.id = :produto_id", ItemDoPedido.class);
		query.setParameter("produto_id", produto.getId());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemDoPedido> obterPorPedido(Pedido pedido) {
		Query query = entityManager.createQuery("select i from ItemDoPedido i where i.pedido.id = :pedido_id", ItemDoPedido.class);
		query.setParameter("pedido_id", pedido.getId());
		return query.getResultList();
	}

}

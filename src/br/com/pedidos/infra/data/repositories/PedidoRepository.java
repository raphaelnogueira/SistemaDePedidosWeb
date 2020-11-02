package br.com.pedidos.infra.data.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.entities.Pedido;
import br.com.pedidos.domain.intefaces.repositories.PedidoRepositoryInterface;
import br.com.pedidos.infra.data.config.JpaUtil;

public class PedidoRepository implements PedidoRepositoryInterface {
	
	private EntityManager entityManager;
	
	public PedidoRepository() {
		entityManager = JpaUtil.getEntityManager();
	}


	@Override
	public Pedido obterPorId(long id) {
		Query query = entityManager.createQuery("select p from Pedido p where p.id = :id", Pedido.class);
		query.setParameter("id", id);
		return (Pedido) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> obterTodos() {
		Query query = entityManager.createQuery("select p from Pedido p", Pedido.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> obterPorCliente(Cliente cliente) {
		Query query = entityManager.createQuery("select p from Pedido p where p.cliente.id = :cliente_id", Pedido.class);
		query.setParameter("cliente_id", cliente.getId());
		return query.getResultList();
	}


	@Override
	public void salvar(Pedido pedido) {
		entityManager.persist(pedido);
	}

}

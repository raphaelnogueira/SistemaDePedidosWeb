package br.com.pedidos.infra.data.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.pedidos.domain.entities.Produto;
import br.com.pedidos.domain.intefaces.repositories.ProdutoRepositoryInterface;
import br.com.pedidos.infra.data.config.JpaUtil;

public class ProdutoRepository implements ProdutoRepositoryInterface {
	
	private EntityManager entityManager;
	
	public ProdutoRepository() {
		entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Produto obterPorId(Long id) {
		Query query = entityManager.createQuery("select p from Produto p where p.id = :id", Produto.class);
		query.setParameter("id", id);
		return (Produto) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> obterTodos() {
		Query query = entityManager.createQuery("select p from Produto p", Produto.class);
		return query.getResultList();
	}

	@Override
	public void salvar(Produto produto) {
		entityManager.persist(produto);		
	}

	@Override
	public void atualizar(Produto produto) {
		entityManager.merge(produto);
	}

	@Override
	public void deletar(Produto produto) {
		entityManager.remove(produto);
	}
	
	@Override
	public Boolean estaVinculadoPedido(Produto produto) {
		Query query = entityManager.createQuery("select p from Produto p join p.itensDosPedidos i where i.produto_id = :id", Produto.class);
		return !query.getResultList().isEmpty();
	}

}

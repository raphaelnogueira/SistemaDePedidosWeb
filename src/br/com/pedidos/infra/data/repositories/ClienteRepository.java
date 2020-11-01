package br.com.pedidos.infra.data.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.intefaces.repositories.ClienteRepositoryInterface;
import br.com.pedidos.infra.data.config.JpaUtil;

public class ClienteRepository implements ClienteRepositoryInterface {
	
	private EntityManager entityManager;
	
	public ClienteRepository() {
		entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Cliente obterPorId(Long id) {
		Query query = entityManager.createQuery("select c from Cliente c where c.id = :id", Cliente.class);
		query.setParameter("id", id);
		return (Cliente) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> obterTodos() {
		Query query = entityManager.createQuery("select c from Cliente c", Cliente.class);
		return query.getResultList();
	}

	@Override
	public void salvar(Cliente cliente) {
		entityManager.persist(cliente);
	}

	@Override
	public void atualizar(Cliente cliente) {
		entityManager.merge(cliente);		
	}

	@Override
	public void deletar(Cliente cliente) {
		entityManager.remove(cliente);		
	}

}

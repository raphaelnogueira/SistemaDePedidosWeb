import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.pedidos.application.appservices.ClienteAppService;
import br.com.pedidos.application.interfaces.appservices.ClienteAppServiceInterface;
import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.entities.ItemDoPedido;
import br.com.pedidos.domain.entities.Pedido;
import br.com.pedidos.domain.entities.Produto;
import br.com.pedidos.infra.data.config.JpaUtil;

public class Teste {
	public static void main(String[] args) {
//		ClienteServiceInterface clienteService = new ClienteService();
//		ClienteRepositoryInterface clienteRepository = new ClienteRepository();
//		
//		List<Cliente> cs = clienteRepository.obterTodos();
//		
		Cliente c = new Cliente();
		c.setCpf("06598142938");
		c.setNome("Camila");
		c.setSobrenome("Pilato");
		
		ClienteAppServiceInterface clienteAppService = new ClienteAppService();
		clienteAppService.salvar(c);
		
//		
//		EntityManager e = JpaUtil.getEntityManager();
//		EntityTransaction entityTransaction = e.getTransaction();
//		entityTransaction.begin();
//		clienteService.salvar(c);
//		entityTransaction.commit();
//		JpaUtil.closeEntityManager();
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Query q = em.createQuery("select p from Produto p where p.id = :id", Produto.class);
		q.setParameter("id", 1L);
		Produto produto = (Produto) q.getSingleResult();
		
		Query q1 = em.createQuery("select c from Cliente c where c.id = :id", Cliente.class);
		q1.setParameter("id", 4L);
		Cliente cliente = (Cliente) q1.getSingleResult();
		
		ItemDoPedido item = new ItemDoPedido();
		item.setProduto(produto);
		item.setQuantidade(5);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setData(new Date());
		pedido.getItens().add(item);
		
		item.setPedido(pedido);
		
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(pedido);
		tx.commit();
		
		JpaUtil.closeEntityManagerFactory();
	}

}

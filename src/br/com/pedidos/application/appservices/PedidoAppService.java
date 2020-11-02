package br.com.pedidos.application.appservices;

import java.util.ArrayList;
import java.util.List;

import br.com.pedidos.application.interfaces.appservices.PedidoAppServiceInterface;
import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.entities.ItemDoPedido;
import br.com.pedidos.domain.entities.Pedido;
import br.com.pedidos.domain.entities.Produto;
import br.com.pedidos.domain.intefaces.repositories.ClienteRepositoryInterface;
import br.com.pedidos.domain.intefaces.repositories.PedidoRepositoryInterface;
import br.com.pedidos.domain.intefaces.repositories.ProdutoRepositoryInterface;
import br.com.pedidos.domain.interfaces.services.PedidoServiceInterface;
import br.com.pedidos.domain.services.PedidoService;
import br.com.pedidos.infra.data.config.JpaUtil;
import br.com.pedidos.infra.data.repositories.ClienteRepository;
import br.com.pedidos.infra.data.repositories.PedidoRepository;
import br.com.pedidos.infra.data.repositories.ProdutoRepository;

public class PedidoAppService implements PedidoAppServiceInterface {
	
	ProdutoRepositoryInterface produtoRepository;
	PedidoRepositoryInterface pedidoRepository;
	ClienteRepositoryInterface clienteRepository;
	PedidoServiceInterface pedidoService;
	
	public PedidoAppService() {
		produtoRepository = new ProdutoRepository();
		pedidoRepository = new PedidoRepository();
		clienteRepository = new ClienteRepository();
		pedidoService = new PedidoService();
	}

	@Override
	public List<ItemDoPedido> obterItens() {
		List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
		List<Produto> produtos = produtoRepository.obterTodos();
		for(Produto produto : produtos){
			ItemDoPedido item = new ItemDoPedido();
			item.setProduto(produto);
			itens.add(item);
		}
		
		return itens;		
	}

	@Override
	public List<Pedido> obterTodos() {
		return pedidoRepository.obterTodos();
	}

	@Override
	public List<Cliente> obterClientes() {
		return clienteRepository.obterTodos();
	}

	@Override
	public void salvar(Pedido pedido) {
		JpaUtil.beginTransaction();
		
		pedidoService.salvar(pedido);
		
		JpaUtil.closeEntityManager();
	}

}

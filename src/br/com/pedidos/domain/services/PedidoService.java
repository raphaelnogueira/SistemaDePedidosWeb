package br.com.pedidos.domain.services;

import br.com.pedidos.domain.entities.Pedido;
import br.com.pedidos.domain.intefaces.repositories.PedidoRepositoryInterface;
import br.com.pedidos.domain.interfaces.services.PedidoServiceInterface;
import br.com.pedidos.infra.data.repositories.PedidoRepository;

public class PedidoService implements PedidoServiceInterface {

	private PedidoRepositoryInterface pedidoRepository;
	
	public PedidoService() {
		pedidoRepository = new PedidoRepository();
	}
	
	@Override
	public void salvar(Pedido pedido) {
		if(pedido.getItens().isEmpty()) {
			throw new RuntimeException("Ao menos um produto deve ser adicionado ao pedido");
		}
		
		pedidoRepository.salvar(pedido);
	}

}

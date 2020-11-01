package br.com.pedidos.domain.services;

import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.intefaces.repositories.ClienteRepositoryInterface;
import br.com.pedidos.domain.interfaces.services.ClienteServiceInterface;
import br.com.pedidos.infra.data.repositories.ClienteRepository;

public class ClienteService implements ClienteServiceInterface {
	private ClienteRepositoryInterface clienteRepository;
	
	public ClienteService() {
		this.clienteRepository = new ClienteRepository();
	}

	@Override
	public void salvar(Cliente cliente) {
		if(cliente.valido()) {
			clienteRepository.salvar(cliente);
			return;
		}
		
		throw new RuntimeException(String.join("<br/>", cliente.getErros()));
	}

	@Override
	public void deletar(Cliente cliente) {
		if(cliente.getPedidos().isEmpty()) {
			clienteRepository.deletar(cliente);
			return;
		}
		
		throw new RuntimeException("O cliente possui pedidos e não pode ser deletado");
	}

	@Override
	public void atualizar(Cliente cliente) {
		if(cliente.valido()) {
			clienteRepository.atualizar(cliente);
			return;
		}
		
		throw new RuntimeException(String.join("<br/>", cliente.getErros()));		
	}
}

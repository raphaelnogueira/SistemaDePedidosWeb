package br.com.pedidos.application.appservices;


import java.util.List;
import br.com.pedidos.application.interfaces.appservices.ClienteAppServiceInterface;
import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.intefaces.repositories.ClienteRepositoryInterface;
import br.com.pedidos.domain.interfaces.services.ClienteServiceInterface;
import br.com.pedidos.domain.services.ClienteService;
import br.com.pedidos.infra.data.config.JpaUtil;
import br.com.pedidos.infra.data.repositories.ClienteRepository;

public class ClienteAppService implements ClienteAppServiceInterface {
	
	private ClienteServiceInterface clienteService;
	private ClienteRepositoryInterface clienteRepository;
	
	public ClienteAppService() {
		clienteService = new ClienteService();
		clienteRepository = new ClienteRepository();
	}

	@Override
	public void salvar(Cliente cliente) {
		JpaUtil.beginTransaction();
		
		clienteService.salvar(cliente);
		
		JpaUtil.closeEntityManager();		
	}

	@Override
	public void atualizar(Cliente cliente) {
		JpaUtil.beginTransaction();
		
		clienteService.atualizar(cliente);
		
		JpaUtil.closeEntityManager();
		
	}
	
	public List<Cliente> obterTodos(){
		return clienteRepository.obterTodos();
	}

	@Override
	public Cliente obterPorId(Long id) {
		return clienteRepository.obterPorId(id);
	}

	@Override
	public void deletar(Long id) {
		JpaUtil.beginTransaction();
		
		Cliente cliente = clienteRepository.obterPorId(id);
		clienteService.deletar(cliente);
		
		JpaUtil.closeEntityManager();
		
	}

}

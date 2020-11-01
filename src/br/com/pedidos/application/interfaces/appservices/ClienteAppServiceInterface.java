package br.com.pedidos.application.interfaces.appservices;

import java.util.List;
import br.com.pedidos.domain.entities.Cliente;

public interface ClienteAppServiceInterface {
	public void salvar(Cliente cliente);
	public void atualizar(Cliente cliente);
	public List<Cliente> obterTodos();
	public Cliente obterPorId(Long id);
	public void deletar(Long id);
}

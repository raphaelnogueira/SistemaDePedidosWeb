package br.com.pedidos.ui.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import br.com.pedidos.application.appservices.ClienteAppService;
import br.com.pedidos.application.interfaces.appservices.ClienteAppServiceInterface;
import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.entities.ItemDoPedido;
import br.com.pedidos.domain.entities.Pedido;

@ManagedBean
@RequestScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ClienteAppServiceInterface clienteAppService;
	private Cliente cliente;
	private Pedido pedido;
	private List<Cliente> clientes;
	
	@PostConstruct
	public void init(){
		this.clienteAppService = new ClienteAppService();
		this.setCliente(new Cliente());
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getClientes() {
		this.clientes = clienteAppService.obterTodos();
		return clientes;
	}
	
	
	public String editar(Long id) {
		this.cliente = clienteAppService.obterPorId(id);
		
		return "edit.xhtml";
	}
	
	public String detalhar(Long id) {
		this.cliente = clienteAppService.obterPorId(id);
		
		return "detail.xhtml";
	}
	
	public void salvar(Cliente cliente) {
		try {
			clienteAppService.salvar(cliente);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		    e.printStackTrace();
		}
	}
	
	public void atualizar(Cliente cliente) {
		try {
			clienteAppService.atualizar(cliente);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		    e.printStackTrace();
		}
	}
	
	public void deletar(Long id) {
		try {
			clienteAppService.deletar(id);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		    e.printStackTrace();
		}
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public String getItensSerializadosJson(Pedido pedido) {
		List<String> itensJson = new ArrayList<String>();
		for(ItemDoPedido itemDoPedido : pedido.getItens()) {
			itensJson.add(itemDoPedido.serializeToJson());
		}
		
		return "[" + String.join(",", itensJson) + "]";
	}
}

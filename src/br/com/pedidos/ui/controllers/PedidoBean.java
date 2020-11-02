package br.com.pedidos.ui.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.pedidos.application.appservices.PedidoAppService;
import br.com.pedidos.application.interfaces.appservices.PedidoAppServiceInterface;
import br.com.pedidos.domain.entities.Cliente;
import br.com.pedidos.domain.entities.ItemDoPedido;
import br.com.pedidos.domain.entities.Pedido;

@ManagedBean
@RequestScoped
public class PedidoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Pedido pedido;
	private List<Pedido> pedidos;
	private List<Cliente> clientes;
	private PedidoAppServiceInterface pedidoAppService;
	private String clienteId;
	
	@PostConstruct
	public void init(){
		pedidoAppService = new PedidoAppService();
		
		Pedido p = new Pedido();
		p.setCliente(new Cliente());
		List<ItemDoPedido> itens = pedidoAppService.obterItens();
		for(ItemDoPedido item : itens) {
			item.setPedido(p);
		}
		p.setItens(itens);
		this.setPedido(p);
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		pedidos = pedidoAppService.obterTodos();
		return pedidos;
	}

	public List<Cliente> getClientes() {
		clientes = pedidoAppService.obterClientes();
		return clientes;
	}
	
	public void salvar(Pedido pedido) {
		try {
			pedido.setData(new Date());
			
			List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
			for(ItemDoPedido item : pedido.getItens()) {
				if(item.getQuantidade() > 0) {
					itens.add(item);
				}
			}
			pedido.setItens(itens);
			
			pedidoAppService.salvar(pedido);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
			pedido.setItens(pedidoAppService.obterItens());
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		    e.printStackTrace();
		}
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	
	

}

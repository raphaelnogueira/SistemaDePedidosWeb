package br.com.pedidos.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Pedidos")
public class Pedido {
	@Id @GeneratedValue
	private Long id;
	
	private Date data;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
	private List<ItemDoPedido> itens;
	
	public Pedido() {
		itens = new ArrayList<ItemDoPedido>();		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemDoPedido> getItens(){
		return itens;
	}
	
	public void setItens(List<ItemDoPedido> itens) {
		this.itens = itens;
	}
}

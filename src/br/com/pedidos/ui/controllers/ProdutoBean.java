package br.com.pedidos.ui.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import br.com.pedidos.application.appservices.ProdutoAppService;
import br.com.pedidos.application.interfaces.appservices.ProdutoAppServiceInterface;
import br.com.pedidos.domain.entities.Produto;

@ManagedBean
@RequestScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ProdutoAppServiceInterface produtoAppService;
	private Produto produto;
	private List<Produto> produtos;
	
	@PostConstruct
	public void init(){
		produtoAppService = new ProdutoAppService();
		this.setProduto(new Produto());
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		this.produtos = produtoAppService.obterTodos();
		return produtos;
	}
	
	public String editar(Long id) {
		this.produto = produtoAppService.obterPorId(id);
		
		return "edit.xhtml";
	}
	
	public void salvar(Produto produto) {
		try {
			produtoAppService.salvar(produto);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		    e.printStackTrace();
		}
	}
	
	public void atualizar(Produto produto) {
		try {
			produtoAppService.atualizar(produto);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		    e.printStackTrace();
		}
	}
	
	public void deletar(Long id) {
		try {
			produtoAppService.deletar(id);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		    e.printStackTrace();
		}
	}

}

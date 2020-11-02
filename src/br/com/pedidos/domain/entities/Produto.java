package br.com.pedidos.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Produtos")
public class Produto {
	@Id @GeneratedValue
	private Long id;
	
	private String descricao;
	
	@Transient
	private List<String> erros;
	
	public Produto() {
		erros = new ArrayList<String>();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Boolean valido() {
		validarDescricao();
		
		return erros.isEmpty();
	}

	public List<String> getErros() {
		return erros;
	}
	
	private void validarDescricao() {
		if(this.descricao.isEmpty() || this.descricao == null) {
			erros.add("A descrição deve ser preenchida");
		}
		
		if(this.descricao.length() < 4) {
			erros.add("A descrição deve ter pelo menos 4 caracteres");
		}
	}

}

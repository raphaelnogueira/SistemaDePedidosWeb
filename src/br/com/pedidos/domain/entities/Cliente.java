package br.com.pedidos.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Clientes")
public class Cliente {
	@Id @GeneratedValue
	private Long id;
	
	private String cpf;
	
	private String nome;
	
	private String sobrenome;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
	
	@Transient
	private List<String> erros;
	
	public Cliente() {
		erros = new ArrayList<String>();
		pedidos = new ArrayList<Pedido>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getNomeCompleto() {
		return nome + " " + sobrenome;
	}
	
	public List<Pedido> getPedidos(){
		return pedidos;
	}
	
	public Boolean valido() {
		validarNome();
		validarSobrenome();
		validarCpf();
		
		return erros.isEmpty();
	}

	public List<String> getErros() {
		return erros;
	}
	
	private void validarCpf() {
		if(this.cpf.isEmpty() || this.cpf == null) {
			erros.add("O cpf deve ser preenchido");
		}
		
		if(this.cpf.length() != 11) {
			erros.add("O cpf deve ter 11 caracteres");
		}
	}
	
	private void validarNome() {
		if(this.nome.isEmpty() || this.nome == null) {
			erros.add("O nome deve ser preenchido");
		}
		
		if(this.nome.length() < 2) {
			erros.add("O nome deve ter pelo menos dois caracteres");
		}
	}
	
	private void validarSobrenome() {
		if(this.sobrenome.isEmpty() || this.sobrenome == null) {
			erros.add("O sobrenome deve ser preenchido");
		}
		
		if(this.sobrenome.length() < 2) {
			erros.add("O sobrenome deve ter pelo menos dois caracteres");
		}
	}
}

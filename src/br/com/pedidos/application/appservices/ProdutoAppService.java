package br.com.pedidos.application.appservices;

import java.util.List;

import br.com.pedidos.application.interfaces.appservices.ProdutoAppServiceInterface;
import br.com.pedidos.domain.entities.Produto;
import br.com.pedidos.domain.intefaces.repositories.ProdutoRepositoryInterface;
import br.com.pedidos.domain.interfaces.services.ProdutoServiceInterface;
import br.com.pedidos.domain.services.ProdutoService;
import br.com.pedidos.infra.data.config.JpaUtil;
import br.com.pedidos.infra.data.repositories.ProdutoRepository;

public class ProdutoAppService implements ProdutoAppServiceInterface {
	
	private ProdutoRepositoryInterface produtoRepository;
	private ProdutoServiceInterface produtoService;
	
	public ProdutoAppService() {
		produtoRepository = new ProdutoRepository();
		produtoService = new ProdutoService();
	}

	@Override
	public void salvar(Produto produto) {
		JpaUtil.beginTransaction();
		
		produtoService.salvar(produto);
		
		JpaUtil.closeEntityManager();	
	}

	@Override
	public void atualizar(Produto produto) {
		JpaUtil.beginTransaction();
		
		produtoService.atualizar(produto);
		
		JpaUtil.closeEntityManager();	
	}

	@Override
	public List<Produto> obterTodos() {
		return produtoRepository.obterTodos();
	}

	@Override
	public Produto obterPorId(Long id) {
		return produtoRepository.obterPorId(id);
	}

	@Override
	public void deletar(Long id) {
		JpaUtil.beginTransaction();
		
		Produto produto = produtoRepository.obterPorId(id);
		produtoService.deletar(produto);
		
		JpaUtil.closeEntityManager();	
	}

}

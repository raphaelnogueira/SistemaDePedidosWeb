package br.com.pedidos.domain.services;

import br.com.pedidos.domain.entities.Produto;
import br.com.pedidos.domain.intefaces.repositories.ItemDoPedidoRepositoryInterface;
import br.com.pedidos.domain.intefaces.repositories.ProdutoRepositoryInterface;
import br.com.pedidos.domain.interfaces.services.ProdutoServiceInterface;
import br.com.pedidos.infra.data.repositories.ItemDoPedidoRepository;
import br.com.pedidos.infra.data.repositories.ProdutoRepository;

public class ProdutoService implements ProdutoServiceInterface {
	private ProdutoRepositoryInterface produtoRepository;
	private ItemDoPedidoRepositoryInterface itemDoPedidoRepository;
	
	public ProdutoService() {
		produtoRepository = new ProdutoRepository();
		itemDoPedidoRepository = new ItemDoPedidoRepository();
	}
	
	@Override
	public void salvar(Produto produto) {
		if(produto.valido()) {
			produtoRepository.salvar(produto);
			return;
		}
		
		throw new RuntimeException(String.join("<br/>", produto.getErros()));
	}

	@Override
	public void atualizar(Produto produto) {
		Boolean podeAtualizar = itemDoPedidoRepository.obterPorProduto(produto).isEmpty();
		if(!podeAtualizar) {
			throw new RuntimeException("O produto não pode ser atualizado pois está vinculado a um ou mais pedidos");
		}
		
		if(produto.valido()) {
			produtoRepository.atualizar(produto);
			return;
		}
		
		throw new RuntimeException(String.join("<br/>", produto.getErros()));		
	}

	@Override
	public void deletar(Produto produto) {
		Boolean podeDeletar = itemDoPedidoRepository.obterPorProduto(produto).isEmpty();
		if(podeDeletar) {
			produtoRepository.deletar(produto);
			return;
		}
		
		throw new RuntimeException("O produto não pode ser deletado pois está vinculado a um ou mais pedidos");
	}

}

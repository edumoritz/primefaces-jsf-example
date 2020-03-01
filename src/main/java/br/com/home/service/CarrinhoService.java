package br.com.home.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.home.model.Carrinho;

@ApplicationScoped
public class CarrinhoService {

	private List<Carrinho> produtosCarrinho;

	public List<Carrinho> getProdutosCarrinho() {
		return produtosCarrinho;
	}

	public void setProdutosCarrinho(List<Carrinho> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
	}

}

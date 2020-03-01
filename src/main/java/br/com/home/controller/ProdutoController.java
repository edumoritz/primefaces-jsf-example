package br.com.home.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.home.message.MessageBean;
import br.com.home.model.Carrinho;
import br.com.home.model.Produto;
import br.com.home.model.Usuario;
import br.com.home.service.CarrinhoService;
import br.com.home.service.ProdutoService;

/**
 * 
 * @author Eduardo Moritz
 * @ApplicationScoped: o bean é mantido até a sessão do usuário encerrar, ou seja, até essa sessão ser explicitamente invalidada 
 * (HttpSession.invalidate()) ou até a ela expirar.
 * 
 */
@Named
@ApplicationScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService service;
	
	@Inject 
	private MessageBean msg;

	@Inject
	private CarrinhoService carrinhoService;

	private Produto selectProduto;

	private List<Produto> produtos;
	private List<Carrinho> produtosCarrinho = new ArrayList<Carrinho>();
	private Usuario usuario;

	@PostConstruct
	public void init() {
		try {
			produtos = service.createProdutos(15);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Carrega Lista do Carrinho
	 */
	public void enviaCarrinho(Produto p) {
		try {
			carregaCarrinho(p);
		} catch (Exception e) {
			throw e;
		}
	}	

	/**
	 * @return Altera para pagina do Carrinho
	 */
	public String carrinho() {
		return "/paginas/Carrinho?faces-redirect=true";
	}
	
	private void carregaCarrinho(Produto p) {
		boolean existe = false;		
		
		for (int i = 0; i < produtosCarrinho.size(); i++) {
			if (produtosCarrinho.get(i).getProduto().getId() == p.getId()) {
				existe = true;
			}
		}
		if (existe == true) {
			msg.addMessage("Atenção", "O produto '" + p.getId() + " - " + p.getDescricao() + "' já existe no Carrinho.",
					0);
		} else  {
			produtosCarrinho.add(new Carrinho(1, p));
			carrinhoService.setProdutosCarrinho(produtosCarrinho);
			msg.addMessage("Sucesso",
					"O produto '" + p.getId() + " - " + p.getDescricao() + "' foi adicionado no Carrinho.", 0);
		}
		
	}

	public ProdutoService getService() {
		return service;
	}

	public void setService(ProdutoService service) {
		this.service = service;
	}

	public Produto getSelectProduto() {
		return selectProduto;
	}

	public void setSelectProduto(Produto selectProduto) {
		this.selectProduto = selectProduto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CarrinhoService getCarrinhoService() {
		return carrinhoService;
	}

	public void setCarrinhoService(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}

	public List<Carrinho> getProdutosCarrinho() {
		return produtosCarrinho;
	}

	public void setProdutosCarrinho(List<Carrinho> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
	}

}

package br.com.home.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.com.home.message.MessageBean;
import br.com.home.model.Carrinho;
import br.com.home.model.Produto;
import br.com.home.service.CarrinhoService;

/**
 * 
 * @author Eduardo Moritz
 * @ApplicationScoped: o bean é mantido até a sessão do usuário encerrar, ou seja, até essa sessão ser explicitamente invalidada 
 * (HttpSession.invalidate()) ou até a ela expirar.
 *
 */
@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject
	private CarrinhoService carrinhoService;
	
	@Inject 
	private MessageBean msg;

	private List<Carrinho> produtosCarrinho;

	@PostConstruct
	public void carregaProdutos() {
		produtosCarrinho = carrinhoService.getProdutosCarrinho();
	}
	
	public String finalizar() {
		return "/restricted/Finalizar?faces-redirect=true";
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			editaProduto(event);
		} catch(Exception e) {
			throw e;
		}
	}	

	public void onRowCancel(RowEditEvent event) {
		msg.addMessage("Edição Cancelada", ((Carrinho) event.getObject()).getProduto().getDescricao() + " - Qtd: "
				+ ((Carrinho) event.getObject()).getQuantidade(), 0);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue))
			msg.addMessage("Quantidade Alterada", "Qtd Anterior: " + oldValue + ", Qtd Nova:" + newValue, 0);

	}

	public void removeItem(Carrinho c) {
		produtosCarrinho.remove(c);
		msg.addMessage("Exclusão", "Produto " + c.getProduto().getDescricao() + " Removido", 0);

	}	
	
	private void editaProduto(RowEditEvent event) {
		Produto p = ((Carrinho) event.getObject()).getProduto();
		int cQtd = ((Carrinho) event.getObject()).getQuantidade();
		BigDecimal frete = new BigDecimal("0.05").multiply(p.getValor()).multiply(new BigDecimal(cQtd)).setScale(2,
				RoundingMode.HALF_EVEN);

		for (int i = 0; i < produtosCarrinho.size(); i++) {
			if (produtosCarrinho.get(i).getProduto().getId().equals(p.getId())) {
				produtosCarrinho.get(i).setFrete(frete);
				produtosCarrinho.get(i).setValorTotal(
						p.getValor().multiply(new BigDecimal(cQtd)).add(frete).setScale(2, RoundingMode.HALF_EVEN));
			}
		}

		msg.addMessage("Quantidade Alterada", p.getDescricao() + " - Qtd: " + cQtd, 0);
		
	}

	public List<Carrinho> getProdutosCarrinho() {
		return produtosCarrinho;
	}

	public void setProdutosCarrinho(List<Carrinho> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
	}

}

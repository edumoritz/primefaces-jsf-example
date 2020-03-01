package br.com.home.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.home.message.MessageBean;
import br.com.home.model.Carrinho;
import br.com.home.model.Usuario;
import br.com.home.service.CarrinhoService;
import br.com.home.service.UsuarioService;

/**
 * 
 * @author Eduardo Moritz
 * @ViewScoped o bean é mantido até a aplicação navegar p/ outra página.
 *
 */
@Named
@ViewScoped
public class FinalizarController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Carrinho> produtosCarrinho;
	private BigDecimal total, cupom_value, armazena;
	private String somaTotal;
	private String parcela;
	private Usuario usuario;
	private String cupom;
	
	@Inject 
	private MessageBean msg;

	@Inject
	private UsuarioService userService;

	@Inject
	private CarrinhoService carrinhoService;

	@PostConstruct
	public void carregaProdutos() {
		produtosCarrinho = carrinhoService.getProdutosCarrinho();
		somaTotal = totalCompra();
		usuario = userService.createUsuario();
	}

	public String totalCompra() {
		total = new BigDecimal("0");
		for (Carrinho c : produtosCarrinho) {
			total = total.add(c.getValorTotal());
		}
		armazena = total;
		return new DecimalFormat("###,###.###").format(total);
	}

	public List<String> comboBoxParcelas(String query) {
		List<String> results = new ArrayList<>();
		BigDecimal parcelas = new BigDecimal(0);

		for (int i = 1; i <= 10; i++) {
			parcelas = this.total.divide(new BigDecimal(i), RoundingMode.HALF_EVEN);
			if (parcelas.intValue() > 10) {
				results.add(query + i + "x = " + new DecimalFormat("###,###.###").format(parcelas));
			}
		}
		return results;
	}

	public void finalizaCompra() {
		if(cupom.equals("cupom")) {
			total = armazena.subtract(new BigDecimal("10.00"));
			cupom_value = total;	
		}
				
		String message = "\nUsuario: "+usuario.getNome()+"\n";
		message += "Endereço: "+usuario.getEndereco()+"-"+usuario.getNumero()+"\n";
		message += "Parcela selecionada: "+parcela+"\n";
		message += cupom_value != null ? "Valor Total com Desconto: "+cupom_value : "Valor Total sem Desconto: "+somaTotal+"\n";
		msg.addMessage("Compra Confirmada",message,1);
	}
	
	public List<Carrinho> getProdutosCarrinho() {
		return produtosCarrinho;
	}

	public void setProdutosCarrinho(List<Carrinho> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
	}

	public CarrinhoService getCarrinhoService() {
		return carrinhoService;
	}

	public String getSomaTotal() {
		return somaTotal;
	}

	public void setSomaTotal(String somaTotal) {
		this.somaTotal = somaTotal;
	}

	public void setCarrinhoService(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUserService() {
		return userService;
	}

	public void setUserService(UsuarioService userService) {
		this.userService = userService;
	}

	public String getCupom() {
		return cupom;
	}

	public void setCupom(String cupom) {
		this.cupom = cupom;
	}

	public BigDecimal getCupom_value() {
		return cupom_value;
	}

	public void setCupom_value(BigDecimal cupom_value) {
		this.cupom_value = cupom_value;
	}

}

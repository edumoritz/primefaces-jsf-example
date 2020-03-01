package br.com.home.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class Carrinho implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private int quantidade;
	private BigDecimal valorTotal;
	private BigDecimal frete;
	private Produto produto;
	
	public Carrinho() {}
	
	public Carrinho(int quantidade, Produto produto) {
		this.id = getRandomId();
		this.quantidade = quantidade;		
		this.frete = new BigDecimal("0.05").multiply(produto.getValor()).multiply(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_EVEN);
		this.valorTotal = produto.getValor().multiply(new BigDecimal(quantidade)).add(frete).setScale(2, RoundingMode.HALF_EVEN);
		this.produto = produto;		
	}
	
	private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getFrete() {
		return frete;
	}
	public void setFrete(BigDecimal frete) {
		this.frete = frete;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package br.com.home.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String descricao;
	private BigDecimal valor;
	private List<String> detalhes;
	
	public Produto() {}	
	
	public Produto(String id, String descricao, BigDecimal valor, List<String> detalhes) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.detalhes = detalhes;
	}


	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<String> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<String> detalhes) {
		this.detalhes = detalhes;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
		
}

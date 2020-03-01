package br.com.home.service;

import javax.enterprise.context.ApplicationScoped;

import br.com.home.model.Usuario;

@ApplicationScoped
public class UsuarioService {
	
	private Usuario usuario;

	public Usuario createUsuario() {
		Usuario u = new Usuario("01", "Teste da Silva", "Rua das Palmeiras", 30);
		return u;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

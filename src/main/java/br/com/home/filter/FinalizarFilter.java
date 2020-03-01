package br.com.home.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.home.controller.ProdutoController;
/**
 * 
 * @author Eduardo Moritz
 * Bloqueia acesso a pagina de Finalização caso lista de carrinho estiver vazia.
 *
 */
public class FinalizarFilter implements Filter {
	
	@Inject
	private ProdutoController produtoController;

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request; 
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI().toString();
		if(url.contains("restricted") && produtoController.getProdutosCarrinho().isEmpty()) {
			res.sendRedirect(((FilterConfig) req).getServletContext().getContextPath()+"/paginas/Lista.xhtml");
		} else {
			filter.doFilter(request, response);
		}
		
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

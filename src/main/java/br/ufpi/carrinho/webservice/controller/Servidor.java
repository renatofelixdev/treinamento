/**
 * 
 */
package br.ufpi.carrinho.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.ufpi.carrinho.webservice.dao.ProdutoServidorDao;
import br.ufpi.carrinho.webservice.model.ProdutoServidor;
import br.ufpi.carrinho.webservice.to.ProdutoTO;

/**
 * @author gleison
 *
 */
@Path("/servidor")
public class Servidor {

	@Inject
	private ProdutoServidorDao produtoDao;
	private List<ProdutoServidor> produtosWeb;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/produtos")
	public List<ProdutoTO> produtos() {
		produtosWeb = produtoDao.listar();
		List<ProdutoTO> produtosTO = new ArrayList<>();
		for (ProdutoServidor produto : produtosWeb) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setId(produto.getId());
			produtoTO.setDescricao(produto.getDescricao());
			produtoTO.setPreco(produto.getPreco());
			produtoTO.setTipo(produto.getTipo());

			produtosTO.add(produtoTO);
		}
		return produtosTO;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/produto/{nome}")
	public List<ProdutoTO> produtoPorNome(@PathParam("nome") String nome) {
		List<ProdutoTO> produtosTO = new ArrayList<>();
		for (ProdutoServidor produto : produtosWeb) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setId(produto.getId());
			produtoTO.setDescricao(produto.getDescricao());
			produtoTO.setPreco(produto.getPreco());
			produtoTO.setTipo(produto.getTipo());

			produtosTO.add(produtoTO);
		}
		return produtosTO;
	}

}

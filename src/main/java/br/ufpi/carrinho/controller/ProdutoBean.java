package br.ufpi.carrinho.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufpi.carrinho.dao.ProdutoDao;
import br.ufpi.carrinho.model.Produto;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject private ProdutoDao produtoDao;
	private Produto produto;
	
	public ProdutoBean() {
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init()");
		produto = new Produto();
	}
	
	

}

package br.ufpi.carrinho.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufpi.carrinho.model.Cliente;
import br.ufpi.carrinho.util.Utils;

@Named
@SessionScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@PostConstruct
	public void init() {
		this.cliente = Utils.getUsuarioLogado();
	}

	
	
}

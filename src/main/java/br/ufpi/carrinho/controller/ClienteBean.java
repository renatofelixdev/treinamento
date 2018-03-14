/**
 * 
 */
package br.ufpi.carrinho.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufpi.carrinho.model.Cliente;

/**
 * @author Renato
 *
 */
@Named
@SessionScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

//	 @Inject
//	 private ClienteDao clienteDao;

	private Cliente cliente;

	private String email;
	private String senha;
	private String cpf;

	public ClienteBean() {
	}

	@PostConstruct
	public void init() {
		this.cliente = new Cliente();
	}
	
	public void salvar() {
		
	}

}

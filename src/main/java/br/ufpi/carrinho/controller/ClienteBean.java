/**
 * 
 */
package br.ufpi.carrinho.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufpi.carrinho.dao.ClienteDao;
import br.ufpi.carrinho.model.Cliente;
import br.ufpi.carrinho.util.Utils;

/**
 * @author Renato
 *
 */
@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDao clienteDao;

	private Cliente cliente;

	public ClienteBean() {
	}

	@PostConstruct
	public void init() {
		this.cliente = new Cliente();
	}
	
	public void popularBanco() {
		Cliente c = new Cliente();
		
		c.setCpf("00000000000");
		c.setNome("Gleison Andrade");
		c.setEmail("gleison@gmail.com");
		c.setSenha("123456");
		
		clienteDao.salvar(c);
	}
	
	public List<Cliente> lista(){
		return clienteDao.listar();
	}

	public void salvar() {
		String cpf = Utils.cpfSoComNumeros(cliente.getCpf());
		if (clienteDao.buscar(cpf) == null) {
			cliente.setCpf(cpf);
			clienteDao.salvar(cliente);
			cliente = new Cliente();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso!", "Cliente salvo com sucesso!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!", "Já existe um cliente com o cpf " + cpf));
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}

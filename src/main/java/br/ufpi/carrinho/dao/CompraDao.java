package br.ufpi.carrinho.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufpi.carrinho.model.Compra;

@Stateless
public class CompraDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	public CompraDao() {
	}
	
	public void salvar(Compra compra){
		em.merge(compra);
	}
	
	public void remover(Compra compra){
		em.remove(compra);
	}
}

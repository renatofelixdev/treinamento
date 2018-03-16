package br.ufpi.carrinho.webservice.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufpi.carrinho.webservice.model.ProdutoServidor;

@Stateless
public class ProdutoServidorDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="servidor")
	private EntityManager em;

	public void salvar(ProdutoServidor produto) {
		em.persist(produto);
	}

	public void atualizar(ProdutoServidor produto) {
		em.merge(produto);
	}

	public void remover(ProdutoServidor produto) {
		em.remove(produto);
	}

	public List<ProdutoServidor> listar() {
		TypedQuery<ProdutoServidor> query = em.createQuery("Select p from ProdutoServidor p", ProdutoServidor.class);
		return query.getResultList();
	}

	public ProdutoServidor buscar(Long id) {
		ProdutoServidor produto = null;

		try {
			TypedQuery<ProdutoServidor> query = em.createQuery("Select p from ProdutoServidor p WHERE p.id = :id", ProdutoServidor.class)
					.setParameter("id", id);
			produto = query.getSingleResult();

		} catch (Exception e) {
		}

		return produto;
	}

	public ProdutoServidor buscar(String descricao) {
		ProdutoServidor produto = null;

		try {
			TypedQuery<ProdutoServidor> query = em
					.createQuery("Select p from ProdutoServidor p WHERE p.descricao like :desc", ProdutoServidor.class)
					.setParameter("desc", "%" + descricao + "%");

			produto = query.getSingleResult();
		} catch (Exception e) {
		}

		return produto;
	}

}

package br.ufpi.carrinho.webservice.to;

/**
 * 
 */

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ufpi.carrinho.model.Tipo;

/**
 * @author gleison
 *
 */
public class ProdutoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private Double preco;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the preco
	 */
	public Double getPreco() {
		return preco;
	}

	/**
	 * @param preco
	 *            the preco to set
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * @return the tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}

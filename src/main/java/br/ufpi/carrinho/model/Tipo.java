/**
 * 
 */
package br.ufpi.carrinho.model;

/**
 * @author gleison
 *
 */
public enum Tipo {
	LIVROS("Livros"), MOVEIS("Móveis"), ELETRONICOS("Eletrônicos");
	
	private String descricao;
	
	private Tipo(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}

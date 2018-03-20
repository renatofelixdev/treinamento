package report;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufpi.carrinho.model.Produto;
import report.api.docx4j.Docx4jItemBuilder;
import report.api.docx4j.Docx4jReportBuilder;
import report.api.docx4j.TipoFonte;
import report.enums.Alinhamento;
import report.interfaces.IItemBuilder;
import report.interfaces.IRelatorioBuilder;
import report.model.IRelatorio;
import report.model.ItemColuna;
import report.model.ItemLinha;
import report.model.ItemParagrafo;
import report.model.ItemRelatorio;
import report.model.ItemTabela;

public class RelatorioDocx {
	
	public InputStream geraRelatorio(List<Produto> produtos) {
		IRelatorioBuilder relatorioBuilder = new Docx4jReportBuilder();
		IRelatorio r = new IRelatorio();
		IItemBuilder ib = new Docx4jItemBuilder();
		
		List<ItemRelatorio> itens = new ArrayList<ItemRelatorio>();
		
		itens.add(paragrafo(ib, "Lista de Produtos", true));
		itens.add(tabela(produtos, ib));
		
		r.getItensRelatorio().addAll(itens);
		
		InputStream is = relatorioBuilder.construirRelatorio(r);
		
		return is;
	}
	
	private ItemTabela tabela(List<Produto> produtos, IItemBuilder ib) {
		ItemTabela it = new ItemTabela(ib);
		it.addLinha(cabecalho(ib));
		
		for(Produto produto : produtos) {
			ItemLinha linha = linha();
			linha.addColuna(coluna(produto.getDescricao(), ib));
			linha.addColuna(coluna(produto.getTipo().toString(), ib));
			linha.addColuna(coluna(produto.getPreco()+"", ib));
			it.addLinha(linha);
		}
				
		return it;
	}
	
	private ItemLinha cabecalho(IItemBuilder ib) {
		String corCabecalho = "ececec";
		ItemLinha il = new ItemLinha();
		
		ItemColuna c1 = new ItemColuna(); 			
		c1.getItensRelatorio().add(paragrafo(ib, "DESCRIÇÃO", true));
		c1.setCorDeFundo(corCabecalho);

		ItemColuna c2 = new ItemColuna();
		c2.getItensRelatorio().add(paragrafo(ib, "TIPO", true));
		c2.setCorDeFundo(corCabecalho);

		ItemColuna c3 = new ItemColuna();
		c3.getItensRelatorio().add(paragrafo(ib, "PREÇO", true));
		c3.setCorDeFundo(corCabecalho);
		
		il.addAllColunas(Arrays.asList(c1, c2, c3));
		return il;
	}
	
	private ItemLinha linha() {
		ItemLinha il = new ItemLinha();
		return il;
	}
	
	private ItemColuna coluna(String conteudo, IItemBuilder ib) {
		ItemColuna ic = new ItemColuna();
		ic.getItensRelatorio().add(paragrafo(ib, conteudo, false));
		return ic;
	}
	
	private ItemRelatorio paragrafo(IItemBuilder ib, String conteudo, boolean negrito) {
		ItemParagrafo itemPara = new ItemParagrafo(ib);
		itemPara.setConteudo(conteudo);
		itemPara.setAlinhamento(Alinhamento.CENTRO);
		itemPara.setFonte(TipoFonte.ARIAL);
		itemPara.setTamanhoFonte(12);
		itemPara.setNegrito(negrito);
		return itemPara;	
	}
	
	

}

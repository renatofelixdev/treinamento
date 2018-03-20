package br.ufpi.carrinho.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.docx4j.org.apache.poi.util.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.ufpi.carrinho.dao.ProdutoDao;
import br.ufpi.carrinho.model.Produto;
import br.ufpi.carrinho.model.Tipo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import report.RelatorioDocx;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject private ProdutoDao produtoDao;
	private Produto produto;
	
	private StreamedContent file;
	
	public ProdutoBean() {
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init()");
		produto = new Produto();
	}
	
	public void salvaProduto() {
		produtoDao.atualizar(produto);
		produto = new Produto();
	}
	
	public void editar(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> listarProdutos(){
		return produtoDao.listar();
	}
	
	public byte[] gerarRelatorio() {
		List<Produto> produtos = produtoDao.listar();
		JRDataSource datasource = new JRBeanCollectionDataSource(produtos);

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			
			FileInputStream fileInputStream = (FileInputStream) 
					Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("produtos_relatorio.jrxml");
			
			JasperReport jasperReport = JasperCompileManager.compileReport(fileInputStream);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, datasource);
			
			return JasperExportManager.exportReportToPdf(jasperPrint);

			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
	}
	
	public StreamedContent downloadRelatorio(){
		try {
			byte[] contentReport =  gerarRelatorio();
			
			InputStream is = new ByteArrayInputStream(contentReport);
			
						
			return new DefaultStreamedContent(is, "application/pdf", "RelatorioProdutos.pdf");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Tipo[] tipos() {
		return Tipo.values();
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	public void geraRelatorio() {
		RelatorioDocx relatorio = new RelatorioDocx();
		
		InputStream is = relatorio.geraRelatorio(produtoDao.listar());
		byte[] conteudoRelatorio = null;
		try {
			conteudoRelatorio = IOUtils.toByteArray(is);
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("erro no relatorio");
		}

		this.file =  new DefaultStreamedContent(
				new ByteArrayInputStream(conteudoRelatorio),
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
				"ListadeProdutos.docx");
		System.out.println("gerou relatorio");
	}

}

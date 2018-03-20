package br.ufpi.carrinho.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.tiefaces.components.websheet.TieWebSheetBean;

import br.ufpi.carrinho.dao.ProdutoDao;
import br.ufpi.carrinho.model.Produto;

@Named
@ViewScoped
public class PlanilhaProdutoBean extends TieWebSheetBean {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private ProdutoDao produtoDao;

    @Override
    public void initialLoad()  {
        Map<String, Object> context = new HashMap<String, Object>();
        List<Produto> produtos = produtoDao.listar();
        context.put("produtos", produtos);
        InputStream stream = getFileContent("listaprodutos.xlsx");
        loadWebSheet(stream, context);        
    }
    
    private InputStream getFileContent(String fileName) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		InputStream in = externalContext.getResourceAsStream("resources" + System.getProperty( "file.separator" ) + fileName);

		return in;
	}
}

package br.ufpi.carrinho.listeners;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.envers.RevisionListener;

import br.ufpi.carrinho.model.AuditEntity;
import br.ufpi.carrinho.model.Cliente;

public class AuditListener implements RevisionListener {
	
	@Override
	public void newRevision(Object revisionEntity) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.
				getExternalContext().getSession(true);
		HttpServletRequest request = 
				(HttpServletRequest) context.getExternalContext()
				.getRequest();

		Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");

		AuditEntity revEntity = (AuditEntity) revisionEntity;

		revEntity.setUsuario(cliente.getNome());
		revEntity.setIp(request.getRemoteAddr());
	}
}
package br.ufpi.carrinho.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.faces.context.FacesContext;

import br.ufpi.carrinho.model.Cliente;

public class Utils {
	
	public static String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {

			// Instanciamos o nosso HASH MD5, poderíamos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior comparação se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String cpfSoComNumeros(String cpf) {
		return cpf.replaceAll("\\.", "").replaceAll("\\-", "");
	}
	
	public static Cliente getUsuarioLogado() {
		Map<String, Object> sessionMap = FacesContext
											.getCurrentInstance()
											.getExternalContext()
											.getSessionMap();

		return (Cliente) sessionMap.get("usuarioLogado");
	}
	
	public static void salvaUsuarioNaSessao(Cliente cliente) {
		Map<String, Object> sessionMap = FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap();

		sessionMap.put("usuarioLogado", cliente);
	}

}

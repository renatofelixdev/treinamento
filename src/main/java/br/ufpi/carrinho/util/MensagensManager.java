package br.ufpi.carrinho.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensagensManager {
	public static String readMessage(String key) {
		ResourceBundle bundle;
		bundle = ResourceBundle.getBundle("mensagens", 
				new Locale("pt_br"));
		return bundle.getString(key);
	}
}
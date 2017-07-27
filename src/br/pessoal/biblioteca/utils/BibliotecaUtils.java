package br.pessoal.biblioteca.utils;

import java.util.ResourceBundle;

public class BibliotecaUtils {

	public static ResourceBundle getConfig() {
		ResourceBundle bundle = ResourceBundle.getBundle("br.pessoal.biblioteca.resource.Mensagem");
		return bundle;
	}
	
	public static String getProperty(String key) {
		return getConfig().getString(key);
	}
}

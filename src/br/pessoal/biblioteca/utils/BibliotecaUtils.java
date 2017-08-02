package br.pessoal.biblioteca.utils;

import java.util.ResourceBundle;

import br.pessoal.biblioteca.to.UsuarioTO;

public class BibliotecaUtils {

	public static ResourceBundle getConfig() {
		ResourceBundle bundle = ResourceBundle.getBundle("br.pessoal.biblioteca.resource.Mensagem");
		return bundle;
	}
	
	public static String getProperty(String key) {
		return getConfig().getString(key);
	}
	
	public static String getTipoEndereco(String tipo) {
		switch (tipo) {
		case "Alameda":
			return "Al";			
		case "Avenida":
			return "Av";	
		case "Beco":
			return "B";
		case "Estrada":
			return "Est";
		case "Rodovia":
			return "Rod";
		case "Rua":
			return "R";
		case "Travessa":
			return "Tv";
		default:
			return null;
		}
	}
	
	public static String setTipoEndereco(String tipo) {
		switch (tipo.trim()) {
		case "AL":
			return "Alameda";			
		case "AV":
			return "Avenida";	
		case "B":
			return "Beco";
		case "EST":
			return "Estrada";
		case "ROD":
			return "Rodovia";
		case "R":
			return "Rua";
		case "TV":
			return "Travessa";
		default:
			return null;
		}
	}
	
	public static long removeMascaraTelefoneCelular(String numero) {
		
			numero = numero.replace("(", "");
			numero = numero.replace(")", "");
			numero = numero.replace("-", "");
		return Long.parseLong(numero);		
	}
	
	public static String adicionaMascaraTelefoneCelular(String numero) {
		String numeroFormatado = numero;
		if (numero.length() == 11) {
			numeroFormatado = String.format("(%s)%s-%s", numero.substring(0, 2), numero.substring(2, 6), numero.substring(6, 11));
		}else {
			numeroFormatado = String.format("(%s)%s-%s", numero.substring(0, 2), numero.substring(2, 6), numero.substring(6, 10));
		}
		return numeroFormatado;
	}
	
	public static boolean validarTelefone(String numero) {
		return ((numero.length() == 14 || numero.length() == 13) && numero.charAt(0)=='(' && numero.charAt(3)==')' && numero.charAt(8)=='-');
	}
	
	
	public static String compararUsuarios(UsuarioTO usuarioPrincipal, UsuarioTO usuarioTemporario) {					
		String camposModificados="";
		
		if(!usuarioPrincipal.getNome().trim().equals(usuarioTemporario.getNome())) {
			camposModificados += "#_Nome\n";
		}
		if (!usuarioPrincipal.getUltimoNome().trim().equals(usuarioTemporario.getUltimoNome())) {
			camposModificados += "#_Sobrenome\n";
		}
		if (!usuarioPrincipal.getTipoLogradouro().trim().equals(usuarioTemporario.getTipoLogradouro())) {
			camposModificados += "#_Tipo de endere�o\n";
		}
		if (!usuarioPrincipal.getLogradouro().trim().equals(usuarioTemporario.getLogradouro())) {
			camposModificados += "#_Endere�o\n";
		}
		if (!usuarioPrincipal.getComplLogradouro().trim().equals(usuarioTemporario.getComplLogradouro())) {
			camposModificados += "#_Complemento de Endere�o\n";
		}
		if(!(usuarioPrincipal.getTelefone() == usuarioTemporario.getTelefone())) {
			camposModificados += "#_Telefone\n";
		}
		if (!usuarioPrincipal.getEmail().trim().equals(usuarioTemporario.getEmail())) {
			camposModificados += "#_Email\n";
		}
		return camposModificados;
	}	
}

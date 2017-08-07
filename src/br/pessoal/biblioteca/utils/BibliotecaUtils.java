package br.pessoal.biblioteca.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.to.EmprestimoTO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.to.UsuarioTO;
import javafx.collections.ObservableList;

public class BibliotecaUtils {

	private static final String DATE="dd-MMMM-yyyy";
	private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern(DATE);
	
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
	
	public static boolean checkarEmprestimos(ObservableList<EmprestimoTO> emprestimos) {
		for(EmprestimoTO emprestimoDaVez: emprestimos) {
			if (emprestimoDaVez.getDataDevolução().isBefore(LocalDate.now())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Futuramente mostrar os campos que foram modificados
	 * 
	 * @param usuarioPrincipal
	 * @param usuarioTemporario
	 * @return
	 */
	public static String compararUsuarios(UsuarioTO usuarioPrincipal, UsuarioTO usuarioTemporario) {					
		String camposModificados="";
		
		if(!usuarioPrincipal.getNome().trim().equals(usuarioTemporario.getNome())) {
			camposModificados += "#_Nome\n";
		}
		if (!usuarioPrincipal.getUltimoNome().trim().equals(usuarioTemporario.getUltimoNome())) {
			camposModificados += "#_Sobrenome\n";
		}
		if (!usuarioPrincipal.getTipoLogradouro().trim().equals(usuarioTemporario.getTipoLogradouro())) {
			camposModificados += "#_Tipo de endereço\n";
		}
		if (!usuarioPrincipal.getLogradouro().trim().equals(usuarioTemporario.getLogradouro())) {
			camposModificados += "#_Endereço\n";
		}
		if (!usuarioPrincipal.getComplLogradouro().trim().equals(usuarioTemporario.getComplLogradouro())) {
			camposModificados += "#_Complemento de Endereço\n";
		}
		if(!(usuarioPrincipal.getTelefone() == usuarioTemporario.getTelefone())) {
			camposModificados += "#_Telefone\n";
		}
		if (!usuarioPrincipal.getEmail().trim().equals(usuarioTemporario.getEmail())) {
			camposModificados += "#_Email\n";
		}
		return camposModificados;
	}	
	
	/**
	 * Futuramente mostrar os campos que foram modificados
	 * 
	 * @param livroPrincipal
	 * @param livroTemporario
	 * @return
	 */
	public static String compararLivros(LivroTO livroPrincipal, LivroTO livroTemporario) {					
		String camposModificados="";
		
		if(!livroPrincipal.getNomeLivro().trim().equals(livroTemporario.getNomeLivro().trim())) {
			camposModificados += "#_Nome\n";
		}
		if (!livroPrincipal.getDescricao().trim().equals(livroTemporario.getDescricao().trim())) {
			camposModificados += "#_Descrição\n";
		}
		if (!livroPrincipal.getAutor().trim().equals(livroTemporario.getAutor().trim())) {
			camposModificados += "#_Autor\n";
		}
		if (!livroPrincipal.getEditora().trim().equals(livroTemporario.getEditora().trim())) {
			camposModificados += "#_Editora\n";
		}
		if (!livroPrincipal.getTipo().trim().equals(livroTemporario.getTipo().trim())) {
			camposModificados += "#_Tipo\n";
		}
		if(!(livroPrincipal.getEdicao() == livroTemporario.getEdicao())) {
			camposModificados += "#_Edição\n";
		}
		if (!(livroPrincipal.getPublicacao().equals(livroTemporario.getPublicacao()))) {
			camposModificados +="#_Publicação\n";
		}
		if (!(livroPrincipal.getCircular() == livroTemporario.getCircular())) {
			camposModificados +="#_Circular";
		}
		return camposModificados;
	}
	
	public static LivroTO livroEscolhido(int idLivro, Main main) {
		ObservableList<LivroTO> acervo = main.getLivros();
		for (LivroTO livroDaVez : acervo) {
			if (idLivro == livroDaVez.getIdLivro()) {
				return livroDaVez;
			}
		}
		return null;
	}
	
	public static String formatarData(LocalDate date) {
		return DATE_FORMATER.format(date);
	}
	
	public static String setTipoMaterial(String tipo) {
		switch (tipo) {
		case "L":
			return "Livro";
		case "M":
			return "Multimidia";
		default:
			return "Revista";
		}
	}
	
	public static String getTipoMaterial(String tipo) {
		switch (tipo.trim()) {
		case "Livro":
			return "L";			
		case "Revista":
			return "R";	
		case "Multimidia":
			return "M";		
		default:
			return null;
		}
	}
	
	public static String setTipoUsuario(String tipo) {
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
}

package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.to.LivroTO;

public class LivroDAO {

	private Connection connection;
	Logger logger = Logger.getLogger(LivroDAO.class.getName());
	
	public LivroDAO() {	
		this.connection = new ConnectionFactory().getConnection();
	}	
	
	public void listarLivrosAtrasados(int idLivro, Main main) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome_do_livro, ")
			.append("nome_do_livro, ")
			.append("autor, ")
			.append("publicacao, ")
			.append("circ, ")
			.append("edicao, ")
			.append("editora, ")
			.append("tipo ")
			.append("FROM acervo ")
			.append("WHERE id_acervo = ?");
		try {
			PreparedStatement stm = connection.prepareStatement(sql.toString());
			stm.setInt(1, idLivro);
			stm.execute();
			
			ResultSet rs = stm.executeQuery();
			logger.log(Level.INFO, stm.toString());
			
			while (rs.next()) {
				LivroTO livro = new LivroTO();
				/**
				 * Colocar os atributos do livro para ser preenchido
				 * e adicionar a lista de livros atrasados que esta no Main
				 */
			}
		}catch (SQLException e) {
			 logger.log(Level.SEVERE, "Erro ao buscar acervo atrasado ",e);
		}
	}
}

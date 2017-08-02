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
	private PreparedStatement stm;
	private ResultSet rs;
	
	Logger logger = Logger.getLogger(LivroDAO.class.getName());
	
	public LivroDAO() {	
		this.connection = new ConnectionFactory().getConnection();
	}	
	
	public void listarLivrosAtrasados(int idLivro, Main main) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome_do_livro, ")
			.append("autor, ")
			.append("publicacao, ")
			.append("circ, ")
			.append("edicao, ")
			.append("editora, ")
			.append("tipo ")
			.append("FROM acervo ")
			.append("WHERE id_acervo = ?");
		try {
			this.stm = connection.prepareStatement(sql.toString());
			this.stm.setInt(1, idLivro);
			this.stm.execute();
			
			this.rs = stm.executeQuery();
			logger.log(Level.INFO, this.stm.toString());
			
			if (rs.next()) {
				LivroTO livroTO = new LivroTO();
				livroTO.setIdLivro(idLivro);
				livroTO.setNomeLivro(this.rs.getString(1));
				livroTO.setAutor(this.rs.getString(2));
				livroTO.setPublicacao(this.rs.getDate(3).toLocalDate());
				livroTO.setCircular(this.rs.getBoolean(4));
				livroTO.setEdicao(this.rs.getInt(5));
				livroTO.setEditora(this.rs.getString(6));
				livroTO.setTipo(this.rs.getString(7));
				main.getLivrosAtrasados().add(livroTO);
			}
		}catch (SQLException e) {
			 logger.log(Level.SEVERE, "Erro ao buscar acervo atrasado ",e);
		}
	}
}

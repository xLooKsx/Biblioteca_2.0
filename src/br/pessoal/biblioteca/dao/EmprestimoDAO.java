package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.controller.Main;

public class EmprestimoDAO {

	private Connection connection;
	Logger logger = Logger.getLogger(EmprestimoDAO.class.getName());
	
	public EmprestimoDAO() {		
		this.connection = new ConnectionFactory().getConnection();
	}	
	
	public void buscarEmprestimosAtrasados(Main main) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT acervo_idacervo ")
			.append("FROM emprestimo ")
			.append("WHERE usuarios_matricula = ? ")
			.append("AND encerrado = false");
		try {		
			PreparedStatement stm = connection.prepareStatement(sql.toString());
			stm.setInt(1, main.getUsuarioTO().getMatricula());
			stm.execute();
			
			ResultSet rs = stm.executeQuery();
			logger.log(Level.INFO, stm.toString());
			
			while (rs.next()) {
				new LivroDAO().listarLivrosAtrasados(rs.getInt(1), main);				
			}
		}catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao procurar emprestimos atrasados ", e);
		}
	}
}

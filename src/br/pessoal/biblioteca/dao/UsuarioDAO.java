package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

	private Connection connection;
	private PreparedStatement stm;
	private ResultSet rs;
	
	Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

	public UsuarioDAO() {		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void alterarSenha(int usuario, String senha) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios ")
				.append("SET senha=? ")
				.append("WHERE matricula=?");
			
			this.stm = this.connection.prepareStatement(sql.toString());			
			this.stm.setString(1, senha);
			this.stm.setInt(2, usuario);
			this.stm.execute();					
			
			logger.log(Level.INFO, stm.toString());
		}catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao alterar a senha do usuario ", e);
		}
	}
	
}

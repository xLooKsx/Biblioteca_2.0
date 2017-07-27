package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.to.UsuarioTO;

public class LoginDAO {
	
	private Connection connection;
	Logger logger = Logger.getLogger(LoginDAO.class.getName());

	public LoginDAO() {	
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public UsuarioTO buscarUsuario(int usuario) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT matricula,")
				.append("nome,")
				.append("is_ativa,")
				.append("tipo_usuario,")
				.append("qtd_livro,")
				.append("qtd_revista")
				.append(" FROM usuarios WHERE matricula = ? and is_ativa = true");
			
			PreparedStatement stm = this.connection.prepareStatement(sql.toString());
			
			stm.setInt(1, usuario);
			stm.execute();
			ResultSet rs = stm.executeQuery();
			
			logger.info(stm.toString());
			UsuarioTO usuarioTO = new UsuarioTO();
			if (rs.next()) {							
				usuarioTO.setMatricula(rs.getInt(1));
				usuarioTO.setNome(rs.getString(2));
				usuarioTO.setContaAtiva(rs.getBoolean(3));
				usuarioTO.setTipoUsuario(rs.getString(4));
				usuarioTO.setQtdLivro(rs.getInt(5));
				usuarioTO.setQtdRevista(rs.getInt(6));
			}
			rs.close();
			stm.close();
			
			return usuarioTO;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
	}
}

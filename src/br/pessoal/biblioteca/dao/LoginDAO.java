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
	private PreparedStatement stm;
	private ResultSet rs;
	
	Logger logger = Logger.getLogger(LoginDAO.class.getName());

	public LoginDAO() {	
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public UsuarioTO buscarUsuario(int usuario, String senha) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT matricula,")
				.append(" senha,")
				.append(" nome,")
				.append(" is_ativa,")
				.append(" is_biblioteca,")
				.append(" tipo_usuario,")
				.append(" qtd_livro,")
				.append(" qtd_revista")
				.append(" FROM usuarios WHERE matricula = ? and senha = ?");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			
			this.stm.setInt(1, usuario);
			this.stm.setString(2, senha);
			this.stm.execute();
			
			this.rs = this.stm.executeQuery();
			
			logger.info(this.stm.toString());
			UsuarioTO usuarioTO = new UsuarioTO();
			if (rs.next()) {							
				usuarioTO.setMatricula(rs.getInt(1));
				usuarioTO.setSenha(rs.getString(2));
				usuarioTO.setNome(rs.getString(3));
				usuarioTO.setContaAtiva(rs.getBoolean(4));
				usuarioTO.setBibliotecario(rs.getBoolean(5));
				usuarioTO.setTipoUsuario(rs.getString(6));
				usuarioTO.setQtdLivro(rs.getInt(7));
				usuarioTO.setQtdRevista(rs.getInt(8));
			}
			this.rs.close();
			this.stm.close();
			
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
	
	public boolean validarUsuario(String nome, String sobrenome, int usuario) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT senha")
				.append(" FROM usuarios")
				.append(" WHERE matricula=? and is_ativa=true and nome=? and ultimo_nome=?");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			
			this.stm.setInt(1, usuario);
			this.stm.setString(2, nome.toUpperCase().trim().trim());
			this.stm.setString(3, sobrenome.toUpperCase().trim().trim());
			this.stm.execute();
			
			this.rs = this.stm.executeQuery();
			
			logger.log(Level.INFO, this.stm.toString());			
			
			return this.rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stm.close();
				connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void alterarSenha(String nome, String sobrenome, String senha, int usuario) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios")
				.append(" SET senha = ?")
				.append(" WHERE matricula=? and nome=? and ultimo_nome=?");
						
			this.stm = this.connection.prepareStatement(sql.toString());
			
			this.stm.setString(1, senha);
			this.stm.setInt(2, usuario);
			this.stm.setString(3, nome.toUpperCase());
			this.stm.setString(4, sobrenome.toUpperCase());
			this.stm.execute();
			
			logger.log(Level.INFO, this.stm.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
				connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
}

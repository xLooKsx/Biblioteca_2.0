package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.to.UsuarioTO;

public class UsuarioDAO {

	private Connection connection;
	private PreparedStatement stm;	
	
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
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public void alteracaoDadosPessoais(UsuarioTO usuarioTO) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios ")
				.append("SET nome=?, ")
				.append("ultimo_nome=?, ")
				.append("logradouro=?, ")
				.append("tipo_logradouro=?, ")
				.append("comp_logradouro=?, ")
				.append("telefone=?, ")
				.append("email=? ")
				.append("where matricula=?");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			this.stm.setString(1, usuarioTO.getNome().toUpperCase());
			this.stm.setString(2, usuarioTO.getUltimoNome().toUpperCase());
			this.stm.setString(3, usuarioTO.getLogradouro().toUpperCase());
			this.stm.setString(4, usuarioTO.getTipoLogradouro().toUpperCase());
			this.stm.setString(5, usuarioTO.getComplLogradouro().toUpperCase());
			this.stm.setLong(6, usuarioTO.getTelefone());
			this.stm.setString(7, usuarioTO.getEmail().toUpperCase());
			this.stm.setInt(8, usuarioTO.getMatricula());
			
			this.stm.execute();
			logger.log(Level.INFO, stm.toString());
		}catch (SQLException e) {
				logger.log(Level.SEVERE, "Erro ao alterar dados pessoais do usuario ",e); 
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public void adicionarUsuario(UsuarioTO usuarioTO) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO usuarios(")
				.append("matricula, ")
				.append("senha, ")
				.append("nome, ")
				.append("ultimo_nome, ")
				.append("logradouro, ")
				.append("tipo_logradouro, ")
				.append("comp_logradouro, ")
				.append("telefone, ")
				.append("email, ")
				.append("is_ativa,")
				.append("is_biblioteca, ")
				.append("tipo_usuario, ")
				.append("qtd_livro, ")
				.append("qtd_revista) ")
				.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, true, ?, ?, 0, 0)");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			this.stm.setInt(1, usuarioTO.getMatricula());
			this.stm.setString(2, usuarioTO.getSenha());
			this.stm.setString(3, usuarioTO.getNome().toUpperCase());
			this.stm.setString(4, usuarioTO.getUltimoNome().toUpperCase());
			this.stm.setString(5, usuarioTO.getLogradouro().toUpperCase());
			this.stm.setString(6, usuarioTO.getTipoLogradouro().toUpperCase());
			this.stm.setString(7, usuarioTO.getComplLogradouro().toUpperCase());
			this.stm.setLong(8, usuarioTO.getTelefone());
			this.stm.setString(9, usuarioTO.getEmail().toUpperCase());
			this.stm.setBoolean(10, usuarioTO.getBibliotecario());
			this.stm.setString(11, usuarioTO.getTipoUsuario().toUpperCase());
			
			this.stm.execute();
			logger.log(Level.INFO, this.stm.toString());
		} catch (SQLException e) {
			 logger.log(Level.SEVERE, "Erro ao cadastrar novo usuario ", e);
		}
	}
}

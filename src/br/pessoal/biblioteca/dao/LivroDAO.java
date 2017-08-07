package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.to.LivroTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LivroDAO {

	private Connection connection;
	private PreparedStatement stm;
	private ResultSet rs;
	
	Logger logger = Logger.getLogger(LivroDAO.class.getName());
	
	public LivroDAO() {	
		this.connection = new ConnectionFactory().getConnection();
	}	
	
	public ObservableList<LivroTO> acervo(){		
		try {
			
			ObservableList<LivroTO> acervo = FXCollections.observableArrayList();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ")
				.append("FROM acervo");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			this.rs = this.stm.executeQuery();
			
			logger.log(Level.INFO, this.stm.toString());
			
			while (this.rs.next()) {
				LivroTO livroTO = new LivroTO();
				livroTO.setIdLivro(this.rs.getInt(1));
				livroTO.setNomeLivro(this.rs.getString(2));
				livroTO.setDescricao(this.rs.getString(3));
				livroTO.setAutor(this.rs.getString(4));
				livroTO.setPublicacao(this.rs.getDate(5).toLocalDate());
				livroTO.setCircular(this.rs.getBoolean(6));
				livroTO.setEdicao(this.rs.getInt(7));
				livroTO.setEditora(this.rs.getString(8));
				livroTO.setEmprestado(this.rs.getBoolean(9));
				livroTO.setTipo(this.rs.getString(10));
				livroTO.setReservado(this.rs.getBoolean(11));
				acervo.add(livroTO);
			}
			
			return acervo;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar acervo: "+e);
		}finally {
			try {
				this.rs.close();
				this.stm.close();				
				this.connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}			
	}
	
	public ObservableList<Integer> buscaDeLivro(String condicao){
		
		ObservableList<Integer> livrosBuscados = FXCollections.observableArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_acervo ")
			.append("FROM acervo ")
			.append("where nome_do_livro LIKE '%"+condicao+"%'");
		try {
			this.stm = this.connection.prepareStatement(sql.toString());
			this.stm.execute();
			
			this.rs = this.stm.executeQuery();
			logger.log(Level.INFO, this.stm.toString());
			
			while(this.rs.next()) {
				Integer idLivro = this.rs.getInt(1);
				livrosBuscados.add(idLivro);
			}
			return livrosBuscados;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao realizar a busca do livro: "+e);
		}finally {
			try {
				this.rs.close();
				this.stm.close();
				this.connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}		
	}
	
	public void adicionarLivro(LivroTO livroTO) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO acervo(")
				.append("id_acervo, ")
				.append("nome_do_livro, ")
				.append("descricao, ")
				.append("autor, ")
				.append("publicacao, ")
				.append("circ, ")
				.append("edicao, ")
				.append("editora, ")
				.append("is_emprestado, ")
				.append("tipo, ")
				.append("reservado)")
				.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, false, ?, false)");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			this.stm.setInt(1, livroTO.getIdLivro());
			this.stm.setString(2, livroTO.getNomeLivro());
			this.stm.setString(3, livroTO.getDescricao());
			this.stm.setString(4, livroTO.getAutor());
			this.stm.setDate(5, Date.valueOf(livroTO.getPublicacao()));
			this.stm.setBoolean(6, livroTO.getCircular());
			this.stm.setInt(7, livroTO.getEdicao());
			this.stm.setString(8, livroTO.getEditora());
			this.stm.setString(9, livroTO.getTipo());
			
			this.stm.execute();
			logger.log(Level.INFO, this.stm.toString());
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao cadastrar livro ", e);
		}finally {
			try {
				this.stm.close();
				this.connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}		
	}
	
	public void alterarLivro(LivroTO livroTO) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE acervo ")
				.append("SET nome_do_livro=?, ")
				.append("descricao=?, ")
				.append("autor=?, ")
				.append("publicacao=?, ")
				.append("editora=?, ")
				.append("tipo=? ")
				.append("WHERE id_acervo=?");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			this.stm.setString(1, livroTO.getNomeLivro().toUpperCase());
			this.stm.setString(2, livroTO.getDescricao().toUpperCase());
			this.stm.setString(3, livroTO.getAutor().toUpperCase());
			this.stm.setDate(4, Date.valueOf(livroTO.getPublicacao()));
			this.stm.setString(5, livroTO.getEditora().toUpperCase());
			this.stm.setString(6, livroTO.getTipo().toUpperCase());
			this.stm.setInt(7, livroTO.getIdLivro());
			
			this.stm.execute();
			logger.log(Level.INFO, this.stm.toString());
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao alterar livro ", e);
		}finally {
			try {
				this.stm.close();
				this.connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public void deletarLivro(int idLivro) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE from acervo ")
				.append("WHERE id_acervo = ?");
			
			this.stm = this.connection.prepareStatement(sql.toString());
			this.stm.setInt(1, idLivro);
			
			this.stm.execute();
			logger.log(Level.INFO, this.stm.toString());
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erro ao alterar Livro ", e);
		}
	}
}

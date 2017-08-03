package br.pessoal.biblioteca.dao;

import java.sql.Connection;
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
		}		
	}
}

package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.to.EmprestimoTO;
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;		
	}
}

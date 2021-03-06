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

public class EmprestimoDAO {

	private Connection connection;
	private PreparedStatement stm;
	private ResultSet rs;
	
	Logger logger = Logger.getLogger(EmprestimoDAO.class.getName());
	
	public EmprestimoDAO() {		
		this.connection = new ConnectionFactory().getConnection();
	}		
	
	public void listarEmprestimos(int usuario, Main main) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.id_acervo, ")
			.append("a.nome_do_livro, ")
			.append("a.autor, ")
			.append("a.publicacao, ")
			.append("a.circ, ")
			.append("a.edicao, ")
			.append("a.editora, ")
			.append("a.tipo, ")
			.append("b.idemprestimo, ")
			.append("b.data_emprestimo, ")
			.append("b.data_devolucao ")
			.append("FROM acervo ")
			.append("as A INNER JOIN ")
			.append("emprestimo as B on ")
			.append("b.encerrado = false AND ")
			.append("b.data_devolucao < ? and ")
			.append("b.usuarios_matricula = ? and ")
			.append("b.acervo_idacervo = a.id_acervo");
		try {
			this.stm = connection.prepareStatement(sql.toString());
			this.stm.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			this.stm.setInt(2, usuario);
			this.stm.execute();
			
			this.rs = stm.executeQuery();
			logger.log(Level.INFO, this.stm.toString());
			
			while (rs.next()) {
				
				EmprestimoTO emprestimoTO = new EmprestimoTO();
				emprestimoTO.setIdLivro(this.rs.getInt(1));
				emprestimoTO.setNomeLivro(this.rs.getString(2));
				emprestimoTO.setIdEmprestimo(this.rs.getInt(9));
				emprestimoTO.setDataEmprestimo(this.rs.getDate(10).toLocalDate());
				emprestimoTO.setDataDevolu��o(this.rs.getDate(11).toLocalDate());
				
				main.getEmprestimos().add(emprestimoTO);
			}
		}catch (SQLException e) {
			 logger.log(Level.SEVERE, "Erro ao buscar acervo atrasado ",e);
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
	
	public void finalizaEmprestimo(int idAcervo, int matriculaUsuario) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE emprestimo ")
			.append("SET encerrado = true ")
			.append("WHERE acervo_idacervo=? AND ")
			.append("usuarios_matricula=?");
		
		try {
			this.stm = this.connection.prepareStatement(sql.toString());
			this.stm.setInt(1, idAcervo);
			this.stm.setInt(2, matriculaUsuario);
			this.stm.execute();
			
			this.logger.log(Level.INFO, this.stm.toString());
		} catch (SQLException e) {
			this.logger.log(Level.SEVERE, "Erro ao finalizar o emprestimo selecionado ", e);
		}finally {
			try {
				this.stm.close();
				this.connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
}

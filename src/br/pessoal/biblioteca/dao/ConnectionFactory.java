package br.pessoal.biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.pessoal.biblioteca.utils.BibliotecaUtils;

public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			Class.forName(BibliotecaUtils.getProperty("config.bd.class"));
			return DriverManager.getConnection(BibliotecaUtils.getProperty("config.bd.conexao"), "postgres", "admin");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}catch (ClassNotFoundException e) {
			 throw new RuntimeException(e);
		}
	}
}

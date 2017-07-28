package br.pessoal.biblioteca.to;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LivroTO {
	
	private IntegerProperty idLivro;
	private IntegerProperty edicao;
	
	private StringProperty nomeLivro;
	private StringProperty descricao;
	private StringProperty autor;
	private StringProperty editora;
	private StringProperty tipo;
	
	private BooleanProperty circular;
	private BooleanProperty emprestado;
	private BooleanProperty reservado;
	
	private ObjectProperty<LocalDate> publicacao;

	public LivroTO() {
		this(0, null, null, null, 0, null, null, false, false, false, null);
	}

	public LivroTO(int idLivro, String nomeLivro, String descricao, String autor, Integer edicao, String editora, 
			String tipo, boolean circular, boolean emprestado, boolean reservado, LocalDate publicacao) {
		
		this.idLivro = new SimpleIntegerProperty(idLivro);
		this.nomeLivro = new SimpleStringProperty(nomeLivro);
		this.descricao = new SimpleStringProperty(descricao);
		this.autor = new SimpleStringProperty(autor);
		this.edicao = new SimpleIntegerProperty(edicao);
		this.editora = new SimpleStringProperty(editora);
		this.tipo = new SimpleStringProperty(tipo);
		this.circular = new SimpleBooleanProperty(circular);
		this.emprestado = new SimpleBooleanProperty(emprestado);
		this.reservado = new SimpleBooleanProperty(reservado);
		this.publicacao = new SimpleObjectProperty<LocalDate>(publicacao);
	}
	
	

	
}

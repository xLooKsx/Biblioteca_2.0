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
	
	public int getIdLivro() {
		return idLivro.get();
	}
	public void setIdLivro(int idLivro) {
		this.idLivro.set(idLivro);
	}
	public IntegerProperty idLivroProperty() {
		return this.idLivro;
	}
		
	public int getEdicao() {
		return edicao.get();
	}
	public void setEdicao(int edicao) {
		this.edicao.set(edicao);
	}
	public IntegerProperty edicaoProperty() {
		return this.edicao;
	}
	
	public String getNomeLivro() {
		return nomeLivro.get();
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro.set(nomeLivro);
	}
	public StringProperty nomeLivroProperty() {
		return this.nomeLivro;
	}
	
	public String getDescricao() {
		return descricao.get();
	}
	public void setDescricao(String descricao) {
		this.descricao.set(descricao);
	}
	public StringProperty descricaoProperty() {
		return this.descricao;
	}

	public String getAutor() {
		return autor.get();
	}
	public void setAutor(String autor) {
		this.autor.set(autor);
	}
	public StringProperty autorProperty() {
		return this.autor;
	}
	
	public String getEditora() {
		return editora.get();
	}
	public void setEditora(String editora) {
		this.editora.set(editora);
	}
	public StringProperty editoraProperty() {
		return this.editora;
	}
	
	public String getTipo() {
		return tipo.get();
	}
	public void setTipo(String tipo) {
		this.tipo.set(tipo);
	}
	public StringProperty tipoProperty() {
		return this.tipo;
	}
	
	public boolean getCircular() {
		return circular.get();
	}
	public void setCircular(boolean circular) {
		this.circular.set(circular);
	}
	public BooleanProperty circularProperty() {
		return this.circular;
	}
	
	public boolean getEmprestado() {
		return emprestado.get();
	}
	public void setEmprestado(boolean emprestado) {
		this.emprestado.set(emprestado);
	}
	public BooleanProperty emprestadoProperty() {
		return this.emprestado;
	}
	
	public boolean getReservado() {
		return reservado.get();
	}
	public void setReservado(boolean reservado) {
		this.reservado.set(reservado);
	}
	public BooleanProperty reservadoProperty() {
		return this.reservado;
	}
	
	public LocalDate getPublicacao() {
		return publicacao.get();
	}
	public void setPublicacao(LocalDate publicacao) {
		this.publicacao.set(publicacao);
	}
	public ObjectProperty<LocalDate> publicacaoProperty(){
		return this.publicacao;
	}
	
}

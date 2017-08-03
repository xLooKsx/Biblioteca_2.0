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

public class EmprestimoTO {

	private IntegerProperty idEmprestimo;
	private IntegerProperty matriculaUsuario;
	private IntegerProperty idLivro;
	
	private StringProperty nomeLivro;
	
	private ObjectProperty<LocalDate> dataEmprestimo;
	private ObjectProperty<LocalDate> dataDevolucao;
	
	private BooleanProperty encerrado;
	
	public EmprestimoTO() {
		this(0, 0, 0, null, null, null, false);
	}

	public EmprestimoTO(int idEmprestimo, int matriculaUsuario, int idLivro, String nomeLivro, LocalDate dataEmprestimo,
						LocalDate dataDevolucao, boolean encerrado) {
		
		this.idEmprestimo = new SimpleIntegerProperty(idEmprestimo);
		this.matriculaUsuario = new SimpleIntegerProperty(matriculaUsuario);
		this.idLivro = new SimpleIntegerProperty(idLivro);
		this.nomeLivro = new SimpleStringProperty(nomeLivro);
		this.dataEmprestimo = new SimpleObjectProperty<LocalDate>(dataEmprestimo);
		this.dataDevolucao = new SimpleObjectProperty<LocalDate>(dataDevolucao);
		this.encerrado = new SimpleBooleanProperty(encerrado);
	}

	public int getIdEmprestimo() {
		return idEmprestimo.get();
	}
	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo.set(idEmprestimo);
	}
	public IntegerProperty idEmprestimoProperty() {
		return this.idEmprestimo;
	}
	
	public int getMatriculaUsuario() {
		return matriculaUsuario.get();
	}
	public void setMatriculaUsuario(int matriculaUsuario) {
		this.matriculaUsuario.set(matriculaUsuario);
	}
	public IntegerProperty matriculaUsuarioProperty() {
		return this.matriculaUsuario;
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
		
	public String getNomeLivro() {
		return nomeLivro.get();
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro.set(nomeLivro);
	}
	public StringProperty nomeLivroEmprestimoProperty() {
		return this.nomeLivro;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo.get();
	}
	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo.set(dataEmprestimo);
	}
	public ObjectProperty<LocalDate> dataEmprestimoProperty() {
		return this.dataEmprestimo;
	}
	
	public LocalDate getDataDevolução() {
		return dataDevolucao.get();
	}
	public void setDataDevolução(LocalDate dataDevolucao) {
		this.dataDevolucao.set(dataDevolucao);
	}
	public ObjectProperty<LocalDate> dataDevolucaoProperty(){
		return this.dataDevolucao;
	}
	
	public boolean getEncerrado() {
		return encerrado.get();
	}
	public void setEncerrado(boolean encerrado) {
		this.encerrado.set(encerrado);
	}
	public BooleanProperty encerradoProperty() {
		return this.encerrado;
	}
	
}

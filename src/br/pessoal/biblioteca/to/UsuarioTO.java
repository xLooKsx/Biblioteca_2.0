package br.pessoal.biblioteca.to;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UsuarioTO {

	private StringProperty senha;
	private StringProperty nome;
	private StringProperty ultimoNome;
	private StringProperty logradouro;
	private StringProperty tipoLogradouro;
	private StringProperty complLogradouro;
	private StringProperty email;
	private StringProperty tipoUsuario;
	
	private IntegerProperty matricula;
	private IntegerProperty telefone;
	private IntegerProperty qtdLivro;
	private IntegerProperty qtdRevista;
	
	private BooleanProperty contaAtiva;
	private BooleanProperty bibliotecario;
	
	public UsuarioTO() {
		this(0,null,null,null,null,null,null,null,null,0,0,0,false,false);
	}

	public UsuarioTO(int matricula, String senha, String nome, String ultimoNome, String logradouro, String tipoLogradouro, String complLogradouro,
					String email, String tipoUsuario, int telefone, int qtdLivro, int qtdRevista, boolean contaAtiva, boolean bibliotecario) {
		
		this.matricula = new SimpleIntegerProperty(matricula);
		this.senha = new SimpleStringProperty(senha);
		this.nome = new SimpleStringProperty(nome);
		this.ultimoNome = new SimpleStringProperty(ultimoNome);
		this.logradouro = new SimpleStringProperty(logradouro);
		this.tipoLogradouro = new SimpleStringProperty(tipoLogradouro);
		this.complLogradouro = new SimpleStringProperty(complLogradouro);
		this.email = new SimpleStringProperty(email);
		this.tipoUsuario = new SimpleStringProperty(tipoUsuario);
		this.telefone = new SimpleIntegerProperty(telefone);
		this.qtdLivro = new SimpleIntegerProperty(qtdLivro);
		this.qtdRevista = new SimpleIntegerProperty(qtdRevista);
		this.contaAtiva = new SimpleBooleanProperty(contaAtiva);
		this.bibliotecario = new SimpleBooleanProperty(bibliotecario);
	}

	/**
	 * Senha
	 * @return
	 */
	public String getSenha() {
		return senha.get();
	}
	public void setSenha(String senha) {
		this.senha.set(senha);
	}
	public StringProperty senhaProperty() {
		return this.senha;
	}

	/**
	 * Nome
	 * @return
	 */
	public String getNome() {
		return nome.get();
	}
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	/**
	 * Ultimo Nome
	 * @return
	 */
	public String getUltimoNome() {
		return ultimoNome.get();
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome.set(ultimoNome);
	}
	public StringProperty UltimoNomeProperty() {
		return this.ultimoNome;
	}

	/**
	 * logradouro
	 * @return
	 */
	public String getLogradouro() {
		return logradouro.get();
	}
	public void setLogradouro(String logradouro) {
		this.logradouro.set(logradouro);
	}
	public StringProperty LogradouroProperty() {
		return this.logradouro;
	}
	
	/**
	 * Tipo de Logradouro
	 * @return
	 */
	public String getTipoLogradouro() {
		return tipoLogradouro.get();
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro.set(tipoLogradouro);
	}
	public StringProperty tipoLogradouroProperty() {
		return this.tipoLogradouro;
	}

	/**
	 * complemento de logradouro
	 * @return
	 */
	public String getComplLogradouro() {
		return complLogradouro.get();
	}
	public void setComplLogradouro(String complLogradouro) {
		this.complLogradouro.set(complLogradouro);
	}
	public StringProperty complLogradouroProperty() {
		return this.complLogradouro;
	}

	/**
	 * Email
	 * @return
	 */
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty emailProperty() {
		return this.email;
	}
	
	/**
	 * tipo de usuario
	 * @return
	 */
	public String getTipoUsuario() {
		return tipoUsuario.get();
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario.set(tipoUsuario);
	}
	public StringProperty tipoUsuarioProperty() {
		return this.tipoUsuario;
	}
	
	/**
	 * Matricula
	 * @return
	 */
	public int getMatricula() {
		return matricula.get();
	}
	public void setMatricula(int matricula) {
		this.matricula.set(matricula);
	}
	public IntegerProperty matriculaProperty() {
		return this.matricula;
	}
	
	/**
	 * Telefone
	 * @return
	 */
	public int getTelefone() {
		return telefone.get();
	}
	public void setTelefone(int telefone) {
		this.telefone.set(telefone);
	}
	public IntegerProperty telefoneProperty() {
		return this.telefone;
	}
	
	/**
	 * Quantidade de Livros
	 * @return
	 */
	public int getQtdLivro() {
		return qtdLivro.get();
	}
	public void setQtdLivro(int qtdLivro) {
		this.qtdLivro.set(qtdLivro);
	}
	public IntegerProperty qtdLivroProperty() {
		return this.qtdLivro;
	}
	
	/**
	 * Quantidade de Revistas
	 * @return
	 */
	public int getQtdRevista() {
		return qtdRevista.get();
	}
	public void setQtdRevista(int qtdRevista) {
		this.qtdRevista.set(qtdRevista);
	}
	public IntegerProperty qtdRevistaProperty() {
		return this.qtdRevista;
	}

	/**
	 * Conta Ativa
	 * @return
	 */
	public boolean getContaAtiva() {
		return contaAtiva.get();
	}
	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva.set(contaAtiva);
	}
	public BooleanProperty contaAtivaProperty() {
		return this.contaAtiva;
	}
	
	/**
	 * bibliotecario
	 * @return
	 */
	public Boolean getBibliotecario() {
		return bibliotecario.get();
	}
	public void setBibliotecario(Boolean bibliotecario) {
		this.bibliotecario.set(bibliotecario);
	}
	public BooleanProperty bibliotecarioProperty() {
		return this.bibliotecario;
	}
	
}

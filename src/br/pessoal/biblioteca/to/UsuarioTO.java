package br.pessoal.biblioteca.to;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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
	
	private LongProperty telefone;
	
	private IntegerProperty matricula;	
	private IntegerProperty qtdLivro;
	private IntegerProperty qtdRevista;
	
	private BooleanProperty contaAtiva;
	private BooleanProperty bibliotecario;
	
	public UsuarioTO() {
		this(0,null,null,null,null,null,null,null,null,0,0,0,false,false);
	}

	public UsuarioTO(int matricula, String senha, String nome, String ultimoNome, String logradouro, String tipoLogradouro, String complLogradouro,
					String email, String tipoUsuario, long telefone, int qtdLivro, int qtdRevista, boolean contaAtiva, boolean bibliotecario) {
		
		this.matricula = new SimpleIntegerProperty(matricula);
		this.senha = new SimpleStringProperty(senha);
		this.nome = new SimpleStringProperty(nome);
		this.ultimoNome = new SimpleStringProperty(ultimoNome);
		this.logradouro = new SimpleStringProperty(logradouro);
		this.tipoLogradouro = new SimpleStringProperty(tipoLogradouro);
		this.complLogradouro = new SimpleStringProperty(complLogradouro);
		this.email = new SimpleStringProperty(email);
		this.tipoUsuario = new SimpleStringProperty(tipoUsuario);
		this.telefone = new SimpleLongProperty(telefone);
		this.qtdLivro = new SimpleIntegerProperty(qtdLivro);
		this.qtdRevista = new SimpleIntegerProperty(qtdRevista);
		this.contaAtiva = new SimpleBooleanProperty(contaAtiva);
		this.bibliotecario = new SimpleBooleanProperty(bibliotecario);
	}


	public String getSenha() {
		return senha.get();
	}
	public void setSenha(String senha) {
		this.senha.set(senha);
	}
	public StringProperty senhaProperty() {
		return this.senha;
	}

	public String getNome() {
		return nome.get();
	}
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public String getUltimoNome() {
		return ultimoNome.get();
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome.set(ultimoNome);
	}
	public StringProperty UltimoNomeProperty() {
		return this.ultimoNome;
	}

	public String getLogradouro() {
		return logradouro.get();
	}
	public void setLogradouro(String logradouro) {
		this.logradouro.set(logradouro);
	}
	public StringProperty LogradouroProperty() {
		return this.logradouro;
	}
	
	public String getTipoLogradouro() {
		return tipoLogradouro.get();
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro.set(tipoLogradouro);
	}
	public StringProperty tipoLogradouroProperty() {
		return this.tipoLogradouro;
	}

	public String getComplLogradouro() {
		return complLogradouro.get();
	}
	public void setComplLogradouro(String complLogradouro) {
		this.complLogradouro.set(complLogradouro);
	}
	public StringProperty complLogradouroProperty() {
		return this.complLogradouro;
	}

	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty emailProperty() {
		return this.email;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario.get();
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario.set(tipoUsuario);
	}
	public StringProperty tipoUsuarioProperty() {
		return this.tipoUsuario;
	}
	
	public int getMatricula() {
		return matricula.get();
	}
	public void setMatricula(int matricula) {
		this.matricula.set(matricula);
	}
	public IntegerProperty matriculaProperty() {
		return this.matricula;
	}
	
	public long getTelefone() {
		return telefone.get();
	}
	public void setTelefone(long telefone) {
		this.telefone.set(telefone);
	}
	public LongProperty telefoneProperty() {
		return this.telefone;
	}
		
	public int getQtdLivro() {
		return qtdLivro.get();
	}
	public void setQtdLivro(int qtdLivro) {
		this.qtdLivro.set(qtdLivro);
	}
	public IntegerProperty qtdLivroProperty() {
		return this.qtdLivro;
	}
		
	public int getQtdRevista() {
		return qtdRevista.get();
	}
	public void setQtdRevista(int qtdRevista) {
		this.qtdRevista.set(qtdRevista);
	}
	public IntegerProperty qtdRevistaProperty() {
		return this.qtdRevista;
	}

	public boolean getContaAtiva() {
		return contaAtiva.get();
	}
	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva.set(contaAtiva);
	}
	public BooleanProperty contaAtivaProperty() {
		return this.contaAtiva;
	}
	
	public Boolean getBibliotecario() {
		return bibliotecario.get();

	}
	public void setBibliotecario(Boolean bibliotecario) {
		this.bibliotecario.set(bibliotecario);
	}
	public BooleanProperty bibliotecarioProperty() {
		return this.bibliotecario;
	}

	@Override
	public String toString() {
		return "UsuarioTO [senha=" + senha + ", nome=" + nome + ", ultimoNome=" + ultimoNome + ", logradouro="
				+ logradouro + ", tipoLogradouro=" + tipoLogradouro + ", complLogradouro=" + complLogradouro
				+ ", email=" + email + ", tipoUsuario=" + tipoUsuario + ", telefone=" + telefone + ", matricula="
				+ matricula + ", qtdLivro=" + qtdLivro + ", qtdRevista=" + qtdRevista + ", contaAtiva=" + contaAtiva
				+ ", bibliotecario=" + bibliotecario + "]";
	}	
	
	
}

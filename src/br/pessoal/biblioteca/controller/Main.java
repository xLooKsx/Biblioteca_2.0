package br.pessoal.biblioteca.controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.to.EmprestimoTO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.to.UsuarioTO;
import br.pessoal.biblioteca.view.AlterarDadosController;
import br.pessoal.biblioteca.view.AlterarSenhaController;
import br.pessoal.biblioteca.view.BuscaController;
import br.pessoal.biblioteca.view.EmprestimosController;
import br.pessoal.biblioteca.view.LoginController;
import br.pessoal.biblioteca.view.PainelBaseController;
import br.pessoal.biblioteca.view.RecuperacaoSenhaController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private UsuarioTO usuarioTO;
	private BorderPane painelBase;	
	
	private PainelBaseController painelBaseController;
	
	private ObservableList<EmprestimoTO> emprestimos = FXCollections.observableArrayList();
	private ObservableList<LivroTO> livros = FXCollections.observableArrayList();
	
	Logger logger = Logger.getLogger(Main.class.getName());
		
	public Main() {		
		
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");
		this.primaryStage.getIcons().add(new Image("file:imagem/Bookmark.png"));
		this.primaryStage.setResizable(false);
		
		mostrarJanelaLogin();
	}

	public void mostraPainelBase() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/PainelBase.fxml"));
			this.painelBase = (BorderPane) loader.load();
			
			this.primaryStage.setTitle("Biblioteca");					
			
			Scene cena = new Scene(painelBase);
			this.primaryStage.setScene(cena);
						
			this.painelBaseController = loader.getController();
			this.painelBaseController.setMain(this);
			this.painelBaseController.mostrarInforamacoes();
			
			this.primaryStage.centerOnScreen();
			this.primaryStage.show();
		}catch (IOException e) {
			logger.log(Level.SEVERE, "Erro ao mostrar o painel base ", e);
		}
	}
	
	public void mostrarJanelaBusca() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/Busca.fxml"));
			AnchorPane boasVindas = (AnchorPane) loader.load();
			
			BuscaController buscaController = loader.getController();
			buscaController.setMain(this);
			
			
			painelBase.setCenter(boasVindas);						
		}catch (IOException e) {
			 logger.log(Level.SEVERE, "Erro ao apresentar a busca ", e);
		}
	}
	
	public void mostrarJanelaLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/Login.fxml"));			
			AnchorPane login = (AnchorPane) loader.load();
			
			this.primaryStage.setTitle("Login");
			
			Scene cena = new Scene(login);
			this.primaryStage.setScene(cena);
			
			LoginController loginController = loader.getController();
			loginController.setMain(this);
			
			this.primaryStage.show();
		}catch (IOException e) {
			logger.log(Level.SEVERE, "Erro ao carregar a janela de Login:", e);
		}
	}

	public void mostraJanelaRecuperacaoSenha() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/RecuperacaoSenha.fxml"));
			AnchorPane recuperarSenha = (AnchorPane) loader.load();
			
			//Criação da Janela dialogo
			Stage janelaDialogo = new Stage();
			janelaDialogo.setTitle("Recuperação de Senha");
			janelaDialogo.getIcons().add(new Image("file:imagem/Bookmark.png"));
			janelaDialogo.initModality(Modality.WINDOW_MODAL);
			janelaDialogo.initOwner(this.primaryStage);
			
			Scene cena = new Scene(recuperarSenha);
			janelaDialogo.setScene(cena);
			
			RecuperacaoSenhaController recuperacaoSenhaController = loader.getController();
			recuperacaoSenhaController.setJanelaDialogo(janelaDialogo);
			
			janelaDialogo.showAndWait();
		}catch (IOException e) {
			logger.log(Level.SEVERE, "Erro ao mostrar janela de recuperação de Senha ", e);
		}
	}
	
	public void mostraJanelaAlteracaoSenha() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/AlterarSenha.fxml"));
			AnchorPane alterarSenha = (AnchorPane) loader.load();
			
			AlterarSenhaController alterarSenhaController = loader.getController();
			alterarSenhaController.setMain(this);
			
			painelBase.setCenter(alterarSenha);
		}catch (Exception e) {
			logger.log(Level.SEVERE, "Não foi possivel carregar a janela de alterar Senha ", e);
		}
	}
	
	public void mostraJanelaAlterarDados() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/AlterarDados.fxml"));
			AnchorPane alterarDados = (AnchorPane) loader.load();
			
			AlterarDadosController alterarDadosController = loader.getController();
			alterarDadosController.setMain(this);
			alterarDadosController.setUsuarioTO(this.usuarioTO);
			alterarDadosController.mostrarDadosUsuario();
			
			painelBase.setCenter(alterarDados);
		}catch (IOException e) {
			logger.log(Level.SEVERE, "Não foi possivel carregar a janela de alterar Dados ", e);
		}
	}
	
	public void mostraEmprestimos() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/Emprestimos.fxml"));
			AnchorPane emprestimos = (AnchorPane) loader.load();
			
			EmprestimosController emprestimosController = loader.getController();
			emprestimosController.setMain(this);
			emprestimosController.inicializaJanela();
			
			painelBase.setCenter(emprestimos);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Não foi possivel carregar a janela de Emprestimos ", e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}
	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public ObservableList<EmprestimoTO> getEmprestimos() {
		return emprestimos;
	}
	
	public void setLivros(ObservableList<LivroTO> livros) {
		this.livros = livros;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public ObservableList<LivroTO> getLivros() {
		return livros;
	}

	public String getToStringUsuario() {
		return this.usuarioTO.toString();
	}

	public BorderPane getPainelBase() {
		return painelBase;
	}

	public PainelBaseController getPainelBaseController() {
		return painelBaseController;
	}	
}

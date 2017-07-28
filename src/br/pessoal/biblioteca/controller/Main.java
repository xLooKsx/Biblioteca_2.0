package br.pessoal.biblioteca.controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.pessoal.biblioteca.to.UsuarioTO;
import br.pessoal.biblioteca.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private UsuarioTO usuarioTO;
	private BorderPane painelBase;
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
			
			this.primaryStage.show();
		}catch (IOException e) {
			logger.log(Level.SEVERE, "Erro ao mostrar o painel base ", e);
		}
	}
	
	public void apresentarBoasVindas() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/BoasVindas.fxml"));
			AnchorPane boasVindas = (AnchorPane) loader.load();
			
			painelBase.setCenter(boasVindas);
						
		}catch (IOException e) {
			 logger.log(Level.SEVERE, "Erro ao apresentar as boas vindas ", e);
		}
	}
	
	public void mostrarJanelaLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/Login.fxml"));			
			AnchorPane login = (AnchorPane) loader.load();
			
			Scene cena = new Scene(login);
			this.primaryStage.setScene(cena);
			
			LoginController loginController = loader.getController();
			loginController.setMain(this);
			
			this.primaryStage.show();
		}catch (IOException e) {
			logger.log(Level.SEVERE, "Erro ao carregar a janela de Login:", e);
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
}

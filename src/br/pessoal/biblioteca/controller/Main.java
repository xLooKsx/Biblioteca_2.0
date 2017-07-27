package br.pessoal.biblioteca.controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	Logger logger = Logger.getLogger(Main.class.getName());
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");
		this.primaryStage.getIcons().add(new Image("/br/pessoal/biblioteca/resource/Bookmark.png"));
		
		mostrarJanelaLogin();
	}

	private void mostrarJanelaLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/br/pessoal/biblioteca/view/Login.fxml"));			
			AnchorPane login = (AnchorPane) loader.load();
			
			Scene cena = new Scene(login);
			this.primaryStage.setScene(cena);
			
			this.primaryStage.show();
		}catch (Exception e) {
			logger.log(Level.SEVERE, "Erro ao carregar a janela de Login: {0}", e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}	
}

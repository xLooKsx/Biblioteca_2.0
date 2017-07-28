package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PainelBaseController {

	@FXML
	private Label lblNomeUsuario;
	@FXML
	private Label lblQtdMaterialEmprestado;
	@FXML
	private Label lblPossuiMaterialAtrasado;
	
	private Main main;
	
	public PainelBaseController() {
	}

	@FXML
	private void initialize() {		
	}

	public void mostrarDadosUsuario() {
		int QtdMaterialAtrasado = main.getUsuarioTO().getQtdLivro() + main.getUsuarioTO().getQtdRevista();
		
		lblNomeUsuario.setText(main.getUsuarioTO().getNome());
		lblQtdMaterialEmprestado.setText(Integer.toString(QtdMaterialAtrasado));
		//Fazer a logica para mostrar se tem ou não material atrasado
	}
	
	@FXML
	private void handleSair() {
		main.mostrarJanelaLogin();
	}

	public void setMain(Main main) {
		this.main = main;
	}
}

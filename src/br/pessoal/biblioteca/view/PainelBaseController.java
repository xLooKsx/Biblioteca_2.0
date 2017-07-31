package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class PainelBaseController {

	@FXML
	private Label lblNomeUsuario;
	@FXML
	private Label lblQtdMaterialEmprestado;
	@FXML
	private Label lblPossuiMaterialAtrasado;
	
	@FXML
	private Text txtBuscar;
	
	
	private Main main;
	
	public PainelBaseController() {
	}

	@FXML
	private void initialize() {		
		
	}

	@FXML
	private void handleAlterarSenha() {
		this.main.mostraJanelaAlteracaoSenha();
	}
	
	public void mostrarDadosUsuario() {
		int QtdMaterialAtrasado = main.getUsuarioTO().getQtdLivro() + main.getUsuarioTO().getQtdRevista();		
		
		lblNomeUsuario.setText(main.getUsuarioTO().getNome());
		lblQtdMaterialEmprestado.setText(Integer.toString(QtdMaterialAtrasado));
		lblPossuiMaterialAtrasado.setText(this.main.getLivrosAtrasados().size()==0?"Não":"Sim");
	}	
	
	@FXML
	private void handleSair() {
		main.mostrarJanelaLogin();
	}

	public void setMain(Main main) {
		this.main = main;
	}
}

package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class PainelBaseController {

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
	
	@FXML
	private void handleSair() {
		main.mostrarJanelaLogin();
	}
	
	@FXML
	private void handleAlterarDados() {
		main.mostraJanelaAlterarDados();
	}
	
	public void mostrarDadosUsuario() {
		int QtdMaterialAtrasado = main.getUsuarioTO().getQtdLivro() + main.getUsuarioTO().getQtdRevista();		
				
		lblQtdMaterialEmprestado.setText(Integer.toString(QtdMaterialAtrasado));
		lblPossuiMaterialAtrasado.setText(this.main.getLivrosAtrasados().size()==0?"Não":"Sim");
	}		

	public void setMain(Main main) {
		this.main = main;
	}
}

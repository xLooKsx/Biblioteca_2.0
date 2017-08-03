package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
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
	private void handleEmprestimos() {
		this.main.mostraEmprestimos();
	}
	
	@FXML
	private void handleAlterarSenha() {
		this.main.mostraJanelaAlteracaoSenha();
	}
	
	@FXML
	private void handleSair() {
		main.mostrarJanelaLogin();
		this.main.getEmprestimos().clear();
	}
	
	@FXML
	private void handleAlterarDados() {
		main.mostraJanelaAlterarDados();
	}
	
	

	public void setMain(Main main) {
		this.main = main;
	}
	
	public void mostrarInforamacoes() {
		int QtdMaterialAtrasado = main.getUsuarioTO().getQtdLivro() + main.getUsuarioTO().getQtdRevista();		

		lblQtdMaterialEmprestado.setText(Integer.toString(QtdMaterialAtrasado));
		lblPossuiMaterialAtrasado.setText(BibliotecaUtils.checkarEmprestimos(main.getEmprestimos())==true?"Sim":"Não");
	}
	
}

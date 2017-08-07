package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
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
	private void handleRealizarEmprestimo() {
		this.main.mostrarEmprestimo();
	}
	
	@FXML
	private void handleAcervo() {
		this.main.mostrarAcervo();
	}

	@FXML
	private void handleCadastroLivro() {
		this.main.mostrarCadastroLivro();
	}
	
	@FXML
	private void handleCadastroUsuario() {
		this.main.mostrarCadastroUsuario();
	}
	
	@FXML
	private void handleBuscar() {
		this.main.mostrarBusca();
	}
	
	@FXML
	private void handleEmprestimos() {
		this.main.mostraEmprestimos();
	}
	
	@FXML
	private void handleAlterarSenha() {
		this.main.mostraAlteracaoSenha();
	}
	
	@FXML
	private void handleSair() {
		main.mostrarJanelaLogin();
		this.main.getEmprestimos().clear();
	}
	
	@FXML
	private void handleAlterarDados() {
		main.mostraAlterarDados();
	}
	
	

	public void setMain(Main main) {
		this.main = main;
	}
	
	public void mostrarInforamacoes() {
		int QtdMaterialAtrasado = main.getUsuarioTO().getQtdLivro() + main.getUsuarioTO().getQtdRevista();		

		lblNomeUsuario.setText(this.main.getUsuarioTO().getNome());
		lblQtdMaterialEmprestado.setText(Integer.toString(QtdMaterialAtrasado));
		lblPossuiMaterialAtrasado.setText(BibliotecaUtils.checkarEmprestimos(main.getEmprestimos())==true?"Sim":"Não");
	}
	
}

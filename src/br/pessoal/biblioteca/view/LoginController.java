package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.LoginDAO;
import br.pessoal.biblioteca.to.UsuarioTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField pwSenha;
	
	@FXML
	private Label lblMensagemErro;

	private Main main;
	private UsuarioTO usuarioTO;	
	
	public LoginController() {	
	}

	@FXML
	public void initialize() {
		this.lblMensagemErro.setText("");
	}
	
	@FXML
	private void handleEntrar() {
		if (camposValidos()) {
			this.usuarioTO = new LoginDAO().buscarUsuario(Integer.parseInt(this.txtUsuario.getText()), this.pwSenha.getText());
			if (this.usuarioTO.getContaAtiva()) {
				main.setUsuarioTO(this.usuarioTO);
				main.mostraPainelBase();
				main.apresentarBoasVindas();
			}else {
				this.lblMensagemErro.setText("Usuario Invalido");
			}
		}
		
	}
	
	@FXML
	private void handleRecuperarSenha() {
		this.txtUsuario.setText("");
		this.pwSenha.setText("");
		main.mostraJanelaRecuperacaoSenha();
	}
	
	private boolean camposValidos() {
		String mensagemErro="";
		if (this.txtUsuario.getText().trim().length() == 0 || this.txtUsuario.getText() == null) {
			mensagemErro += "Favor preencher o campo 'Usuario'\n";
		}else if(!this.txtUsuario.getText().matches("[0-9]*")) {
			mensagemErro += "A matricula deve possuir apenas numeros";
		}
		if (this.pwSenha.getText().trim().length() == 0 || this.pwSenha.getText() == null) {
			mensagemErro +="Favor preencher o campo 'Senha'";
		}
		
		if (mensagemErro.length() == 0) {
			return true;
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Favor arrumar os erros abaixo");
			alert.setContentText(mensagemErro);
			alert.showAndWait();
		}
		return false;
	}

	public void setMain(Main main) {
		this.main = main;
	}	
}

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
	
	LoginDAO loginDAO = new LoginDAO();
	
	public LoginController() {	
	}

	@FXML
	public void initialize() {
		this.lblMensagemErro.setText("");
	}
	
	@FXML
	private void handleEntrar() {
		if (camposValidos()) {
			this.usuarioTO = this.loginDAO.buscarUsuario(Integer.parseInt(txtUsuario.getText()));
			if (this.usuarioTO != null) {
				main.setUsuarioTO(this.usuarioTO);
				this.lblMensagemErro.setText("Login foi um sucesso");
			}else {
				this.lblMensagemErro.setText("Usuario Invalido");
			}
		}
		
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

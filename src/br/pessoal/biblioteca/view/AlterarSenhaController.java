package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class AlterarSenhaController {

	@FXML
	private PasswordField pwSenhaAtual;
	@FXML
	private PasswordField pwNovaSenha;
	@FXML
	private PasswordField pwConfirmaNovaSenha;
	
	private Main main;
	
	public AlterarSenhaController() {		
	}

	@FXML
	private void initialize() {
		
	}

	@FXML
	private void handleAlterarSenha() {
		if (camposValidos()) {
			if (this.main.getUsuarioTO().getSenha().equals(this.pwSenhaAtual.getText())) {
				new UsuarioDAO().alterarSenha(this.main.getUsuarioTO().getMatricula(), this.pwNovaSenha.getText());
				
				this.pwNovaSenha.setText("");
				this.pwConfirmaNovaSenha.setText("");
				this.pwSenhaAtual.setText("");
				
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Alteração de Senha");
				alerta.setHeaderText("Senha alterada com sucesso");	
				alerta.showAndWait();
				
				this.main.getUsuarioTO().setSenha(this.pwNovaSenha.getText());
			}else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Alteração de Senha");
				alerta.setHeaderText("Senha atual incorreta");	
				alerta.showAndWait();
			}			
		}
	}
	
	private boolean camposValidos() {
		String menssagemErro = "";
		
		if (this.pwSenhaAtual.getText().trim().length() == 0 || this.pwSenhaAtual.getText() == null) {
			menssagemErro += "Favor preencher o campo da senha atual";
		}
		if (this.pwNovaSenha.getText().trim().length() == 0 || this.pwNovaSenha.getText() == null) {
			menssagemErro += "Favor preencher o campo da nova senha";
		}
		if (this.pwConfirmaNovaSenha.getText().trim().length() == 0 || this.pwConfirmaNovaSenha.getText() == null) {
			menssagemErro += "Favor preencher o campo de confirmação da nova senha";
		}
		
		if (menssagemErro.length() == 0) {
			if (this.pwNovaSenha.getText().equals(this.pwConfirmaNovaSenha.getText())) {
				return true;
			}
			menssagemErro += "As senhas não conferem";
		}
		
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Erro ao alterar a Senha");
		alerta.setHeaderText("Não foi possivel alterar a Senha");
		alerta.setContentText(menssagemErro);
		alerta.showAndWait();
		
		return false;
	}

	public void setMain(Main main) {
		this.main = main;
	}	
}

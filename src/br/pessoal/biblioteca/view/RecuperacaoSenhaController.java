package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.dao.LoginDAO;
import br.pessoal.biblioteca.to.UsuarioTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RecuperacaoSenhaController {
	
	@FXML
	private Pane PaneIdentificacao;
	@FXML
	private Pane PaneRedefinirSenha;
	
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtSobrenome;
	@FXML
	private TextField txtNumeroMatricula;
	
	@FXML
	private PasswordField pwSenha;
	@FXML
	private PasswordField pwConfirmarSenha;	
	
	private Stage janelaDialogo;
	private UsuarioTO usuarioTO;
	
 	public RecuperacaoSenhaController() {		
	}

	@FXML
	public void initialize() {		
	
	}
	
	
	@FXML
	private void handleConfirmarUsuario() {
		if (validarCamposPrimeiroPasso()) {
			if (new LoginDAO().validarUsuario(this.txtNome.getText(), this.txtSobrenome.getText(), Integer.parseInt(this.txtNumeroMatricula.getText().trim()))) {
				this.PaneIdentificacao.setDisable(true);
				this.PaneRedefinirSenha.setDisable(false);
			}else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Erro");
				alerta.setHeaderText("Usuario não existe ou esta Desativado");
				alerta.setContentText("Favor entrar em conntato com o bibliotecario");
				alerta.showAndWait();
			}
		}
	}	

	@FXML
	private void handleSalvarSenha() {
		if (validarCamposSegundoPasso()) {
			new LoginDAO().alterarSenha(this.txtNome.getText(), this.txtSobrenome.getText(), this.pwSenha.getText(), Integer.parseInt(this.txtNumeroMatricula.getText().trim()));
			this.PaneRedefinirSenha.setDisable(true);

			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Alteração de Senha Efetuada");
			alerta.setHeaderText("A senha foi alterada com sucesso");
			alerta.setContentText("Senha alterada com sucesso");
			alerta.showAndWait();

			this.janelaDialogo.close();
			}
		}
	
	
	@FXML
	private void handleCancelar() {
		this.janelaDialogo.close();
	}
	
	private boolean validarCamposSegundoPasso() {
		String menssagemErro="";
		
		if (this.pwSenha.getText().trim().length() == 0 || this.pwSenha.getText() == null) {
			menssagemErro += "Favor preencher o campo da senha\n";
		}
		if (this.pwConfirmarSenha.getText().trim().length() == 0 || this.pwConfirmarSenha.getText() == null) {
			menssagemErro += "Favor preencher o campo 'confirmar Senha'\n";
		}
		if (menssagemErro.length() == 0) {
			if (this.pwConfirmarSenha.getText().equals(this.pwSenha.getText())) {
				return true;
			}
			menssagemErro += "As senhas digitadas não são iguais\n";
		}
			
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Falha ao alterar a senha");
		alerta.setHeaderText("Favor fazer as alteraçõs necessarias");
		alerta.setContentText(menssagemErro);
		alerta.showAndWait();
		
		return false;
	}
	
	private boolean validarCamposPrimeiroPasso() {
		String menssagemErro = "";
		
		if (this.txtNome.getText().trim().length() == 0 || this.txtNome.getText() == null) {
			menssagemErro += "Favor preencher campo 'Nome'\n";
		}
		if (this.txtSobrenome.getText().trim().length() == 0 || this.txtSobrenome.getText() == null) {
			menssagemErro += "Favor preencher campo 'Sobrenome'\n";
		}
		if (this.txtNumeroMatricula.getText().trim().length() == 0 || this.txtNumeroMatricula.getText() == null) {
			menssagemErro += "Favor preencher campo 'Numero Matricula'\n";
		}else if(!this.txtNumeroMatricula.getText().matches("[0-9]*")) {
			menssagemErro += "O campo 'Numero Matricula' deve ser apenas numerico";
		}
		
		if (menssagemErro.length() == 0) {
			return true;
		}else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Primeiro passo invalido");
			alerta.setHeaderText("Favor corrigir os campos abaixo");
			alerta.setContentText(menssagemErro);
			alerta.showAndWait();
		}
		
		return false;
	}
	
	public void setJanelaDialogo(Stage janelaDialogo) {
		this.janelaDialogo = janelaDialogo;
	}
}

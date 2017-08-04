package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class CadastroUsuarioController {
	
	@FXML
	private TextField txtMatricula;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtSobrenome;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtComplemento;
	@FXML
	private TextField txtTelefone;	
	@FXML
	private TextField txtEmail;	
	
	@FXML
	private RadioButton rbFuncionario;
	@FXML
	private RadioButton rbProfessor;
	@FXML
	private RadioButton rbAluno;
		
	@FXML
	private PasswordField pwSenha;
	@FXML
	private PasswordField pwConfirmarSenha;
	
	@FXML
	private CheckBox chkBibliotecario;
	
	@FXML
	private ComboBox<String> comboboxTipoEndereco = new ComboBox<>();	
	private ToggleGroup grupo = new ToggleGroup();
	
	private Main main;
	
	@FXML
	private void initialize() {
		inicializarRadioButton();
		
		grupo.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov,
		          Toggle old_toggle, Toggle new_toggle) {
		        if (grupo.getSelectedToggle() != null) {
		          System.out.println(grupo.getSelectedToggle().getUserData().toString());
		        }
		      }
		    });
	}
	
	@FXML
	private void handleSalvarUsuario() {
		if (camposValidos()) {
			
		}
	}
	
	private boolean camposValidos() {
		String mensagemErro="";
		
		if (this.txtMatricula.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de matricula\n";
		}else if (this.txtMatricula.getText().length() != 7) {
			mensagemErro +="Favor preencher o campo de matricula\n";
		}else if (!this.txtMatricula.getText().matches("[0-9]*")) {
			mensagemErro +="O campo de matricula de possuir apenas numeros\n";
		}
		
		
		if (this.pwSenha.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de senha\n";
		}
		if (this.pwConfirmarSenha.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de confirmação de senha\n";
		}
		if (this.txtNome.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo nome\n";
		}
		if (this.txtSobrenome.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo Sobrenome\n";
		}if (this.comboboxTipoEndereco.getValue() == null) {
			mensagemErro +="Favor selecionar o tipo de endereço\n";
		}
		if (this.txtEndereco.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de Endereço\n";
		}
		
		if (this.txtTelefone.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de Telefone\n";
		}else if (!this.txtTelefone.getText().matches("[0-9]*")) {
			mensagemErro +="O campo de telefone de possuir apenas numeros\n";
		}
		
		if (this.txtEmail.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de Email\n";
		}else if (!this.txtEmail.getText().contains("@")) {
			mensagemErro +="Email invalido\n";
		}
		
		if (this.txtComplemento.getText().isEmpty()) {
			this.txtComplemento.setText("");
		}		
		if (mensagemErro.length() == 0) {
			if (this.pwSenha.getText().equals(this.pwConfirmarSenha.getText())) {
				return true;
			}
			mensagemErro +="Favor preencher o campo de Email\n";
		}
		
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("alteração de dados");
		alerta.setHeaderText("Não foi possivel alterar os dados");			
		alerta.setContentText(mensagemErro);
		alerta.showAndWait();
		return false;
	}

	private void inicializarRadioButton() {
		this.rbAluno.setToggleGroup(grupo);
		this.rbAluno.setUserData("A");
		
		this.rbProfessor.setToggleGroup(grupo);
		this.rbProfessor.setUserData("P");
		
		this.rbFuncionario.setToggleGroup(grupo);
		this.rbFuncionario.setUserData("F");
		this.rbFuncionario.setSelected(true);
	}

	public void setMain(Main main) {
		this.main = main;
	}	
}

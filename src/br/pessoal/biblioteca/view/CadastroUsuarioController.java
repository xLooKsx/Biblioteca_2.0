package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.dao.UsuarioDAO;
import br.pessoal.biblioteca.to.UsuarioTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
	
	private ObservableList<String> tiposEndereco = FXCollections.observableArrayList("Alameda", "Avenida", "Beco", "Estrada", "Rodovia", "Rua", "Travessa");		
	
	public CadastroUsuarioController() {
		
	}

	@FXML
	private void initialize() {
		inicializarRadioButton();
		comboboxTipoEndereco.getItems().addAll(tiposEndereco);	
	}
	
	@FXML
	private void handleSalvarUsuario() {
		if (camposValidos()) {
			new UsuarioDAO().adicionarUsuario(new UsuarioTO(Integer.parseInt(this.txtMatricula.getText()), this.pwSenha.getText(), this.txtNome.getText(), 
																				this.txtSobrenome.getText(), this.txtEndereco.getText(), BibliotecaUtils.getTipoEndereco(this.comboboxTipoEndereco.getValue().toString()), 
																				this.txtComplemento.getText(), this.txtEmail.getText(), this.grupo.getSelectedToggle().getUserData().toString(), 
																				Long.parseLong(this.txtTelefone.getText()), 0, 0, true, this.chkBibliotecario.isSelected()));
			
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Cadastro de Usuario");
			alerta.setHeaderText("Usuario cadastrado");			
			alerta.setContentText("Novo usuario cadastrado com sucesso");
			alerta.showAndWait();
			limparCampos();
		}
	}
	
	private void limparCampos() {
		
		this.txtMatricula.setText("");
		this.txtNome.setText("");
		this.txtSobrenome.setText("");
		this.txtEndereco.setText("");
		this.txtComplemento.setText("");
		this.txtTelefone.setText("");
		this.txtEmail.setText("");
		
		this.rbFuncionario.setSelected(true);
		this.rbProfessor.setSelected(false);
		this.rbAluno.setSelected(false);
			
		this.pwSenha.setText("");
		this.pwConfirmarSenha.setText("");
		
		this.chkBibliotecario.setSelected(false);
		
		this.comboboxTipoEndereco.setValue(null);
	}
	
	private boolean camposValidos() {
		String mensagemErro="";
		
		if (this.txtMatricula.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de matricula\n";
		}else if (this.txtMatricula.getText().length() != 7) {
			mensagemErro +="O campo de matricula deve possuir 7 digitos numericos\n";
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
		}else if (this.txtNome.getText().matches("[0-9]*")) {
			mensagemErro +="O campo de nome deve conter apenas letras\n";
		}
		if (this.txtSobrenome.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo Sobrenome\n";
		}else if (this.txtSobrenome.getText().matches("[0-9]*")) {
			mensagemErro +="O campo de sobrenome deve conter apenas letras\n";
		}
		if (this.comboboxTipoEndereco.getValue() == null) {
			mensagemErro +="Favor selecionar o tipo de endereço\n";
		}
		if (this.txtEndereco.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de Endereço\n";
		}
		
		if (this.txtTelefone.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de Telefone\n";
		}else if (this.txtTelefone.getText().length() < 10 || this.txtTelefone.getText().length() > 11) {
			mensagemErro +="preencha com DD+Telefone ou DD+celular, sem traços ou caracteres especiais, apenas digitos\n";
		}else if (!this.txtTelefone.getText().matches("[0-9]*")) {
			mensagemErro +="O campo de telefone deve possuir apenas numeros\n";
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
			mensagemErro +="As senhas digitadas não são iguais\n";
		}
		
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Cadastro de Usuario");
		alerta.setHeaderText("Não foi possivel realizar o cadastro");			
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
}

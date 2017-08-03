package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.UsuarioDAO;
import br.pessoal.biblioteca.to.UsuarioTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlterarDadosController {

	@FXML
	private Label teste;
	
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
	private ComboBox<String> comboBoxTipoEndereco = new ComboBox<>();
		
	private UsuarioTO usuarioTO;
	private Main main;
	private UsuarioTO usuarioTemp = new UsuarioTO();
	
	private ObservableList<String> tiposEndereco = FXCollections.observableArrayList("Alameda", "Avenida", "Beco", "Estrada", "Rodovia", "Rua", "Travessa");	
	
	@FXML
	public void initialize() {		
		comboBoxTipoEndereco.getItems().addAll(tiposEndereco);
	}
	
	@FXML
	private void handleTeste() {
		teste.setText(comboBoxTipoEndereco.getValue().toString());
	}
	
	@FXML
	private void handleSalvar() {
		if (camposValidos()) {			
			this.usuarioTemp.setNome(this.txtNome.getText().trim().toUpperCase());
			this.usuarioTemp.setUltimoNome(this.txtSobrenome.getText().trim().toUpperCase());
			this.usuarioTemp.setTipoLogradouro(BibliotecaUtils.getTipoEndereco(this.comboBoxTipoEndereco.getValue().trim()));
			this.usuarioTemp.setLogradouro(this.txtEndereco.getText().trim().toUpperCase());
			this.usuarioTemp.setComplLogradouro(this.txtComplemento.getText().trim().toUpperCase());
			this.usuarioTemp.setTelefone(BibliotecaUtils.removeMascaraTelefoneCelular(this.txtTelefone.getText().trim()));
			this.usuarioTemp.setEmail(this.txtEmail.getText().trim().toUpperCase());
			
			if (BibliotecaUtils.compararUsuarios(this.usuarioTO, this.usuarioTemp).length() == 0) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Alteração de dados pessoais");
				alerta.setHeaderText("Os dados não foram alterados");
				alerta.setContentText("Altere pelo menos um campo para efetuar alguma alteração no cadastro");
				alerta.showAndWait();
			}else {
				
				this.usuarioTO.setNome(this.txtNome.getText().trim().toUpperCase());
				this.usuarioTO.setUltimoNome(this.txtSobrenome.getText().trim().toUpperCase());
				this.usuarioTO.setTipoLogradouro(BibliotecaUtils.getTipoEndereco(this.comboBoxTipoEndereco.getValue()));
				this.usuarioTO.setLogradouro(this.txtEndereco.getText().trim().toUpperCase());
				this.usuarioTO.setComplLogradouro(this.txtComplemento.getText().trim().toUpperCase());
				this.usuarioTO.setTelefone(BibliotecaUtils.removeMascaraTelefoneCelular(this.txtTelefone.getText().trim()));
				this.usuarioTO.setEmail(this.txtEmail.getText().trim().toUpperCase());
				
				new UsuarioDAO().alteracaoDadosPessoais(this.usuarioTO);
				
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Alteração de dados pessoais");
				alerta.setHeaderText("Dados pessoais alterados com sucesso");
				alerta.showAndWait();	
				
				this.main.getPainelBaseController().mostrarInforamacoes();
			}
		}
	}
	
	private boolean camposValidos() {
		String menssagemErro = "";
		
		if (this.txtNome.getText().trim().length() == 0 || this.txtNome.getText() == null) {
			menssagemErro += "Nome invalido\n";
		}
		if (this.txtSobrenome.getText().trim().length() == 0 || this.txtSobrenome.getText() == null) {
			menssagemErro += "Sobrenome invalido\n";
		}
		if (this.comboBoxTipoEndereco.getValue() == null) {
			menssagemErro += "Tipo do endereço invalido\n";
		}
		if (this.txtEndereco.getText().trim().length() == 0 || this.txtEndereco.getText() == null) {
			menssagemErro += "Enderenço invalido\n";
		}
		if(this.txtComplemento.getText() == null) {
			this.txtComplemento.setText("");
		}
		if(!BibliotecaUtils.validarTelefone(this.txtTelefone.getText().toString())) {
			menssagemErro += "O telefone deve ser preenchido com a sua mascara(xx)1234-5678 ou (xx)9123-45678\n";
		}
		if (!this.txtEmail.getText().contains("@")) {
			menssagemErro += "Email invalido\n";
		}
		if (menssagemErro.length() == 0) {
			
			return true;
		}else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("alteração de dados");
			alerta.setHeaderText("Não foi possivel alterar os dados");			
			alerta.setContentText(menssagemErro);
			alerta.showAndWait();
		}
		return false;
	}

	public void mostrarDadosUsuario() {
		
		this.txtNome.setText(this.main.getUsuarioTO().getNome());
		this.txtSobrenome.setText(this.main.getUsuarioTO().getUltimoNome());
		this.comboBoxTipoEndereco.setValue(BibliotecaUtils.setTipoEndereco(this.main.getUsuarioTO().getTipoLogradouro()));
		this.txtEndereco.setText(this.main.getUsuarioTO().getLogradouro());
		this.txtComplemento.setText(this.main.getUsuarioTO().getComplLogradouro());
		this.txtTelefone.setText(BibliotecaUtils.adicionaMascaraTelefoneCelular(Long.toString(this.main.getUsuarioTO().getTelefone())));
		this.txtEmail.setText(this.main.getUsuarioTO().getEmail());
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}	
}

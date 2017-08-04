package br.pessoal.biblioteca.view;

import java.time.LocalDate;

import br.pessoal.biblioteca.dao.LivroDAO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class CadastroLivroController {

	@FXML
	private TextField txtIdLivro;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtEdicao;
	@FXML
	private TextField txtEditora;
	
	@FXML
	private DatePicker dtPublicacao;
	
	@FXML
	private RadioButton rbtsim;
	@FXML
	private RadioButton rbtnao;
	
	@FXML
	private TextArea txaDescricao;
	
	@FXML
	private ComboBox<String> comboBoxTipoMaterial;
	
	private ToggleGroup grupo = new ToggleGroup();
	
	private ObservableList<String> tiposMaterial = FXCollections.observableArrayList("Livro", "Revista", "Multimidia");

	public CadastroLivroController() {
		
	}
	
	@FXML
	private void initialize() {
		this.dtPublicacao.setValue(LocalDate.now());
		inicializarRadioButton();
		comboBoxTipoMaterial.getItems().addAll(tiposMaterial);		
	}
	
	@FXML
	private void handleSalvar() {
		if (validaCampos()) {
			new LivroDAO().adicionarLivro(new LivroTO(Integer.parseInt(this.txtIdLivro.getText()), this.txtNome.getText(), this.txaDescricao.getText(), 
														this.txtAutor.getText(), Integer.parseInt(this.txtEdicao.getText()), this.txtEditora.getText(), 
														BibliotecaUtils.getTipoMaterial(this.comboBoxTipoMaterial.getValue().toString()), this.rbtsim.isSelected(), 
														false, false, this.dtPublicacao.getValue()));
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Cadastro de Livro");
			alerta.setHeaderText("Livro cadastrado");			
			alerta.setContentText("Novo Livro cadastrado com sucesso");
			alerta.showAndWait();
			
			limparCampos();
		}
	}
	
	private void limparCampos() {
		
		this.txtIdLivro.setText("");
		this.txtNome.setText("");
		this.txtAutor.setText("");
		this.txtEdicao.setText("");
		this.txtEditora.setText("");
		
		this.rbtsim.setSelected(true);

		this.txaDescricao.setText("");
		
		this.comboBoxTipoMaterial.setValue(null);
	}

	private void inicializarRadioButton() {
		this.rbtnao.setToggleGroup(grupo);
		this.rbtsim.setToggleGroup(grupo);
		this.rbtsim.setSelected(true);		
	}

	private boolean validaCampos() {
		String mensagemErro="";
		
		if (this.txtIdLivro.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo de matricula\n";
		}else if (this.txtIdLivro.getText().length() != 5) {
			mensagemErro +="O campo de matricula deve possuir 5 digitos numericos\n";
		}else if (!this.txtIdLivro.getText().matches("[0-9]*")) {
			mensagemErro +="O campo de matricula de possuir apenas numeros\n";
		}
		if (this.txtNome.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo nome\n";
		}
		if (this.txaDescricao.getText().isEmpty()) {
			mensagemErro +="Favor inserir a descrição do livro\n";
		}
		if (this.txtAutor.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo nome\n";
		}
		if (this.txtEdicao.getText().isEmpty()) {
			mensagemErro +="Favor inserir o numero de edição do livro\n";
		}else if (!this.txtEdicao.getText().matches("[0-9]*")) {
			mensagemErro +="A edição deve ser apenas numerica\n";
		}
		if (this.txtEditora.getText().isEmpty()) {
			mensagemErro +="Favor preencher o campo editora\n";
		}
		if (this.comboBoxTipoMaterial.getValue() == null) {
			mensagemErro +="Favor escolher o tipo de material\n";
		}
		
		if (mensagemErro.length() == 0) {
			return true;
		}else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Cadastro de livro");
			alerta.setHeaderText("Não foi possivel realizar o cadastro");			
			alerta.setContentText(mensagemErro);
			alerta.showAndWait();
		}
		return false;
	}
}

package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.LivroDAO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EdicaoLivroController {

	@FXML
	private Label lblIdLivro;
	
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtEdicao;
	@FXML
	private TextField txtEditora;	
	
	@FXML
	private TextArea txaDescricao;
	
	@FXML
	private ComboBox<String> cbTipoMaterial = new ComboBox<>();
	
	@FXML
	private DatePicker dtPublicacao;
	
	@FXML
	private RadioButton rbSim;
	@FXML
	private RadioButton rbNao;
		
	private ObservableList<String> tipoMaterial = FXCollections.observableArrayList("Livro", "Revista", "Multimidia");
	private ToggleGroup grupo = new ToggleGroup();
	private LivroTO livroTO = new LivroTO();
	private Main main;
	private Stage janelaDialogo;
		
	@FXML
	private void initialize() {
		this.cbTipoMaterial.getItems().addAll(tipoMaterial);
		
		this.rbNao.setToggleGroup(grupo);
		this.rbSim.setToggleGroup(grupo);
	}
	
	@FXML
	private void handleSalvar() {
		if (validarCampos()) {			
			this.livroTO.setIdLivro(this.main.getLivroEscolhido().getIdLivro());
			this.livroTO.setNomeLivro(this.txtNome.getText().trim());
			this.livroTO.setDescricao(this.txaDescricao.getText().trim());
			this.livroTO.setAutor(this.txtAutor.getText().trim());
			this.livroTO.setPublicacao(this.dtPublicacao.getValue());
			this.livroTO.setCircular(this.rbSim.isSelected());
			this.livroTO.setEdicao(Integer.parseInt(this.txtEdicao.getText().trim()));
			this.livroTO.setEditora(this.txtEditora.getText().trim());
			this.livroTO.setTipo(BibliotecaUtils.getTipoMaterial(this.cbTipoMaterial.getValue().toString().trim()));

			LivroTO livroTemp = BibliotecaUtils.buscarLivroEscolhido(this.main.getLivroEscolhido().getIdLivro(), this.main);
			if (BibliotecaUtils.compararLivros(this.livroTO, livroTemp).length() == 0) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Alteração de Acervo");
				alerta.setHeaderText("Os dados não foram alterados");
				alerta.setContentText("Altere pelo menos um campo para efetuar alguma alteração no Acervo");
				alerta.showAndWait();
			}else {
				new LivroDAO().alterarLivro(this.livroTO);
				this.main.setLivros(new LivroDAO().acervo());
				this.main.getAcervoController().inicializarTabela();

				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Alteração de Acervo");
				alerta.setHeaderText("Acervo alterado com sucesso");
				alerta.showAndWait();					

				this.janelaDialogo.close();
			}
		}
	}
	
	private boolean validarCampos() {
		String mensagemErro="";
		
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
		if (this.cbTipoMaterial.getValue() == null) {
			mensagemErro +="Favor escolher o tipo de material\n";
		}
		if (this.dtPublicacao.getValue() == null || this.dtPublicacao.getValue().toString().toUpperCase().matches("[A-Z]*")) {
			mensagemErro +="Data invalida\n";
		}
		
		if (mensagemErro.length() == 0) {
			return true;
		}else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Alterar livro");
			alerta.setHeaderText("Não foi possivel realizar a alteração");			
			alerta.setContentText(mensagemErro);
			alerta.showAndWait();
		}
		return false;
	}

	@FXML
	private void hanldeCancelar() {
		this.janelaDialogo.close();
	}
	
	public void mostrarDados() {
		
		
		this.lblIdLivro.setText(Integer.toString(this.main.getLivroEscolhido().getIdLivro()));
		this.txtNome.setText(this.main.getLivroEscolhido().getNomeLivro());
		this.txtAutor.setText(this.main.getLivroEscolhido().getAutor());
		this.txtEdicao.setText(Integer.toString(this.main.getLivroEscolhido().getEdicao()));
		this.txtEditora.setText(this.main.getLivroEscolhido().getEditora());
		this.txaDescricao.setText(this.main.getLivroEscolhido().getDescricao());
		this.cbTipoMaterial.setValue(BibliotecaUtils.setTipoMaterial(this.main.getLivroEscolhido().getTipo()));
		this.dtPublicacao.setValue(this.main.getLivroEscolhido().getPublicacao());
		if (this.main.getLivroEscolhido().getCircular()) {
			this.rbSim.setSelected(true);
		}else {
			this.rbNao.setSelected(true);
		}
	}

	public void setLivroTO(LivroTO livroTO) {
		this.livroTO = livroTO;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setJanelaDialogo(Stage janelaDialogo) {
		this.janelaDialogo = janelaDialogo;
	}
			
}

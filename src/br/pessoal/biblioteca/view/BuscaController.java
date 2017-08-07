package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.LivroDAO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class BuscaController {

	@FXML
	private TextField txtTitulo;
	
	@FXML
	private TableView<LivroTO> tabelaLivro;
	@FXML
	private TableColumn<LivroTO, String>colunaNome;
	
	@FXML
	private Label lblId;
	@FXML
	private Label lblNome;	
	@FXML
	private Label lblAutor;
	@FXML
	private Label lblPublicacao;
	@FXML
	private Label lblEdicao;
	@FXML
	private Label lblEditora;
	@FXML
	private Label lblCirculacao;
	@FXML
	private Label lblEmprestado;
	@FXML
	private Label lblReservado;
	
	@FXML
	private TextArea txaDescricao;
	
	@FXML
	private Pane panelInformacoes;
	
	private Main main;
	private ObservableList<Integer> resultadoBuscaLivros = FXCollections.observableArrayList();
	private ObservableList<LivroTO> livrosRetornados = FXCollections.observableArrayList();
	
	public BuscaController() {
		
	}

	@FXML
	private void initialize() {
		this.colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeLivroProperty());
		mostrarDetalhes(null);
		this.tabelaLivro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarDetalhes(newValue));
	}

	@FXML
	private void handleBuscar() {
		if (!this.txtTitulo.getText().isEmpty()) {			
			this.livrosRetornados.clear();
			resultadoBuscaLivros = new LivroDAO().buscarLivroPorNome(this.txtTitulo.getText());
			for (Integer idLivroDaVez : resultadoBuscaLivros) {
				livrosRetornados.add(BibliotecaUtils.buscarLivroEscolhido(idLivroDaVez, this.main));			
			}
			this.tabelaLivro.setItems(livrosRetornados);
			this.tabelaLivro.setDisable(false);
			this.panelInformacoes.setDisable(false);
			if (livrosRetornados.size() == 0) {
				Alert alerta = new Alert(AlertType.WARNING);				
				alerta.setTitle("Busca de livos");
				alerta.setHeaderText("Nenhum resultado encontrado");
				alerta.setContentText("Não foi possivel achar nenhum resultado com: "+this.txtTitulo.getText().toUpperCase());
				alerta.showAndWait();
				this.tabelaLivro.setDisable(true);
				this.panelInformacoes.setDisable(true);
			}
		}else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Busca de livos");
			alerta.setHeaderText("Nenhum resultado encontrado");
			alerta.setContentText("Favor digitar um titulo no campo de busca");
			alerta.showAndWait();
		}

	}
	
	private void mostrarDetalhes(LivroTO livroTO) {
		if (livroTO == null) {
			this.lblId.setText("");
			this.lblNome.setText("");
			this.txaDescricao.setText("");
			this.lblAutor.setText("");
			this.lblPublicacao.setText("");
			this.lblEdicao.setText("");
			this.lblEditora.setText("");
			this.lblCirculacao.setText("");
			this.lblEmprestado.setText("");
			this.lblReservado.setText("");
		}else {
			this.lblId.setText(Integer.toString(livroTO.getIdLivro()));
			this.lblNome.setText(livroTO.getNomeLivro());
			this.txaDescricao.setText(livroTO.getDescricao());
			this.lblAutor.setText(livroTO.getAutor());
			this.lblPublicacao.setText(BibliotecaUtils.formatarData(livroTO.getPublicacao()));
			this.lblEdicao.setText(Integer.toString(livroTO.getEdicao()));
			this.lblEditora.setText(livroTO.getEditora());
			this.lblCirculacao.setText(livroTO.getCircular()==true?"Sim":"Não");
			this.lblEmprestado.setText(livroTO.getEmprestado()==true?"Sim":"Não");
			this.lblReservado.setText(livroTO.getEmprestado()==true?"Sim":"Não");
		}
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}	
}

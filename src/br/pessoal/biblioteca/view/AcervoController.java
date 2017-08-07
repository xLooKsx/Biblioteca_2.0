package br.pessoal.biblioteca.view;

import com.sun.media.sound.ModelStandardTransform;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.LivroDAO;
import br.pessoal.biblioteca.to.LivroTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class AcervoController {

	@FXML
	private TableView<LivroTO> tabelaLivro;
	
	@FXML
	private TableColumn<LivroTO, String> colunaId;
	@FXML
	private TableColumn<LivroTO, String> colunaNome;
	@FXML
	private TableColumn<LivroTO, String> colunaMaterial;
	@FXML
	private TableColumn<LivroTO, String> colunaCirculacao;
	
	private Main main;	
	
	@FXML
	private void initialize() {
		this.colunaId.setCellValueFactory(cellData -> cellData.getValue().idLivroProperty().asString());
		this.colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeLivroProperty());
		this.colunaMaterial.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
		this.colunaCirculacao.setCellValueFactory(cellData -> cellData.getValue().circularProperty().asString());					
	}

	@FXML
	private void handleDeletar() {
		LivroTO livroSelecionado = tabelaLivro.getSelectionModel().getSelectedItem();
		if (livroSelecionado == null) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Não foi possivel completar a ação desejada");
			alerta.setContentText("Não há nehum item selecionado");
			alerta.showAndWait();
		}else {
			new LivroDAO().deletarLivro(livroSelecionado.getIdLivro());
			this.main.getLivros().remove(livroSelecionado);
		}
	}
	
	@FXML
	private void handleEditar() {
		LivroTO livroSelecionado = tabelaLivro.getSelectionModel().getSelectedItem();
		if (livroSelecionado == null) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Não foi possivel completar a ação desejada");
			alerta.setContentText("Não há nehum item selecionado");
			alerta.showAndWait();
		}else {
			this.main.setLivroEscolhido(livroSelecionado);
			this.main.mostraJanelaEdicaoLivro();
		}
	}
	
	public void inicializarTabela() {
		
		this.tabelaLivro.setItems(this.main.getLivros());
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}

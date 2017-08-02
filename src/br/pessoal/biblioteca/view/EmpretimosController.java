package br.pessoal.biblioteca.view;

import java.time.LocalDate;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.to.EmprestimoTO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmpretimosController {
	
	@FXML
	private TableView<EmprestimoTO> tabela;
	
	@FXML
	private TableColumn<LivroTO, String> colunaNome;
	@FXML
	private TableColumn<EmprestimoTO, LocalDate> colunaDevolucao;
	@FXML
	private TableColumn<EmprestimoTO, LocalDate> colunaEmprestimo;
	
	@FXML
	private Label lblId;
	@FXML
	private Label lblAutor;
	@FXML
	private Label lblPublicacao;
	@FXML
	private Label lblEdicao;
	@FXML
	private Label lblEditora;
	@FXML
	private Label lblTipoMaterial;
	@FXML
	private Label lblMaterialAtrasado;
	
	private Main main;
	
	public EmpretimosController() {
		
	}
	
	@FXML
	private void initialize() {
		this.colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeLivroProperty());
		this.colunaDevolucao.setCellValueFactory(cellData -> cellData.getValue().dataDevolucaoProperty());
		this.colunaEmprestimo.setCellValueFactory(cellData -> cellData.getValue().dataEmprestimoProperty());
		
		tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostraDetalhes(newValue));
	}
	
	private void mostraDetalhes(EmprestimoTO emprestimo) {
		LivroTO livroTO = new BibliotecaUtils().livroEscolhido(emprestimo.getIdLivro(), this.main);	
		
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}

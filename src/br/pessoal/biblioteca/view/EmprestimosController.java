package br.pessoal.biblioteca.view;

import java.time.LocalDate;



import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.EmprestimoDAO;
import br.pessoal.biblioteca.to.EmprestimoTO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class EmprestimosController {
	
	@FXML
	private TableView<EmprestimoTO> tabela;
	
	@FXML
	private TableColumn<EmprestimoTO, String> colunaNome;
	@FXML
	private TableColumn<EmprestimoTO, LocalDate> colunaDevolucao;
	@FXML
	private TableColumn<EmprestimoTO, LocalDate> colunaEmprestimo;
	
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
	private Label lblTipoMaterial;
	@FXML
	private Label lblMaterialAtrasado;
	
	private Main main;
	
	public EmprestimosController() {
		
	}
	
	@FXML
	private void initialize() {
		this.colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeLivroEmprestimoProperty());
		this.colunaDevolucao.setCellValueFactory(cellData -> cellData.getValue().dataDevolucaoProperty());
		this.colunaEmprestimo.setCellValueFactory(cellData -> cellData.getValue().dataEmprestimoProperty());
		
		tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostraDetalhes(newValue));
		
	}
		
	@FXML
	private void handleFinalizaEmprestimo() {
		EmprestimoTO emprestimoSelecionado = tabela.getSelectionModel().getSelectedItem();
		if (emprestimoSelecionado == null) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Não foi possivel completar a ação desejada");
			alerta.setContentText("Não há nehum item selecionado");
			alerta.showAndWait();
		}else {			
			new EmprestimoDAO().finalizaEmprestimo(emprestimoSelecionado.getIdLivro(), this.main.getUsuarioTO().getMatricula());
			this.main.getEmprestimos().remove(emprestimoSelecionado);
			this.main.getPainelBaseController().mostrarInforamacoes();
		}
	}
	
	public void inicializaJanela() {
		this.lblId.setText("");
		this.lblNome.setText("");
		this.lblAutor.setText("");
		this.lblPublicacao.setText("");
		this.lblEdicao.setText("");
		this.lblEditora.setText("");
		this.lblTipoMaterial.setText("");		
		this.lblMaterialAtrasado.setText("");
		this.tabela.setItems(this.main.getEmprestimos());
	}
	
	private void mostraDetalhes(EmprestimoTO emprestimo) {
		if (emprestimo == null) {
			this.lblId.setText("");
			this.lblNome.setText("");
			this.lblAutor.setText("");
			this.lblPublicacao.setText("");
			this.lblEdicao.setText("");
			this.lblEditora.setText("");
			this.lblTipoMaterial.setText("");
			this.lblMaterialAtrasado.setText("");
		}else {
			LivroTO livroTO = new BibliotecaUtils().livroEscolhido(emprestimo.getIdLivro(), this.main);	
			this.lblId.setText(Integer.toString(livroTO.getIdLivro()));
			this.lblNome.setText(emprestimo.getNomeLivro());
			this.lblAutor.setText(livroTO.getAutor());
			this.lblPublicacao.setText(BibliotecaUtils.formatarData(livroTO.getPublicacao()));
			this.lblEdicao.setText(Integer.toString(livroTO.getEdicao()));
			this.lblEditora.setText(livroTO.getEditora());
			this.lblTipoMaterial.setText(BibliotecaUtils.setTipoMaterial(livroTO.getTipo().trim()));
			if (emprestimo.getDataDevolução().isBefore(LocalDate.now())) {
				this.lblMaterialAtrasado.setText("SIM");
			}else {
				this.lblMaterialAtrasado.setText("NÃO");
			}
		}		
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}

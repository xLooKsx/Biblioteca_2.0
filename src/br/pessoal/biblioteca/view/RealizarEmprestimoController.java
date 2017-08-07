package br.pessoal.biblioteca.view;

import br.pessoal.biblioteca.controller.Main;
import br.pessoal.biblioteca.dao.LivroDAO;
import br.pessoal.biblioteca.to.LivroTO;
import br.pessoal.biblioteca.utils.BibliotecaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class RealizarEmprestimoController {

	@FXML
	private TextField txtIdLivro;
	
	@FXML
	private Pane paneDadosLivro;

	@FXML
	private Button btnConfirmar;
	
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
	private Label lblEmprestado;
	@FXML
	private Label lblReserva;
	@FXML
	private Label lblCirculacao;
	@FXML
	private Label lblTipoMaterial;	
	@FXML
	private Label lblDescricao;
	
	private Main main;
	private LivroTO livroTO = new LivroTO();
	
	@FXML
	private void initialize() {
		limparDados();
	}

	@FXML
	private void handleConfirmarEmprestimo() {
		if (this.livroTO.getCircular() && !livroTO.getReservado() && !livroTO.getEmprestado()) {
			if (new LivroDAO().verificaDisponibilidadeLivro(livroTO.getIdLivro())) {
				new LivroDAO().realizarEmprestimoLivro(this.livroTO.getIdLivro());
				
			}else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Emprestimo");
				alerta.setHeaderText("Falha ao escolher livro");
				alerta.setContentText("Livro indisponivel para emprestimo");
				alerta.showAndWait();
			}
		}else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Emprestimo");
			alerta.setHeaderText("Falha ao escolher livro");
			alerta.setContentText("Livro indisponivel para emprestimo");
			alerta.showAndWait();
		}
	}
	
	@FXML
	private void handleBuscar() {
		if (campoValido()) {
			this.livroTO = BibliotecaUtils.buscarLivroEscolhido(Integer.parseInt(this.txtIdLivro.getText()), this.main);
			if (this.livroTO != null) {
				mostrarDadosLivro();
				this.paneDadosLivro.setDisable(false);
				this.btnConfirmar.setDisable(false);
			}
			
		}
	}
	
	private void mostrarDadosLivro() {
		
		
		this.lblNome.setText(livroTO.getNomeLivro());
		this.lblAutor.setText(livroTO.getAutor());
		this.lblPublicacao.setText(BibliotecaUtils.formatarData(livroTO.getPublicacao()));
		this.lblEdicao.setText(Integer.toString(livroTO.getEdicao()));
		this.lblEditora.setText(livroTO.getEditora());
		this.lblEmprestado.setText(livroTO.getEmprestado()==true?"Sim":"Não");
		this.lblReserva.setText(livroTO.getReservado()==true?"Sim":"Não");
		this.lblCirculacao.setText(livroTO.getCircular()==true?"Sim":"Não");
		this.lblTipoMaterial.setText(BibliotecaUtils.setTipoMaterial(livroTO.getTipo()));
		this.lblDescricao.setText(livroTO.getDescricao());
	}

	private boolean campoValido() {
		String menssagemErro="";
		if (this.txtIdLivro.getText().isEmpty()) {
			menssagemErro += "Favor preencher o campo de busca\n";
		}else if (!this.txtIdLivro.getText().matches("[0-9]*")) {
			menssagemErro +="O id deve conter apenas numeros\n";
		}
		
		if (menssagemErro.length() == 0) {
			return true;
		}else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Emprestimo");
			alerta.setHeaderText("Falha ao escolher livro");
			alerta.setContentText(menssagemErro);
			alerta.showAndWait();
		}
		return false;
	}

	private void limparDados() {
		this.txtIdLivro.setText("");
		this.btnConfirmar.setDisable(true);
		this.lblNome.setText("");
		this.lblAutor.setText("");
		this.lblPublicacao.setText("");
		this.lblEdicao.setText("");
		this.lblEditora.setText("");
		this.lblEmprestado.setText("");
		this.lblReserva.setText("");
		this.lblCirculacao.setText("");
		this.lblTipoMaterial.setText("");
		this.lblDescricao.setText("");
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
}

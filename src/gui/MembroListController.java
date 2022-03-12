package gui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Membro;
import model.entities.enums.StatusMembro;
import model.services.MembServices;

public class MembroListController implements Initializable {

	private MembServices service;

	@FXML
	private TableView<Membro> tableViewMembro;

	@FXML
	private TableColumn<Membro, Integer> tableColumnId;

	@FXML
	private TableColumn<Membro, String> tableColumnNome;

	@FXML
	private TableColumn<Membro, Date> tableColumnNas;

	@FXML
	private TableColumn<Membro, String> tableColumnGenero;

	@FXML
	private TableColumn<Membro, String> tableColumnEmail;

	@FXML
	private TableColumn<Membro, String> tableColumnEndereco;

	@FXML
	private TableColumn<Membro, String> tableColumnBairro;

	@FXML
	private TableColumn<Membro, String> tableColumnTelefone;

	@FXML
	private TableColumn<Membro, String> tableColumnEstCivil;

	@FXML
	private TableColumn<Membro, String> tableColumnConjuge;

	@FXML
	private TableColumn<Membro, Integer> tableColumnIdConjuge;

	@FXML
	private TableColumn<Membro, String> tableColumnPgm;

	@FXML
	private TableColumn<Membro, String> tableColumnQtdFilhos;

	@FXML
	private TableColumn<Membro, String> tableColumnRG;

	@FXML
	private TableColumn<Membro, String> tableColumnCPF;

	@FXML
	private TableColumn<Membro, StatusMembro> tableColumnStatus;

	@FXML
	private TableColumn<Membro, Integer> tableColumnIgreja;

	private ObservableList<Membro> obsList;

	@FXML
	private Button btNew;

	@FXML
	public void onBtNewAction() {
		System.out.println("OnBtNewAction");
	}

	public void setMembService(MembServices service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnNas.setCellValueFactory(new PropertyValueFactory<>("dataDeNascimento"));
		tableColumnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tableColumnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
		tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tableColumnEstCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
		tableColumnConjuge.setCellValueFactory(new PropertyValueFactory<>("conjuge"));
		tableColumnIdConjuge.setCellValueFactory(new PropertyValueFactory<>("idConjuge"));
		tableColumnPgm.setCellValueFactory(new PropertyValueFactory<>("pgm"));
		tableColumnQtdFilhos.setCellValueFactory(new PropertyValueFactory<>("qtdFilhos"));
		tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
		tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tableColumnIgreja.setCellValueFactory(new PropertyValueFactory<>("igreja"));

		// Ajuste para a tablea acompanhar a altura da janela principal (stage)
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewMembro.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("o Service estava nulo");
		}

		List<Membro> list = service.listaMembros();

		obsList = FXCollections.observableArrayList(list);

		tableViewMembro.setItems(obsList);

	}

}

package gui;

import java.net.URL;
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
import model.entities.Pgm;
import model.entities.enums.StatusPgm;
import model.services.PgmServices;

public class PgmListController implements Initializable {

	private PgmServices service;

	@FXML
	private TableView<Pgm> tableViewPgm;

	@FXML
	private TableColumn<Pgm, Integer> tableColumnId;

	@FXML
	private TableColumn<Pgm, String> tableColumnNome;

	@FXML
	private TableColumn<Pgm, String> tableColumnStatus;

	@FXML
	private TableColumn<Pgm, StatusPgm> tableColumnIdIgrejaPgm;

	private ObservableList<Pgm> obsList;

	@FXML
	private Button btNew;

	@FXML
	public void onBtNewAction() {
		System.out.println("OnBtNewAction");
	}

	public void setPgmService(PgmServices service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tableColumnIdIgrejaPgm.setCellValueFactory(new PropertyValueFactory<>("idIgreja"));

		// Ajuste para a tablea acompanhar a altura da janela principal (stage)
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewPgm.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("o Service estava nulo");
		}

		List<Pgm> list = service.listPgms();

		obsList = FXCollections.observableArrayList(list);

		tableViewPgm.setItems(obsList);

	}

}

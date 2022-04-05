package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Pgm pgm = new Pgm();
		createDialogForm(pgm, "/gui/PgmForm.fxml", parentStage);
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
	
	private void createDialogForm (Pgm pgm, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // recebe o nome da view que será carregada
			Pane pane = loader.load();
			
			PgmFormController controller = loader.getController();
			controller.setPgm(pgm);
			controller.updateFormData();
			controller.setService(service);
			
			//Quando vou carregar uma janela na frente da outra precisa instanciar um novo Stage
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entrada de dados de PGM");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);// trava de redimencionamento
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}

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
import model.entities.Igreja;
import model.services.IgrejaServices;

public class IgrejaListController implements Initializable {

	private IgrejaServices service;

	// Referêncas para os quatro componentes: Botão table view e as duas colunas da
	// tabela

	@FXML
	private TableView<Igreja> tableViewIgreja;// Referência para tabela

	@FXML
	private TableColumn<Igreja, Integer> tableColumnId;// O primeiro tipo é a entidade e a segunda é o tipo da										// coluna

	@FXML
	private TableColumn<Igreja, String> tableColumnNome;

	@FXML
	private TableColumn<Igreja, String> tableColumnDenominacao;

	@FXML
	private TableColumn<Igreja, String> tableColumnCNPJ;

	private ObservableList<Igreja> obsList;

	@FXML
	private Button btNew;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Igreja igreja = new Igreja();
		createDialogForm(igreja, "/gui/IgrejaForm.fxml", parentStage);
	}

	public void setIgrejaService(IgrejaServices service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	// Inicialização do comprtamento da tabela
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnDenominacao.setCellValueFactory(new PropertyValueFactory<>("denominacao"));
		tableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

		// Ajuste para a tablea acompanhar a altura da janela principal (stage)
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewIgreja.prefHeightProperty().bind(stage.heightProperty());
	}

	// Metodo responsavel por acessar os serviços carregar os departameentos e
	// repassar para o observable list.
	// o Observable list é associado ao table view e os departamentos são
	// apresentados na tela
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("o Service estava nulo");
		}

		List<Igreja> list = service.listaIgrejas();

		obsList = FXCollections.observableArrayList(list);

		tableViewIgreja.setItems(obsList);

	}
	
	// Esta classe cria uma janela modal (diálogo)
	// Recebe como parametro o palco que chamou a janela e a tela
	private void createDialogForm (Igreja igreja, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // recebe o nome da view que será carregada
			Pane pane = loader.load();
			
			IgrejaFormController controller = loader.getController();
			controller.setIgreja(igreja);
			controller.setIgrejaService(new IgrejaServices());
			controller.updateFormData();
			
			//Quando vou carregar uma janela na frente da outra precisa instanciar um novo Stage
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entrada de dados de Igreja");
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

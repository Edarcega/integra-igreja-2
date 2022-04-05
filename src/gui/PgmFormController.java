package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Pgm;
import model.entities.enums.StatusPgm;
import model.services.PgmServices;

public class PgmFormController implements Initializable {
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	private Pgm pgm;

	private PgmServices service;

	@FXML
	TextField txtId;

	@FXML
	TextField txtNome;

	@FXML
	TextField txtStatus;

	@FXML
	TextField txtIdIgreja;

	@FXML
	private Label labelErrorNome;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (pgm == null) {
			throw new IllegalStateException("Entidade estava nula");
		}

		if (pgm == null) {
			throw new IllegalStateException("Service estava nulo");
		}
		try {
			pgm = getFormData();
			service.saveOrUpdate(pgm);
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}

	}

	private Pgm getFormData() {
		Pgm obj = new Pgm();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setNome(txtNome.getText());
		obj.setStatus(StatusPgm.valueOf(txtStatus.getText()));
		obj.setIdIgreja(Integer.parseInt(txtIdIgreja.getText()));
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 60);
		Constraints.setTextFieldInteger(txtIdIgreja);
		Constraints.setTextFieldMaxLength(txtStatus, 60);
	}

	public void setPgm(Pgm pgm) {
		this.pgm = pgm;
	}

	public void updateFormData() {
		if (pgm == null) {
			throw new IllegalStateException("Entidade estava vazia");
		}
		txtId.setText(String.valueOf(pgm.getId()));
		txtNome.setText(pgm.getNome());
		txtStatus.setText(String.valueOf(pgm.getStatus()));
		txtIdIgreja.setText(String.valueOf(pgm.getIdIgreja()));
	}

	public void setService(PgmServices service) {
		this.service = service;
	}

}

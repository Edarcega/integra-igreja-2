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
import model.entities.Igreja;
import model.services.IgrejaServices;

public class IgrejaFormController implements Initializable {

	private Igreja igreja;

	private IgrejaServices service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtDenominacao;

	@FXML
	private TextField txtCNPJ;

	@FXML
	private Label labelErrorName;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (igreja == null) {
			throw new IllegalStateException("Entidade estava nula");
		}
		
		if (service == null) {
			throw new IllegalStateException("Service estava nulo");
		}
		try {
			igreja = getFormData();
			service.saveOrUpdate(igreja);
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}
		
	}

	// Responsavel por pegar os dados do formulario e isntanciar um departamento
	private Igreja getFormData() {
		Igreja obj = new Igreja();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setNome(txtNome.getText());
		obj.setCnpj(txtCNPJ.getText());
		obj.setDenominacao(txtDenominacao.getText());
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

	// Classe responsavel por implementar as restrições
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtCNPJ, 14);
		Constraints.setTextFieldMaxLength(txtDenominacao, 30);
		Constraints.setTextFieldMaxLength(txtNome, 60);
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}

	public void setIgrejaService(IgrejaServices service) {
		this.service = service;
	}

	public void updateFormData() {
		if (igreja == null) {
			throw new IllegalStateException("Entidade estava vazia");
		}
		txtId.setText(String.valueOf(igreja.getId()));
		txtNome.setText(igreja.getNome());
		txtDenominacao.setText(igreja.getDenominacao());
		txtCNPJ.setText(igreja.getCnpj());
	}

}

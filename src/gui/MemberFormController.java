package gui;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import db.DbException;
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
import model.entities.Membro;
import model.entities.enums.StatusCivil;
import model.entities.enums.StatusMembro;
import model.services.MembServices;

public class MemberFormController implements Initializable {

	private Membro membro;

	private MembServices service;

	@FXML
	TextField txtId;

	@FXML
	TextField txtStatus;

	@FXML
	TextField txtNome;

	@FXML
	TextField txtNascimeto;

	@FXML
	TextField txtGenero;

	@FXML
	TextField txtEmail;

	@FXML
	TextField txtEndereco;

	@FXML
	TextField txtBairro;

	@FXML
	TextField txtTelefone;

	@FXML
	TextField txtEstadoCivil;

	@FXML
	TextField txtNomConjuge;

	@FXML
	TextField txtIdConjuge;

	@FXML
	TextField txtIdIgreja;

	@FXML
	TextField txtIgreja;

	@FXML
	TextField txtPgm;

	@FXML
	TextField txtIdPgm;

	@FXML
	TextField txtNomesFilhos;

	@FXML
	TextField txtQtdFilhos;

	@FXML
	TextField txtIdFilhos;

	@FXML
	TextField txtRG;

	@FXML
	TextField txtCPF;

	@FXML
	private Label labelErrorNome;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	@FXML
	public void onBtSaveAction(ActionEvent event) throws ParseException {
		if (membro == null) {
			throw new IllegalStateException("Entidade estava nula");
		}

		if (service == null) {
			throw new IllegalStateException("Service estava nulo");
		}
		try {
			membro = getFormData();
			service.saveOrUpdate(membro);
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private Membro getFormData() throws ParseException {
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
		Membro obj = new Membro();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setStatus(StatusMembro.valueOf(txtStatus.getText()));
		obj.setNome(txtNome.getText());
		obj.setDataDeNascimento(sdf.parse(txtNascimeto.getText()));
		obj.setGenero(txtGenero.getText());
		obj.setEmail(txtEmail.getText());
		obj.setEndereco(txtEndereco.getText());
		obj.setBairro(txtBairro.getText());
		obj.setTelefone(txtTelefone.getText());
		obj.setEstadoCivil(StatusCivil.valueOf(txtEstadoCivil.getText()));
		obj.setConjuge(txtNomConjuge.getText());
		obj.setIdConjuge(Integer.parseInt(txtIdConjuge.getText()));
		//obj.setIgreja(new ig); Resolver aqui
		//obj.setPgm(null);
		obj.setNomeFilho(txtNomesFilhos.getText());
		obj.setQtdFilhos(Integer.parseInt(txtQtdFilhos.getText()));
		obj.setIdFilhos(txtIdFilhos.getText());
		obj.setRg(txtRG.getText());
		obj.setCpf(txtCPF.getText());
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
		Constraints.setTextFieldMaxLength(txtStatus, 60);
		Constraints.setTextFieldMaxLength(txtNome, 60);
		Constraints.setTextFieldMaxLength(txtNascimeto, 60);
		Constraints.setTextFieldMaxLength(txtGenero, 1);
		Constraints.setTextFieldMaxLength(txtEmail, 60);
		Constraints.setTextFieldMaxLength(txtEndereco, 60);
		Constraints.setTextFieldMaxLength(txtBairro, 60);
		Constraints.setTextFieldMaxLength(txtTelefone, 60);
		Constraints.setTextFieldMaxLength(txtEstadoCivil, 30);
		Constraints.setTextFieldMaxLength(txtNomConjuge, 60);
		Constraints.setTextFieldInteger(txtIdConjuge);
		Constraints.setTextFieldInteger(txtIdIgreja);
		Constraints.setTextFieldInteger(txtIdPgm);
		Constraints.setTextFieldMaxLength(txtNomesFilhos, 180);
		Constraints.setTextFieldInteger(txtQtdFilhos);
		Constraints.setTextFieldMaxLength(txtIdFilhos, 30);
		Constraints.setTextFieldMaxLength(txtRG, 30);
		Constraints.setTextFieldMaxLength(txtRG, 11);
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public void updateFormData() {
		if (membro == null) {
			throw new IllegalStateException("Entidade estava vazia");
		}
		txtId.setText(String.valueOf(membro.getId()));
		txtStatus.setText(String.valueOf(membro.getStatus()));
		txtNome.setText(membro.getNome());
		txtNascimeto.setText(String.valueOf(membro.getDataDeNascimento()));
		txtGenero.setText(membro.getGenero());
		txtEmail.setText(membro.getEmail());
		txtEndereco.setText(membro.getEndereco());
		txtBairro.setText(membro.getBairro());
		txtTelefone.setText(membro.getTelefone());
		txtEstadoCivil.setText(String.valueOf(membro.getEstadoCivil()));
		txtNomConjuge.setText(membro.getConjuge());
		txtIdConjuge.setText(String.valueOf(membro.getIdConjuge()));
		// txtIdIgreja.setText(String.valueOf(membro.getIgreja().getId()));
		// txtIgreja.setText(membro.getIgreja().getNome());
		// txtIdPgm.setText(String.valueOf(membro.getPgm().getId()));
		// txtPgm.setText(membro.getPgm().getNome());
		txtNomesFilhos.setText(membro.getIdFilhos());
		txtQtdFilhos.setText(String.valueOf(membro.getQtdFilhos()));
		txtIdFilhos.setText(membro.getIdFilhos());
		txtRG.setText(membro.getRg());
		txtCPF.setText(membro.getCpf());
	}

	public void setMembServices(MembServices service) {
		this.service = service;
	}

}

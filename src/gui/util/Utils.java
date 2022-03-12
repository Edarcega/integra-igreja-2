package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	// Esta classe retorna o Stage (Palco) atual
	// O Argumento recebido ActionEvent � o evento que o bot�o por exemplo recebeu
	public static Stage currentStage(ActionEvent event) {
		return (Stage)((Node) event.getSource()).getScene().getWindow();
	}

}

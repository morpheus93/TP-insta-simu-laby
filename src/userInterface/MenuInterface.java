package userInterface;

import alpha.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuInterface implements Initializable, ControlledScreen {

	private ScreensController myController;

	@FXML
	private ComboBox<String> comboBoxAlgo1;

	@FXML
	private ComboBox<String> comboBoxAlgo2;

	@FXML
	private Label errorMessage;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		errorMessage.setVisible(false);

	}

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@FXML
	private void goToGame() {
		String algo1 = comboBoxAlgo1.getValue();
		String algo2 = comboBoxAlgo2.getValue();
		boolean hasSelectedAlgo = true;
		if (algo1 == null || algo2 == null) {
			hasSelectedAlgo = false;
			errorMessage.setVisible(true);
		}

		if (hasSelectedAlgo) {
			errorMessage.setVisible(false);
			myController.setScreen(Main.screen2ID);
		}
	}
}

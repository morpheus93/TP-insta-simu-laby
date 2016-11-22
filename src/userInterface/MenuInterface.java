package userInterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import tools.GameLogs;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuInterface implements Initializable, ControlledScreen {

	private ScreensController screensController;

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
		screensController = screenParent;
	}

	@Override
	public void init() { }

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
			GameLogs.getInstance().addLog("Algorithms selected");
			GameLogs.getInstance().addLog("Algorithms 1 is " + algo1);
			GameLogs.getInstance().addLog("Algorithms 2 is " + algo2);
			// TODO : Send algos to Engine
			screensController.setScreen(ScreensController.SCREEN_2_ID);
		}
	}
}

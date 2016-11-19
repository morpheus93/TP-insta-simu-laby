package userInterface;

import alpha.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GameInterface implements Initializable, ControlledScreen {

	private ScreensController myController;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	public void setScreenParent(ScreensController screenParent){
		myController = screenParent;
	}

	@FXML
	private void goToScreen1(){
		myController.setScreen(Main.screen1ID);
	}

	@FXML
	private void goToScreen3(){
		myController.setScreen(Main.screen3ID);
	}
}

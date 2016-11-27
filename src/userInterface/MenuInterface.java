package userInterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import specifications.EngineService;
import specifications.RequireEngineService;
import tools.GameLogs;
import tools.Resolver;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MenuInterface implements Initializable, ControlledScreen, RequireEngineService {

	private ScreensController screensController;

	private EngineService engine;

	@FXML
	private ComboBox<String> comboBoxAlgo1;

	@FXML
	private ComboBox<String> comboBoxAlgo2;

	@FXML
	private Label errorMessage;


	private final HashMap<String, Resolver> algoList;

	public MenuInterface() {
		this.algoList = new HashMap<>();
		this.algoList.put("Random", Resolver.RANDOM);
		this.algoList.put("Plaidge", Resolver.PLEDGE);
		this.algoList.put("Dijkstra", Resolver.ASTAR);
		this.algoList.put("Trémeau", Resolver.DEPTH_FIRST);
	}

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
	public void init() {
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
			GameLogs.getInstance().addLog("Algorithms selected");
			GameLogs.getInstance().addLog("Algorithms 1 is " + algo1);
			GameLogs.getInstance().addLog("Algorithms 2 is " + algo2);
			this.engine.addResolver(this.algoList.get(algo1));
			this.engine.addResolver(this.algoList.get(algo2));
			screensController.setScreen(ScreensController.SCREEN_2_ID);
			this.engine.start();
			GameLogs.getInstance().addLog("Engine is started");
		}
	}

	@Override
	public void bindEngineService(EngineService service) {
		if (service != null) {
			this.engine = service;
		}
	}
}

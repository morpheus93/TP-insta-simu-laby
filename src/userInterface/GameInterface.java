package userInterface;

import data.Labyrinth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ListView;
import specifications.ReadService;
import specifications.RequireReadService;
import tools.GameLogs;

import java.net.URL;
import java.util.ResourceBundle;

public class GameInterface implements Initializable, ControlledScreen, RequireReadService {

	@FXML
	public Canvas canvas1;

	@FXML
	public LineChart statsContainer;

	@FXML
	public Canvas canvas2;

	@FXML
	public ListView<String> logContainer;

	private ObservableList<String> logs = FXCollections.observableArrayList();

	private ReadService readService;

	private Labyrinth laby;

	private ScreensController screensController;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	@Override
	public void init() {
		this.drawLabyrinth();
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		// screensController = screenParent;
	}

	@Override
	public void bindReadService(ReadService service) {
		this.readService = service;
	}

	/**
	 * Get logs from Logger instance and display it
	 */
	public void updateLogs() {
		logContainer.setItems(GameLogs.getInstance().getLogs());
		logContainer.scrollTo(logs.size());
	}

	/**
	 * Draw Labyrinth
	 */
	private void drawLabyrinth() {
		GameLogs.getInstance().addLog("Drawing labyrinth");
		this.laby = readService.getLabyrinth();
		GraphicsContext gc = canvas1.getGraphicsContext2D();
		gc.fillOval(10, 10, 10, 10);
		// TODO : Draw laby to canvas id : canvas1 and canvas2
	}

	public void drawBot(){
		// TODO Draw bot
	}
}

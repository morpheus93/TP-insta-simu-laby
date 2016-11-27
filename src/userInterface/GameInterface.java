package userInterface;

import data.Case;
import data.Labyrinth;
import data.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import specifications.EngineService;
import specifications.ReadService;
import specifications.RequireEngineService;
import specifications.RequireReadService;
import tools.GameLogs;

import java.net.URL;
import java.util.ResourceBundle;

public class GameInterface implements Initializable, ControlledScreen, RequireReadService, RequireEngineService {

	@FXML
	public Canvas canvas1;

	@FXML
	public LineChart<String, Double> statsContainer;

	@FXML
	public Canvas canvas2;

	@FXML
	public ListView<String> logContainer;

	@FXML
	public Button quit_btn;

	private ObservableList<String> logs = FXCollections.observableArrayList();

	private ReadService readService;

	private EngineService engineService;

	private ScreensController screensController;

	private Position latestPositionBot1 = new Position(0, 0);

	private Position latestPositionBot2 = new Position(0, 0);

	private int sizeCell = 0;

	private XYChart.Series seriesBot1 = new XYChart.Series<>();

	private XYChart.Series seriesBot2 = new XYChart.Series<>();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		statsContainer.setData(this.updateStats());
		statsContainer.setTitle("Chart");

	}

	@Override
	public void init() {
		this.drawLabyrinth();
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
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
	 *
	 */
	public ObservableList<XYChart.Series<String, Double>> updateStats() {
		double aValue = 1.56;
		double cValue = 1.06;
		ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
		XYChart.Series<String, Double> aSeries = new XYChart.Series<>();
		XYChart.Series<String, Double> cSeries = new XYChart.Series<>();

		for (int i = 2011; i < 2021; i++) {
			aSeries.getData().add(new XYChart.Data(Integer.toString(i), aValue));
			aValue = aValue + Math.random() - .5;
			cSeries.getData().add(new XYChart.Data(Integer.toString(i), cValue));
			cValue = cValue + Math.random() - .5;
		}
		answer.addAll(aSeries, cSeries);
		return answer;
	}

	/**
	 * Draw Labyrinth
	 */
	private void drawLabyrinth() {
		
		GameLogs.getInstance().addLog("Drawing labyrinth");
		Labyrinth laby = readService.getLabyrinth();
		GraphicsContext gc1 = canvas1.getGraphicsContext2D();
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		int x = 0;
		int y = 0;
		this.sizeCell = ((int) canvas1.getWidth() / laby.getWidth() - 20);

		gc1.strokeLine(0, 0, this.sizeCell * laby.getWidth(), 0);
		gc2.strokeLine(0, 0, this.sizeCell * laby.getWidth(), 0);
		gc1.strokeLine(0, 0, 0, this.sizeCell * laby.getHeight());
		gc2.strokeLine(0, 0, 0, this.sizeCell * laby.getHeight());

		for (int i = 0; i < laby.getSize(); i++) {
			Case cell = laby.getCase(i);
			x += this.sizeCell;

			if (!cell.isRightIsOpen()) {
				gc1.strokeLine(x, y, x, y + this.sizeCell);
				gc2.strokeLine(x, y, x, y + this.sizeCell);
			}
			if (!cell.isDownIsOpen()) {
				gc1.strokeLine(x - this.sizeCell, y + this.sizeCell, x, y + this.sizeCell);
				gc2.strokeLine(x - this.sizeCell, y + this.sizeCell, x, y + this.sizeCell);
			}
			if (x == this.sizeCell * laby.getWidth()) {
				x = 0;
				y += this.sizeCell;
			}
		}
	}

	public void drawBots() {
		GraphicsContext gc1 = canvas1.getGraphicsContext2D();
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		Position bot1Position = readService.getBots().get(0).getPosition();
		Position bot2Position = readService.getBots().get(1).getPosition();

		int bot1X = bot1Position.getX() * this.sizeCell + this.sizeCell / 2 - 5;
		int bot1Y = bot1Position.getY() * this.sizeCell + this.sizeCell / 2 - 5;
		int bot2X = bot2Position.getX() * this.sizeCell + this.sizeCell / 2 - 5;
		int bot2Y = bot2Position.getY() * this.sizeCell + this.sizeCell / 2 - 5;

		gc1.setFill(Color.WHITE);
		gc1.fillOval(latestPositionBot1.getX(), latestPositionBot1.getY(), 10, 10);

		gc1.setFill(Color.BLACK);
		gc1.fillOval(bot1X, bot1Y, 10, 10);

		gc2.setFill(Color.WHITE);
		gc2.fillOval(latestPositionBot2.getX(), latestPositionBot2.getY(), 10, 10);

		gc2.setFill(Color.BLACK);
		gc2.fillOval(bot2X, bot2Y, 10, 10);

		this.latestPositionBot1 = new Position(bot1X, bot1Y);
		this.latestPositionBot2 = new Position(bot2X, bot2Y);

		if (this.engineService.isFinish()) {
			GameLogs.getInstance().addLog("Replay ?");
		}
	}

	@Override
	public void bindEngineService(EngineService service) {
		this.engineService = service;
	}

	public void quitGame(ActionEvent actionEvent) {
		System.exit(0);
	}
}

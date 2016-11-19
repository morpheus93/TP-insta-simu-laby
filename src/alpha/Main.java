package alpha;

import javafx.scene.Group;
import tools.HardCodedParameters;

import specifications.DataService;
import specifications.EngineService;
import specifications.LabyrinthFactoryService;
import specifications.ViewerService;

import data.Data;
import engine.Engine;
import factory.LabyrinthFactory;
import userInterface.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class Main extends Application {

	private static String screen1File = "/resources/menu.fxml";
	private static String screen2File = "/resources/game.fxml";
	private static String screen3File = "/resources/credits.fxml";

	public static String screen1ID = "menu";
	public static String screen2ID = "game";
	public static String screen3ID = "credits";

	public static void main(String[] args) {
		DataService data = new Data();
		EngineService engine = new Engine();
		ViewerService viewer = new Viewer();

		LabyrinthFactoryService labyrinthFactory = new LabyrinthFactory();

		((Viewer) viewer).bindReadService(data);
		((Engine) engine).bindDataService(data);
		((Engine) engine).bindLabyrinthFactoryService(labyrinthFactory);

		data.init();
		engine.init();
		viewer.init();

		launch(args);
	}

	/**
	 * Start method
	 *
	 * @param primaryStage Stage
	 * @throws IOException
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {

		ScreensController mainContainer = new ScreensController();
		mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
		mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
		mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
		mainContainer.setScreen(Main.screen1ID);

		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simulateur de Labyrinthe");
		primaryStage.setMinWidth(HardCodedParameters.defaultWidth);
		primaryStage.setMinHeight(HardCodedParameters.defaultHeight);
		primaryStage.setMaxHeight(HardCodedParameters.defaultHeight);
		primaryStage.setMaxWidth(HardCodedParameters.defaultWidth);
		primaryStage.show();

	}
}

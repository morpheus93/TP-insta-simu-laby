package alpha;

import tools.HardCodedParameters;

import specifications.DataService;
import specifications.EngineService;
import specifications.ViewerService;

import data.Data;
import engine.Engine;
import userInterface.Viewer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Main extends Application {
	// ---VARIABLES---//
	private static DataService data;
	private static EngineService engine;
	private static ViewerService viewer;

	// ---EXECUTABLE---//
	public static void main(String[] args) {
		data = new Data();
		engine = new Engine();
		viewer = new Viewer();

		//((Engine) engine).bindDataService(data);
		((Viewer) viewer).bindReadService(data);

		data.init();
		engine.init();
		viewer.init();

		launch(args);
	}

	@Override
	public void start(Stage stage) {
		final Scene scene = new Scene(((Viewer) viewer).getPanel());

		scene.setFill(Color.CORNFLOWERBLUE);

		stage.setScene(scene);
		stage.setWidth(HardCodedParameters.defaultWidth);
		stage.setHeight(HardCodedParameters.defaultHeight);
		
		stage.setOnShown(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				engine.start();
			}
		});

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				engine.stop();
			}
		});
		stage.show();
	}
}

package userInterface;

import specifications.ViewerService;
import specifications.ReadService;
import specifications.RequireReadService;

import javafx.scene.Group;
import javafx.scene.Parent;

public class Viewer implements ViewerService, RequireReadService {
	private ReadService data;

	public Viewer() {
	}

	@Override
	public void bindReadService(ReadService service) {
		data = service;
	}

	@Override
	public void init() {
	}

	@Override
	public Parent getPanel() {
		Group panel = new Group();

		return panel;
	}
}

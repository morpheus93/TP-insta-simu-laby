package engine;

import tools.Action;
import tools.HardCodedParameters;
import specifications.DataService;
import specifications.EngineService;
import specifications.RequireDataService;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Engine implements EngineService, RequireDataService {
	private Timer engineClock;
	private int currentIndexBot;
	private DataService data;

	public Engine() {
		this.currentIndexBot = 0;
	}

	@Override
	public void init() {
		engineClock = new Timer();
	}

	@Override
	public void start() {
		engineClock.schedule(new TimerTask() {
			public void run() {
				// core
			}
		}, 0, HardCodedParameters.enginePaceMillis);
	}

	@Override
	public void stop() {
		engineClock.cancel();
	}
	
	@Override
	public void bindDataService(DataService service) {
		this.data = service;
	}

	@Override
	public boolean canMove(Action action) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Action> getHistories() {
		return this.data.getHistories(this.currentIndexBot);
	}
}

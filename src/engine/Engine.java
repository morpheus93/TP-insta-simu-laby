package engine;

import tools.Action;
import tools.HardCodedParameters;
import specifications.DataService;
import specifications.EngineService;
import specifications.LabyrinthFactoryService;
import specifications.RequireDataService;
import specifications.RequireLabyrinthFactoryService;
import specifications.RequireResolverFactoryService;
import specifications.RequireResolverService;
import specifications.ResolverFactoryService;
import specifications.ResolverService;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Engine implements EngineService, RequireDataService, RequireResolverFactoryService, RequireLabyrinthFactoryService {
	private Timer engineClock;
	private int currentIndexBot;
	private DataService data;
	private ResolverFactoryService resolverFactory;
	private LabyrinthFactoryService labyrinthFactory;
	private ArrayList<ResolverService> resolvers;

	public Engine() {
		this.currentIndexBot = 0;
		this.resolvers = new ArrayList<ResolverService>();
	}

	@Override
	public void init() {
		engineClock = new Timer();
		this.data.setLabyrinth(this.labyrinthFactory.getLabyrinth());

		System.out.println(this.data.getLabyrinth()); // TODO : To remove
	}

	@Override
	public void start() {
		engineClock.schedule(new TimerTask() {
			public void run() {
				// core
				// TODO for each resolvers
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
		return this.data.canMove(this.currentIndexBot, action);
	}

	@Override
	public ArrayList<Action> getHistories() {
		return this.data.getHistories(this.currentIndexBot);
	}

	@Override
	public void bindResolverFactoryService(ResolverFactoryService service) {
		this.resolverFactory = service;
	}

	@Override
	public void bindLabyrinthFactoryService(LabyrinthFactoryService service) {
		this.labyrinthFactory = service;
	}
}
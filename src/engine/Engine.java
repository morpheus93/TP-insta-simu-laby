package engine;

import tools.Action;
import tools.HardCodedParameters;
import tools.Resolver;
import specifications.DataService;
import specifications.EngineService;
import specifications.InfoService;
import specifications.LabyrinthFactoryService;
import specifications.RequireDataService;
import specifications.RequireLabyrinthFactoryService;
import specifications.RequireResolverFactoryService;
import specifications.ResolverFactoryService;
import specifications.ResolverService;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import data.Bot;

public class Engine implements EngineService, RequireDataService, RequireResolverFactoryService, RequireLabyrinthFactoryService, InfoService {
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
				
				currentIndexBot = 0;
				
				for (ResolverService resolver : resolvers) {
					currentIndexBot++;

					int currentCaseId = data.getCaseId(currentIndexBot);

					Action action = resolver.step(currentCaseId);

					if (data.canMove(currentIndexBot, action)) {
						data.moveBot(currentIndexBot, action);
					}
				}
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

	@Override
	public void addResolver(Resolver resolver) {
		this.resolvers.add(
				this.resolverFactory.getResolver(resolver, this.data.getLabyrinth(), this)
		);
		
		Bot bot = new Bot(0, 0);

		data.addBot(bot);
	}
}

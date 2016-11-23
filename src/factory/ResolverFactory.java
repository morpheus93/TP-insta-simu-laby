package factory;

import algorithm.RandomResolver;
import data.Labyrinth;
import engine.Engine;
import specifications.ResolverFactoryService;
import specifications.ResolverService;
import tools.Resolver;

public class ResolverFactory implements ResolverFactoryService {

	@Override
	public ResolverService getResolver(Resolver resolver, Labyrinth labyrinth, Engine engine) {
		
		ResolverService resolverService;
		
		switch(resolver) {
			case ASTAR:
				resolverService = new RandomResolver();
				break;

			case DEPTH_FIRST:
				resolverService = new RandomResolver();
				break;

			case PLEDGE:
				resolverService = new RandomResolver();
				break;

			case RANDOM:
				resolverService = new RandomResolver();
				break;
				
			default:
				resolverService = new RandomResolver();
		}

		resolverService.init();
		
		return resolverService;
	}
}

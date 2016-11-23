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
		switch(resolver) {
			case ASTAR:
				break;

			case DEPTH_FIRST:
				break;

			case PLEDGE:
				break;

			case RANDOM:
				return new RandomResolver();

			default:
				return null;
		}
		
		return null;
	}
}

package factory;

import data.Labyrinth;
import specifications.ResolverFactoryService;
import specifications.ResolverService;
import tools.Resolver;

public class ResolverFactory implements ResolverFactoryService {

	@Override
	public ResolverService getResolver(Resolver resolver, Labyrinth labyrinth) {
		switch(resolver) {
			case ASTAR:
				break;

			case DEPTH_FIRST:
				break;

			case PLEDGE:
				break;

			case RANDOM:
				break;

			default:
				return null;
		}
		
		return null;
	}
}

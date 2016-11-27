package factory;

import algorithm.DepthFirstResolver;
import algorithm.PledgeResolver;
import algorithm.SmartRandomResolver;
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
			case DEPTH_FIRST:
				resolverService = new DepthFirstResolver();
				break;

			case PLEDGE:
				resolverService = new PledgeResolver();
				break;

			case RANDOM:
				resolverService = new SmartRandomResolver();
				break;
				
			default:
				resolverService = new SmartRandomResolver();
		}

		((algorithm.Resolver) resolverService).bindInfoService(engine);
		resolverService.init();
		
		return resolverService;
	}
}

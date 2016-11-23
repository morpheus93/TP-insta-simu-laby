package algorithm;

import java.util.ArrayList;

import specifications.InfoService;
import specifications.RequireInfoService;
import specifications.ResolverService;

public abstract class Resolver implements ResolverService, RequireInfoService {
	protected ArrayList<Integer> caseVisited;
	protected String name;
	protected InfoService info;
	
	private static int countResolver = 0;
	
	public Resolver() {
		this.caseVisited = new ArrayList<Integer>();
		Resolver.countResolver++;

		this.name = "Resolver #" + Integer.toString(Resolver.countResolver);
	}

	protected boolean isVisitedCase(int caseId) {
		for (Integer id : caseVisited) {
			if (id == caseId) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void bindInfoService(InfoService info) {
		this.info = info;
	}
}

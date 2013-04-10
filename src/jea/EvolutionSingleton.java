package jea;

public class EvolutionSingleton {
	
	private GenePool genePool;
	
	private parentSelectionType parentSelType;
	private presumptionType presumptType;
	private int memberCount;
	
	private static EvolutionSingleton instance;
	
	private EvolutionSingleton() {
		parentSelType = parentSelType.rouletteSelection;
		presumptType = presumptType.ranking;
		memberCount = 5;
	}
	
	public static EvolutionSingleton getInstance() {
		if(instance == null)
			instance = new EvolutionSingleton();
		return instance;
	}
	
	public GenePool getGenePool() {
		return genePool;
	}
	
	public void setGenPool(GenePool genePool) {
		this.genePool = genePool;
	}
	
	public void calcFitness(Permutation permutation) {
		genePool.calcFitness(permutation);
	}
	
	public parentSelectionType getParentSelType() {
		return parentSelType;
	}
	
	public void setParentSelType(parentSelectionType parentSelType) {
		this.parentSelType = parentSelType;
	}
	
	public presumptionType getPresumptType() {
		return presumptType;
	}
	
	public void setPresumptType(presumptionType presumptType) {
		this.presumptType = presumptType;
	}
	
	public int getMemberCount() {
		return memberCount;
	}
	
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
}

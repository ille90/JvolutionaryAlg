package jea;

public class EvolutionSingleton {
	
	private GenePool genePool;
	
	private FitnessSelectionType fitnessSelType;
	
	private ParentSelectionType parentSelType;
	private PresumptionType presumptType;
	private int memberCount;
	
	private static EvolutionSingleton instance;
	
	private EvolutionSingleton() {
		fitnessSelType = FitnessSelectionType.Highest;
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
	
	public FitnessSelectionType getFitnessSelType() {
		return fitnessSelType;
	}
	
	public void setFitnessSelType(FitnessSelectionType fitnessSelType) {
		this.fitnessSelType = fitnessSelType;
	}
	
	public ParentSelectionType getParentSelType() {
		return parentSelType;
	}
	
	public void setParentSelType(ParentSelectionType parentSelType) {
		this.parentSelType = parentSelType;
	}
	
	public PresumptionType getPresumptType() {
		return presumptType;
	}
	
	public void setPresumptType(PresumptionType presumptType) {
		this.presumptType = presumptType;
	}
	
	public int getMemberCount() {
		return memberCount;
	}
	
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
}

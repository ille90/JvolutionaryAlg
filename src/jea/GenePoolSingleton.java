package jea;

public class GenePoolSingleton {
	
	private GenePool genePool;
	private static GenePoolSingleton instance;
	
	private GenePoolSingleton() {
		
	}
	
	public static GenePoolSingleton getInstance() {
		if(instance == null)
			instance = new GenePoolSingleton();
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

}

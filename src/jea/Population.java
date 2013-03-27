package jea;

public class Population<T extends GenPool> {
	
	int currentGeneration;
	int permutationCount;
	int maxGeneration;
	Generation<T> generation;
	int childrenCount;
	int limit;
	int geneCount;
	int[] genes;
	
	public Population(int permutationCount) {
		// TODO Auto-generated constructor stub
		this.permutationCount = permutationCount;
		generation = new Generation<T>(permutationCount);
	}
	
	void run() {
		currentGeneration = 0;
		generation.fillInitialGeneration();
		generation.calcFitness();
		while(currentGeneration < maxGeneration) {
			Generation<T> children = new Generation<T>(childrenCount);
			int currentChild = 1;
			while(currentChild <= childrenCount) {
				//TODO: Eltern wählen etc. (Schritt 7-10)
				childrenCount++;
			}
			children.calcFitness();
			currentGeneration++;
			generation = generation.getNextGeneration(children);
		}
	}
	
	public Permutation<T> getBestPermutation() {
		return generation.getBestPermutation();
	}
}

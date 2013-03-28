package jea;

public class Population {
	
	int currentGeneration;
	int permutationCount;
	int maxGeneration;
	Generation generation;
	int childrenCount;
	float limit;
	
	public Population(int permutationCount, int maxGeneration, int childrenCount, float limit) {
		// TODO Auto-generated constructor stub
		this.permutationCount = permutationCount;
		this.maxGeneration = maxGeneration;
		this.childrenCount = childrenCount;
		this.limit = limit;
	}
	
	public void init() {
		currentGeneration = 0;
		generation = new Generation(permutationCount);
		generation.fillInitialGeneration();
		generation.calcFitness();
	}
	
	public void run() {
		while(currentGeneration < maxGeneration) {
			System.out.print((currentGeneration + 1) + ". Generation: ");
			Generation children = new Generation(childrenCount);
			int currentChild = 1;
			while(currentChild <= childrenCount) {
				Permutation father = generation.getRandomPermutation();
				Permutation mother = generation.getRandomPermutation();
				while(father == mother)
					mother = generation.getRandomPermutation();
				//TODO: Zufallszahls u (Schritt 8)
				Permutation child = Evolution.recombination(father, mother);
				child = Evolution.mutation(child);
				children.addPermutation(child);
				currentChild++;
			}
			children.calcFitness();
			currentGeneration++;
			generation = generation.getNextGeneration(children);
			System.out.println("fetig");
		}
	}
	
	public Permutation getBestPermutation() {
		return generation.getBestPermutation();
	}
}

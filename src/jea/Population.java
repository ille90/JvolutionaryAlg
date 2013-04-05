package jea;

public class Population {

	int currentGeneration;
	int permutationCount;
	int maxGeneration;
	Generation generation;
	int childrenCount;
	float limit;

	public Population(int permutationCount, int maxGeneration,
			int childrenCount, float limit) {
		this.permutationCount = permutationCount;
		this.maxGeneration = maxGeneration;
		this.childrenCount = childrenCount;
		if (limit > 1)
			this.limit = 1;
		else if (limit < 0)
			this.limit = 0;
		else
			this.limit = limit;
	}

	public void init() {
		currentGeneration = 0;
		generation = new Generation(permutationCount);
		generation.fillInitialGeneration();
		generation.calcFitness();
	}

	public void run() {
		while (currentGeneration < maxGeneration) {
			System.out.print((currentGeneration + 1) + ". Generation: ");
			Generation children = new Generation(childrenCount);
			int currentChild = 1;
			while (currentChild <= childrenCount) {
				Permutation father = generation.getRandomPermutation();
				Permutation mother = generation.getRandomPermutation();
				while (father == mother)
					mother = generation.getRandomPermutation();
				double u = Math.random();
				if (u < limit) {
					Permutation child = Evolution.recombination(father, mother);
					child = Evolution.mutation(child);
					children.addPermutation(child);
				}
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

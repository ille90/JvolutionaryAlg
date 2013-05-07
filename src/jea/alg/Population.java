package jea.alg;

import jea.alg.selection.DetermSelectionType;
import jea.alg.selection.EnvironmentalSelection;
import jea.alg.selection.ParentSelection;

public class Population {

	int currentGeneration;
	int permutationCount;
	int maxGeneration;
	Generation generation;
	int childrenCount;

	public Population(int permutationCount, int maxGeneration,
			int childrenCount) {
		this.permutationCount = permutationCount;
		this.maxGeneration = maxGeneration;
		this.childrenCount = childrenCount;
	}

	public void init() {
		currentGeneration = 0;
		generation = new Generation(permutationCount);
		generation.fillInitialGeneration();
		generation.calcFitness();
	}

	public void run(DetermSelectionType type) {
		while (currentGeneration < maxGeneration) {

			System.out.print((currentGeneration + 1) + ". Generation: ");
			Generation children = new Generation(childrenCount);
			Thread[] threads = new Thread[EvolutionSingleton.getInstance()
					.getMaxThreads()];

			while (children.getPermutationCount() < childrenCount) {
				for (int i = 0; i < threads.length; i++) {
					if (threads[i] != null && !threads[i].isAlive())
						threads[i] = null;
					if (threads[i] == null) {
						threads[i] = new Thread(new ChildrenCreator(children));
						threads[i].start();
						break;
					}
				}
			}

			for (Thread thread : threads) {
				if (thread != null)
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

			currentGeneration++;

			if (type == DetermSelectionType.commaSelection)
				generation.setPermutations(children.getPermutations());
			else
				generation.addGeneration(children);
			EnvironmentalSelection.determSelection(generation);

			System.out.println("fertig");
			// generation.printGeneration();
		}
	}

	public Permutation getBestPermutation() {
		return generation.getBestPermutation();
	}

	private class ChildrenCreator implements Runnable {

		Generation children;

		public ChildrenCreator(Generation children) {
			this.children = children;
		}

		@Override
		public void run() {

			Permutation father = ParentSelection.useParentSelection(generation);
			Permutation mother = ParentSelection.useParentSelection(generation);

			while (father == mother)
				mother = generation.getRandomPermutation();
			Permutation[] tmpChildren = EvolutionSingleton.getInstance()
					.getCoding().recombination(father, mother);
			for (Permutation child : tmpChildren) {
				EvolutionSingleton.getInstance().getCoding().mutation(child);
				child.calcFitness();
				children.addPermutation(child);
			}
		}
	}
}

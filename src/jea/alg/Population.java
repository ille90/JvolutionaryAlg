package jea.alg;

import java.util.Random;

import jea.alg.selection.DetermSelectionType;
import jea.alg.selection.EnvironmentalSelection;
import jea.alg.selection.ParentSelection;

public class Population {

	int currentGeneration;
	Generation generation;
	ParentSelection parentSelection;
	Model model;

	public Population() {
		model = EvolutionSingleton.getInstance().getModel();
	}

	public void init() {
		currentGeneration = 0;
		generation = new Generation(model.permutationCount);
		generation.fillInitialGeneration();
		generation.calcPermutationFitness();
		parentSelection = new ParentSelection();
	}

	public void run() {//DetermSelectionType type) {
		while (currentGeneration < model.maxGeneration) {

			System.out.print((currentGeneration + 1) + ". Generation: ");

			parentSelection.load(generation);
			Generation children = new Generation(model.childrenCount);
			Thread[] threads = new Thread[EvolutionSingleton.getInstance()
					.getMaxThreads()];

			while (children.getPermutationCount() < model.childrenCount) {
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

			if (model.determSelType == DetermSelectionType.commaSelection)
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

			Permutation father = parentSelection.useParentSelection();
			Permutation mother = parentSelection.useParentSelection();

			while (father == mother)
				mother = generation.getRandomPermutation();
			Permutation[] tmpChildren = EvolutionSingleton.getInstance()
					.getCoding().recombination(father, mother);
			for (Permutation child : tmpChildren) {
				if(new Random().nextBoolean())
					EvolutionSingleton.getInstance().getCoding().mutation(child);
				child.calcFitness();
				children.addPermutation(child);
			}
		}
	}
}

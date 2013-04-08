package jea;

import java.util.Vector;

public class environmentalSelection {
	
	public static Generation determSelection(Generation generation){
		Generation newGeneration = new Generation(generation.getPermutationCount());
		Vector<Permutation> newPermutations = new Vector<Permutation>();
		newPermutations.addAll(generation.getPermutations());
		
		while (generation.getPermutationCount() < newPermutations.size()) {
			int worstFitness = Integer.MAX_VALUE;
			int worstPermutation = 0;
			
			for(int i = 0; i < newPermutations.size(); i++) {
				
				if(worstFitness > newPermutations.get(i).getFitness()) {
					worstFitness = newPermutations.get(i).getFitness();
					worstPermutation = i;
				}
			}
			
			newPermutations.remove(worstPermutation);
		}
		
		newGeneration.setPermutations(newPermutations);
		return newGeneration;
	}
	
	public static Generation probSelection(Generation generation){
		
		return generation;
	}

	public static Generation rouletteSelection(Generation generation){
	
		return generation;
	}
	
	public static Generation qSelection(Generation generation){
		
		return generation;
	}
	
	public static Generation multibleQSelection(Generation generation){
		
		return generation;
	}

}

package reiseproblem;

import java.util.HashMap;

import jea.GenePool;
import jea.Permutation;

public class Staedte implements GenePool{
	
	HashMap<Integer, int[]> costs;
	
	public Staedte(HashMap<Integer, int[]> costs) {
		this.costs = costs;
	}
	
	@Override
	public int geneCount() {
		return costs.size();
	}
	
	private int calcKosten(Permutation permutation) {
		int costsCount = 0;
		for (int i = 0; i < geneCount(); i++) {
			costsCount += costs.get(permutation.getGene(i))[permutation.getGene((i + 1) == geneCount() ? 0 : (i + 1))];
		}
		return costsCount;
	}

	@Override
	public void calcFitness(Permutation permutation) {
		permutation.setFitness(calcKosten(permutation) * -1);
	}
	
	public void printPermutationInfo(Permutation permutation) {
		String staedtefolge = "";
		for(int i = 0; i < geneCount(); i++) {
			staedtefolge += permutation.getGene(i) + " ,";
		}
		staedtefolge += permutation.getGene(0);
		
		System.out.println("Städtefolge: " + staedtefolge);
		System.out.println("Kosten: " + calcKosten(permutation));
	}
}

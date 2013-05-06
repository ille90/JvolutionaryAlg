package jea.alg;

import jea.alg.coding.CodingType;
import jea.alg.coding.binary.BitChain;
import jea.alg.coding.real.RealGene;

public class Permutation {
	
	private Gene[] genes;
	private double fitness;
	
	public Permutation() {
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		genes = new Gene[geneCount];
//		for (int i = 0; i < genes.length; i++) {
//			genes[i] = -1;
//		}
	}
	
	public Permutation(Permutation permutation) {
		genes = new Gene[permutation.genes.length];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = permutation.genes[i].getCopy();
		}
	}
	
	/**
	 * getRandomPermutation gibt ein Individuum mit zufälliger Genkombination zurück
	 * 
	 * @param geneCount
	 * @return
	 */
	public static Permutation getRandomPermutation() {
		Permutation permutation = new Permutation();
		int i = 0;
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		while(i < geneCount) {
			if(EvolutionSingleton.getInstance().getCodingType() == CodingType.binary)
				permutation.genes[i] = new BitChain().getRandomGene();
			else
				permutation.genes[i] = new RealGene().getRandomGene();
			i++;
		}
		return permutation;
	}
	

	public Gene getGene(int pos) {
		if(pos > -1 && pos < genes.length)
			return genes[pos];
		return null;
	}
	
	public void setGene(int pos, Gene gene) {
		if(pos > -1 && pos < genes.length)
			genes[pos] = gene;
	}
	
//	public int getGenePosition(int gene) {
//		for(int i = 0; i < genes.length; i++) {
//			if(genes[i] == gene)
//				return i;
//		}
//		return -1;
//	}
	
	public void calcFitness() {
		EvolutionSingleton.getInstance().calcFitness(this);
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("Genes:\n");
		for (Gene gene : genes) {
			strBld.append(gene);
			strBld.append("\n");
		}
		strBld.append("Fitness: ");
		strBld.append(fitness);
		strBld.append("\n");
		return strBld.toString();
	}
}
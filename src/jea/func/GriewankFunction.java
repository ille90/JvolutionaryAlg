package jea.func;

import jea.alg.EvolutionSingleton;
import jea.alg.FitnessFunction;
import jea.alg.Permutation;

public class GriewankFunction implements FitnessFunction{

	public double lowestValue = -512;
	public double heighestValue = 511;
	EvolutionSingleton es;
	
	public GriewankFunction() {
		es = EvolutionSingleton.getInstance();
	}
	
	@Override
	public Double getLowestValue() {
		return lowestValue;
	}

	@Override
	public Double getHeighestValue() {
		return heighestValue;
	}

	@Override
	public void calcFitness(Permutation permutation) {
		int n = es.getGeneCount();
		double fitness = 1;
		double sum = 0;
		double product = 1;
		for(int i = 0; i < n; i++) {
			double geneValue =permutation.getGene(i).getValue();
			sum += Math.pow(geneValue, 2);
			product *= Math.cos(geneValue / Math.sqrt(i + 1));
		}
		
		fitness += (sum / (400 * n));
		fitness -= product;
		permutation.setFitness(fitness);
	}

	@Override
	public void printPermutationInfo(Permutation permutation) {
		String gene = "";
		for(int i = 0; i < es.getGeneCount(); i++) {
			gene += permutation.getGene(i).getValue() + ", ";
		}
		
		System.out.println("Gene: " + gene);
		calcFitness(permutation);
		System.out.println("Fitness: " + permutation.getFitness());
	}
	
	@Override
	public String getName() {
		return "Griewank";
	}
}

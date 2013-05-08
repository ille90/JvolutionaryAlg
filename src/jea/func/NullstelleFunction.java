package jea.func;

import jea.alg.EvolutionSingleton;
import jea.alg.FitnessFunction;
import jea.alg.Permutation;

public class NullstelleFunction implements FitnessFunction{

	EvolutionSingleton es;
	
	public NullstelleFunction() {
		es = EvolutionSingleton.getInstance();
	}
	
	@Override
	public Double getLowestValue() {
		return null;
	}

	@Override
	public Double getHeighestValue() {
		return null;
	}
	
	@Override
	public void calcFitness(Permutation permutation) {
		double[] coords = new double[es.getGeneCount()];
		double sum = 0;
		double product = 1;
		for (int i = 0; i < coords.length; i++) {
			sum += permutation.getGene(i).getValue();
			product *= permutation.getGene(i).getValue();
		}
		sum -= (es.getGeneCount() + 1);
		
		for (int i = 0; i < coords.length - 1; i++) {
			coords[i] = permutation.getGene(i).getValue() + sum;
		}
		coords[es.getGeneCount() - 1] = (product - 1);
		
		double rtfitness = 0;
		for (int i = 0; i < coords.length; i++) {
			rtfitness += Math.pow(coords[i], 2);
		}
		permutation.setFitness(Math.sqrt(rtfitness));
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
}

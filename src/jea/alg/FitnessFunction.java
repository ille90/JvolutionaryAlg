package jea.alg;

public interface FitnessFunction {

	public Double getLowestValue();
	public Double getHeighestValue();
	public void calcFitness(Permutation permutation);
	public void printPermutationInfo(Permutation permutation);
}

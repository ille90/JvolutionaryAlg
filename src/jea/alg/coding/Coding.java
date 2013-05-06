package jea.alg.coding;

import jea.alg.Permutation;

public interface Coding {
	public Permutation[] recombination(Permutation father, Permutation mother);
	public void mutation(Permutation permutation);
}

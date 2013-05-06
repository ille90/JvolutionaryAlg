package jea.alg.coding.real;

import jea.alg.EvolutionSingleton;
import jea.alg.Permutation;
import jea.alg.coding.Coding;

public class RealCoding implements Coding {
	
	@Override
	public Permutation[] recombination(Permutation father, Permutation mother) {
		RealRecombinationType rrt = EvolutionSingleton.getInstance().getRealRecombType();
		Permutation[] children = new Permutation[1];
		if(rrt == RealRecombinationType.intermediat)
			children[0] = intermediatRecombination(father, mother);
		else if(rrt == RealRecombinationType.arithmetic)
			children[0] = arithmeticRecombination(father, mother);
		else {
			if(Math.random() > 0.5)
				children[0] = arithmeticRecombination(father, mother);
			else
				children[0] = intermediatRecombination(father, mother);
		}
		return children;
	}

	Permutation intermediatRecombination(Permutation father, Permutation mother) {
		Permutation child = new Permutation();
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		for (int i = 0; i < geneCount; i++) {
			child.setGene(i, new RealGene((father.getGene(i).getValue() + mother.getGene(i).getValue()) / 2));
		}
		return child;
	}
	
	Permutation arithmeticRecombination(Permutation father, Permutation mother) {
		Permutation child = new Permutation();
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		double t = Math.random();
		for (int i = 0; i < geneCount; i++) {
			double a = father.getGene(i).getValue();
			double b = mother.getGene(i).getValue();
			if(b < a) {
				double h = a;
				a = b;
				b = h;
			}
			child.setGene(i, new RealGene( a + t * (b - a)));
		}
		return child;
	}
	
	//TODO: Mutation
	@Override
	public void mutation(Permutation permutation) {
		// TODO Auto-generated method stub
		
	}
}

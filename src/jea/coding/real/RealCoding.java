package jea.coding.real;

import jea.EvolutionSingleton;
import jea.coding.Coding;
import jea.neu.Individuum;

public class RealCoding implements Coding {

	Individuum intermediatRecombination(Individuum father, Individuum mother) {
		Individuum child = new Individuum();
		int geneCount = EvolutionSingleton.getInstance().getGenePool()
				.geneCount();
		for (int i = 0; i < geneCount; i++) {
			child.setGene(i, new RealGene((father.getGene(i).getValue() + mother.getGene(i).getValue()) / 2));
		}
		return child;
	}
	
	Individuum arithmeticRecombination(Individuum father, Individuum mother) {
		Individuum child = new Individuum();
		int geneCount = EvolutionSingleton.getInstance().getGenePool()
				.geneCount();
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
}

package jea.alg.coding.binary;

import java.util.Random;

import jea.alg.EvolutionSingleton;
import jea.alg.Gene;
import jea.alg.Permutation;
import jea.alg.coding.Coding;

public class BinaryCoding implements Coding {
	
	@Override
	public Permutation[] recombination(Permutation father, Permutation mother) {
		BinaryRecombinationType brt = EvolutionSingleton.getInstance().getBinaryRecombType();
		if(brt == BinaryRecombinationType.onepoint)
			return onePointRecombination(father, mother);
		else if(brt == BinaryRecombinationType.twopoint)
			return twoPointRecombination(father, mother);
		else if(brt == BinaryRecombinationType.template)
			return templateRecombination(father, mother);
		else {
			int type = (int)(new Random().nextDouble() * 2);
			if(type == 0)
				return onePointRecombination(father, mother);
			else if(type == 1)
				return twoPointRecombination(father, mother);
			else
				return templateRecombination(father, mother);
		}
	}

	Permutation[] onePointRecombination(Permutation father, Permutation mother) {
		Permutation[] children = new Permutation[2];
		children[0] = new Permutation(father);
		children[1] = new Permutation(mother);
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		int z = (int) (new Random().nextDouble() * (geneCount - 1));
		while (z < geneCount) {
			Gene fgene = children[0].getGene(z).getCopy();
			Gene mgene = children[1].getGene(z).getCopy();
			children[0].setGene(z, mgene);
			children[1].setGene(z, fgene);
			z++;
		}
		return children;
	}

	Permutation[] twoPointRecombination(Permutation father, Permutation mother) {
		Permutation[] children = new Permutation[2];
		children[0] = new Permutation(father);
		children[1] = new Permutation(mother);
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		int z1 = (int) (new Random().nextDouble() * (geneCount - 1));
		int z2 = (int) (new Random().nextDouble() * (geneCount - 1));
		if (z2 < z1) {
			int h = z1;
			z1 = z2;
			z2 = h;
		}
		while (z1 <= z2) {
			Gene fgene = children[0].getGene(z1).getCopy();
			Gene mgene = children[1].getGene(z1).getCopy();
			children[0].setGene(z1, mgene);
			children[1].setGene(z1, fgene);
			z1++;
		}
		return children;
	}

	Permutation[] templateRecombination(Permutation father, Permutation mother) {
		Permutation[] children = new Permutation[2];
		children[0] = new Permutation(father);
		children[1] = new Permutation(mother);
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		boolean template[] = new boolean[geneCount];
		for (int i = 0; i < template.length; i++) {
			template[i] = (new Random().nextDouble() > 0.5) ? true : false;
		}
		int z = 0;
		while (z < geneCount) {
			if (template[z]) {
				Gene fgene = children[0].getGene(z).getCopy();
				Gene mgene = children[1].getGene(z).getCopy();
				children[0].setGene(z, mgene);
				children[1].setGene(z, fgene);
			}
			z++;
		}
		return children;
	}

	@Override
	public void mutation(Permutation individuum) {
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		int z = (int) (new Random().nextDouble() * (geneCount - 1));
		((BitChain) individuum.getGene(z)).invert();
	}
}

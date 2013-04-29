package jea.coding.binary;

import jea.EvolutionSingleton;
import jea.coding.Coding;
import jea.neu.Gene;
import jea.neu.Individuum;

public class BinaryCoding implements Coding {

	Individuum[] onePointRecombination(Individuum father, Individuum mother) {
		Individuum[] children = new Individuum[2];
		children[0] = new Individuum(father);
		children[1] = new Individuum(mother);
		int geneCount = EvolutionSingleton.getInstance().getGenePool()
				.geneCount();
		int z = (int) (Math.random() * (geneCount - 1));
		while (z < geneCount) {
			Gene fgene = children[0].getGene(z);
			Gene mgene = children[1].getGene(z);
			children[0].setGene(z, mgene);
			children[1].setGene(z, fgene);
			z++;
		}
		return children;
	}

	Individuum[] twoPointRecombination(Individuum father, Individuum mother) {
		Individuum[] children = new Individuum[2];
		children[0] = new Individuum(father);
		children[1] = new Individuum(mother);
		int geneCount = EvolutionSingleton.getInstance().getGenePool()
				.geneCount();
		int z1 = (int) (Math.random() * (geneCount - 1));
		int z2 = (int) (Math.random() * (geneCount - 1));
		if (z2 < z1) {
			int h = z1;
			z1 = z2;
			z2 = h;
		}
		while (z1 <= z2) {
			Gene fgene = children[0].getGene(z1);
			Gene mgene = children[1].getGene(z1);
			children[0].setGene(z1, mgene);
			children[1].setGene(z1, fgene);
			z1++;
		}
		return children;
	}

	Individuum[] templateRecombination(Individuum father, Individuum mother) {
		Individuum[] children = new Individuum[2];
		children[0] = new Individuum(father);
		children[1] = new Individuum(mother);
		int geneCount = EvolutionSingleton.getInstance().getGenePool()
				.geneCount();
		boolean template[] = new boolean[geneCount];
		for (int i = 0; i < template.length; i++) {
			template[i] = (Math.random() > 0.5) ? true : false;
		}
		int z = 0;
		while (z < geneCount) {
			if (template[z]) {
				Gene fgene = children[0].getGene(z);
				Gene mgene = children[1].getGene(z);
				children[0].setGene(z, mgene);
				children[1].setGene(z, fgene);
			}
			z++;
		}
		return children;
	}
	
	void mutation(Individuum individuum) {
		int geneCount = EvolutionSingleton.getInstance().getGenePool()
				.geneCount();
		int z = (int) (Math.random() * (geneCount - 1));
		((BitChain) individuum.getGene(z)).invert();
	}
}

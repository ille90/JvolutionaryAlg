package jea;

import java.util.HashMap;
import java.util.Vector;

/**
 * enthält die Rekombination und Mutations-Funktionen
 *
 */
public class Evolution {

	/**
	 * mutation gibt ein neues Individuum zurück
	 * welches einer Mutation des übergebenen Individuums enspricht
	 * die Veränderung wird entweder durch eine Vertauschung
	 * oder eine Inversion der Gene verursacht
	 * es kann auch zufälligerweise keine Veränderung stattfinden (TODO: Beibehalten oder Veränderung erzwingen?)
	 * 
	 * @param permutation
	 * @return
	 */
	public static Permutation mutation(Permutation permutation) {
		int geneCount = GenePoolSingleton.getInstance().getGenePool().geneCount();
		//Art der Mutation festlegen: 0 = Vertauschung, 1 = Inversion, 2 = keine Veränderung
		int typ = (int) (Math.random() * 3);
		Permutation newPermutation = new Permutation(permutation);
		if (2 != typ) {
			//Zwei zufällige ungleiche Genpositionen auswählen
			int geneOne = (int) (Math.random() * geneCount);
			int geneTwo = (int) (Math.random() * geneCount);
			while (geneOne == geneTwo) {
				geneTwo = (int) (Math.random() * geneCount);
			}
			if (0 == typ) {
				//Vertauschung
				newPermutation.setGene(geneOne, permutation.getGene(geneTwo));
				newPermutation.setGene(geneTwo, permutation.getGene(geneOne));
			} else {
				//Inversion
				if(geneOne > geneTwo) {
					//sortieren
					int i = geneTwo;
					geneTwo = geneOne;
					geneOne = i;
				}
				
				int j = ((geneTwo - geneOne + 1) / 2) - 1;
				for(int i = 0; i <= j; i++) {
					newPermutation.setGene(geneOne + i, permutation.getGene(geneTwo - i));
					newPermutation.setGene(geneTwo - i, permutation.getGene(geneOne + i));
				}
			}
		}
		return newPermutation;
	}
	
	/**
	 * recombination erstellt aus zwei Eltern-Individuen ein neues Kind-Inviduum
	 * siehe EvoAlg.pdf Seite 18ff
	 * 
	 * @param father
	 * @param mother
	 * @return
	 */
	public static Permutation recombination(Permutation father, Permutation mother) {
		int geneCount = GenePoolSingleton.getInstance().getGenePool().geneCount();
		int k = 0;
		int n = geneCount;
		HashMap<Integer, Vector<Integer>> adjs = new HashMap<Integer, Vector<Integer>>();
		//Benachbarte Gene aus Eltern bestimmen
		while(k < n) {
			int i = father.getGenePosition(k);
			int j = mother.getGenePosition(k);
			Vector<Integer> adj = new Vector<Integer>();
			adj.add(father.getGene((i + n - 1) % n));
			adj.add(father.getGene((i + 1) % n));
			adj.add(mother.getGene((j + n - 1) % n));
			adj.add(mother.getGene((j + 1) % n));
			adjs.put(k, adj);
			k++;
		}
		Permutation child = new Permutation(geneCount);
		//Entscheiden ob erstes Gen aus Mutter oder Vater
		if(0 == (int) (Math.random() * 2))
			child.setGene(0, father.getGene(0));
		else
			child.setGene(0, mother.getGene(0));
		
		for(int i = 1; i < n; i++) {
			//Geringste Nachbaranzahl der aktuellen Nachbargene bestimmen
			int m = Integer.MAX_VALUE;
			Vector<Integer> genes = getUnusedGenes(adjs.get(child.getGene(0)), child);
			for (Integer gene : genes) {
				int length = getUnusedGenes(adjs.get(gene), child).size();
				if(length < m)
					m = length;
			}
			Vector<Integer> K = new Vector<Integer>();
			//alle Nachbarn mit vorher festgelegten Nachbaranzahl in enger Wahl nehmen
			if(0 < m) {
				for (Integer gene : genes) {
					if(m == getUnusedGenes(adjs.get(gene), child).size())
						K.add(gene);
				}
			}
			//Falls keine Nachbarn gefunden alle noch freien Gene in engere Auswahl
			if(0 == K.size())
				K = getUnusedGenes(child);
			//Zufälliges Gen aus engere Auswahl wählen
			child.setGene(i, K.get((int) (Math.random() * K.size())));
		}
		
		return child;
	}
	
	/**
	 * getUnusedGenes gibt alle Gene aus adj zurück
	 * die im unfertigen Individuum permutation noch nicht vorhanden sind
	 * 
	 * @param adj
	 * @param permutation
	 * @return
	 */
	private static Vector<Integer> getUnusedGenes(Vector<Integer> adj, Permutation permutation) {
		Vector<Integer> genes = new Vector<Integer>();
		for (Integer gene : adj) {
			if(-1 == permutation.getGenePosition(gene))
				genes.add(gene);
		}
		return genes;
	}
	
	/**
	 * getUnusedGenes gibt alle Gene zurück
	 * die im unfertigen Individuum permutation noch nicht vorhanden sind
	 * 
	 * @param permutation
	 * @return
	 */
	private static Vector<Integer> getUnusedGenes(Permutation permutation) {
		Vector<Integer> genes = new Vector<Integer>();
		for (int i = 0; i < permutation.getGeneCount(); i++) {
			genes.add(i);
		}
		return getUnusedGenes(genes, permutation);
	}
}

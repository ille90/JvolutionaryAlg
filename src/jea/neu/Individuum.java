package jea.neu;

public class Individuum {
	
	Gene[] genes;
	Double fitness;
	
	public Individuum() {
		// TODO Auto-generated constructor stub
	}
	
	public Individuum(Individuum individuum) {
		genes = new Gene[individuum.genes.length];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = individuum.genes[i].getCopy();
		}
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
}

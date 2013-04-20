package reiseproblem;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jea.GenePool;
import jea.Permutation;
import jea.Population;
import jea.XYSeriesChart;

public class Staedte implements GenePool{
	
	HashMap<Integer, int[]> costs;
	
	public Staedte(HashMap<Integer, int[]> costs) {
		this.costs = costs;
	}
	
	@Override
	public int geneCount() {
		return costs.size();
	}
	
	private Double calcKosten(Permutation permutation) {
		Double costsCount = 0.0;
		for (int i = 0; i < geneCount(); i++) {
			costsCount += costs.get(permutation.getGene(i))[permutation.getGene((i + 1) == geneCount() ? 0 : (i + 1))];
		}
		return costsCount;
	}

	@Override
	public void calcFitness(Permutation permutation) {
		permutation.setFitness(calcKosten(permutation));
	}
	
	public void printPermutationInfo(Permutation permutation) {
		String staedtefolge = "";
		for(int i = 0; i < geneCount(); i++) {
			staedtefolge += permutation.getGene(i) + ", ";
		}
		staedtefolge += permutation.getGene(0);
		
		System.out.println("Städtefolge: " + staedtefolge);
		System.out.println("Kosten: " + calcKosten(permutation));
	}

	public void printBenchmark(Population population) {
		
		XYSeriesChart benchmarkChart = new XYSeriesChart("Dia");	
		
		JPanel panel = benchmarkChart.getPanel(population.getBenchmarkData());
		
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
	}
}

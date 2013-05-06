package jea.alg.coding.real;

import jea.alg.EvolutionSingleton;
import jea.alg.Gene;

public class RealGene implements Gene {

	double value;
	
	public RealGene() {
		value = 0.0d;
	}
	
	public RealGene(double value) {
		this.value = value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public Gene getCopy() {
		return new RealGene(value);
	}
	
	@Override
	public Gene getRandomGene() {
		EvolutionSingleton es = EvolutionSingleton.getInstance();
		return new RealGene((Math.random() * (es.getHeighestValue() - es.getLowestValue())) + es.getLowestValue());
	}
	
	@Override
	public String toString() {
		return Double.toString(value);
	}
}

package jea.coding.real;

import jea.neu.Gene;

public class RealGene implements Gene {

	Double value;
	
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
	public Double getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gene getCopy() {
		// TODO Auto-generated method stub
		return null;
	}
}

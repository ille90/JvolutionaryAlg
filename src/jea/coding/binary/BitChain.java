package jea.coding.binary;

import jea.EvolutionSingleton;
import jea.neu.Gene;

public class BitChain implements Gene {
	
	Bit[] bits;
	Double value;
	static EvolutionSingleton es;
	
	public BitChain() {
		es = EvolutionSingleton.getInstance();
		bits = new Bit[es.getChainLength()];
		value = null;
	}
	
	@Override
	public Double getValue() {
		if(value == null) {
			EvolutionSingleton es = EvolutionSingleton.getInstance();
			double g = es.getLowestValue() + ((es.getHeighestValue() - es.getLowestValue()) / (Math.pow(2, es.getChainLength()) - 1));
			int a = 0;
			for (int j = 0; j < bits.length; j++) {
				a += bits[bits.length - (j+1)].getValue() * Math.pow(2, j);
			}
			value = g * a;
		}
		return value;
	}
	
	@Override
	public Gene getCopy() {
		BitChain bc = new BitChain();
		for (int i = 0; i < bc.bits.length; i++) {
			bc.bits[i] = new Bit();
			if(bits[i].isOne)
				bc.bits[i].setOne();
		}
		return bc;
	}
	
	public void invert() {
		for (Bit bit : bits) {
			if(bit.isOne)
				bit.setZero();
			else
				bit.setOne();
		}
	}
}

package jea.alg.coding.binary;

import java.util.Random;

import jea.alg.EvolutionSingleton;
import jea.alg.Gene;

public class BitChain implements Gene {
	
	Bit[] bits;
	boolean valued;
	double value;
	boolean gray;
	static EvolutionSingleton es;
	
	public BitChain() {
		es = EvolutionSingleton.getInstance();
		bits = new Bit[es.getChainLength()];
		for(int i = 0; i < bits.length; i++)
			bits[i] = new Bit();
		valued = false;
		gray = false;
	}
	
	public BitChain(int binaryValue) {
		this();
		for(int i = (bits.length - 1); i >= 0; i--) {
			int v = (int) (binaryValue / Math.pow(2, i));
			if(1 <= v) {
				bits[i].setOne();
				binaryValue -= (v * Math.pow(2, i));
			} else 
				bits[i].setZero();
		}
		calcValue();
		if(es.useGraycode())
			setToGrayCode();
	}
	
	private void calcValue() {
		Bit[] tmpBits = getStandartBinary();
		int a = 0;
		for (int j = 0; j < tmpBits.length; j++) {
			a += tmpBits[tmpBits.length - (j+1)].getValue() * Math.pow(2, j);
		}
		value = es.getLowestValue() + es.getGranularity() * a;
		valued = true;
	}
	
	@Override
	public double getValue() {
		if(!valued) {
			calcValue();
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
		bc.gray = this.gray;
		bc.value = this.value;
		return bc;
	}
	
	public void invert() {
		for (Bit bit : bits) {
			if(bit.isOne)
				bit.setZero();
			else
				bit.setOne();
		}
		calcValue();
	}
	
	private Bit[] getStandartBinary() {
		if(!gray)
			return bits;
		Bit[] stdbits = new Bit[bits.length];
		stdbin:
		for(int i = 0; i < stdbits.length; i++) {
			boolean one = bits[0].isOne;
			for (int j = 1; j < i; j++) {
				if(one != bits[j].isOne) {
					stdbits[i] = new Bit(true);
					continue stdbin;
				}
			}
			stdbits[i] = new Bit(false);
		}
		return stdbits;
	}
	
	public void setToStdCode() {
		if(!gray)
			return;
		this.bits = getStandartBinary();
	}
	
	public void setToGrayCode() {
		if(gray)
			return;
		Bit[] graybits = new Bit[bits.length];
		graybits[0] = new Bit(bits[0].isOne);
		for (int i = 1; i < graybits.length; i++) {
			if((bits[i].isOne && bits[i - 1].isOne) || (!bits[i].isOne && !bits[i - 1].isOne))
				graybits[i] = new Bit(false);
			else
				graybits[i] = new Bit(true);
		}
		bits = graybits;
		gray = true;
	}
	
	@Override
	public Gene getRandomGene() {
		return new BitChain((int) (new Random().nextDouble() * (es.getChainLength() - 1)));
	}
	
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("[");
		for (int i = 0; i < bits.length; i++) {
			strBld.append(bits[i].getValue());
			if(i < (bits.length - 1))
				strBld.append(",");
		}
		strBld.append("] : ");
		strBld.append(value);
		return strBld.toString();
	}
}

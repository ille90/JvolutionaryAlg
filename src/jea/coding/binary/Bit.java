package jea.coding.binary;

public class Bit {
	public static int ZERO = 0;
	public static int ONE = 1;
	
	boolean isOne;
	
	public Bit() {
		isOne = false;
	}
	
	public void setZero() {
		isOne = false;
	}
	
	public void setOne() {
		isOne = true;
	}
	
	public int getValue() {
		return (isOne == false) ? ZERO : ONE; 
	}
}

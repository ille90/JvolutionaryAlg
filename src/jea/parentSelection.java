package jea;

import java.util.Vector;

public class parentSelection {
	
	public static Generation probSelection(Generation generation){
		
		return generation;
	}

	//rangabsierte, für das Minimierungsprinzip
	public static int rouletteSelection(double[] ps){
		
		double z =  Math.random();
		
		double currentValue = 0;
		
		for(int i = 0; i < ps.length; i++) {
			currentValue += ps[i];
			if(currentValue > z)
				return i;
		}
		
		return ps.length - 1;
	}
	
	public static Generation qSelection(Generation generation){
		
		return generation;
	}
	
	public static Generation multibleQSelection(Generation generation){
		
		return generation;
	}

}

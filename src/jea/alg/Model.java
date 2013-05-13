package jea.alg;

import jea.alg.coding.Coding;
import jea.alg.coding.CodingType;
import jea.alg.coding.binary.BinaryCoding;
import jea.alg.coding.binary.BinaryRecombinationType;
import jea.alg.coding.real.RealCoding;
import jea.alg.coding.real.RealRecombinationType;
import jea.alg.selection.DetermSelectionType;
import jea.alg.selection.FitnessSelectionType;
import jea.alg.selection.ParentSelectionType;
import jea.alg.selection.PresumptionType;

public class Model {
	private FitnessFunction fitnessFunc;

	public int maxGeneration;
	public int permutationCount;
	public int childrenCount;

	private CodingType codingType;
	private Coding coding;

	//binary settings
	public boolean useGrayCode;
	private int chainLength;

	//recombination
	public BinaryRecombinationType binaryRecombType;
	public RealRecombinationType realRecombType;
	
	public int geneCount;
	private double lowestValue;
	private double heighestValue;

	public ParentSelectionType parentSelType;
	public PresumptionType presumptType;
	public int memberCount;
	
	public double mutation;

	public FitnessSelectionType fitnessSelType;
	
	public DetermSelectionType determSelType;
	
	private double granularity;
	
	public Model(FitnessFunction fitnessFunc) {
		this.fitnessFunc = fitnessFunc;
		
		//Default Values
		maxGeneration = 20;
		permutationCount = 50;
		childrenCount = 100;
		
		codingType = CodingType.binary;
		useGrayCode = false;
		chainLength = 10;
		
		binaryRecombType = BinaryRecombinationType.random;
		realRecombType = RealRecombinationType.random;
		
		geneCount = 5;
		if(fitnessFunc.getLowestValue() == null)
			lowestValue = -512;
		else
			lowestValue = fitnessFunc.getLowestValue();
		if(fitnessFunc.getHeighestValue() == null)
			heighestValue = 511;
		else
			heighestValue = fitnessFunc.getHeighestValue();
		
		parentSelType = ParentSelectionType.rouletteSelection;
		presumptType = PresumptionType.fitness;
		memberCount = 5;
		
		mutation = 0.5;
		
		fitnessSelType = FitnessSelectionType.Lowest;
		
		determSelType = DetermSelectionType.plusSelection;
		
		calcGranularity();
	}
	
	public double getHeighestValue() {
		return heighestValue;
	}
	
	public void setHeighestValue(double heighestValue) {
		this.heighestValue = heighestValue;
		calcGranularity();
	}
	
	public double getLowestValue() {
		return lowestValue;
	}
	
	public void setLowestValue(double lowestValue) {
		this.lowestValue = lowestValue;
		calcGranularity();
	}
	
	public int getChainLength() {
		return chainLength;
	}
	
	public void setChainLength(int chainLength) {
		this.chainLength = chainLength;
		calcGranularity();
	}
	
	public void calcGranularity() {
		granularity = (heighestValue - lowestValue) / (Math.pow(2, chainLength) - 1);
	}
	
	public double getGranularity() {
		return granularity;
	}
	
	public FitnessFunction getFitnessFunction() {
		return fitnessFunc;
	}
	
	public CodingType getCodingType() {
		return codingType;
	}
	
	public void setCodingType(CodingType codingType) {
		this.codingType = codingType;
		if(codingType == CodingType.binary)
			coding = new BinaryCoding();
		else
			coding = new RealCoding();
	}
	
	public Coding getCoding() {
		return coding;
	}
}

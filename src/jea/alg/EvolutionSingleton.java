package jea.alg;

import jea.alg.coding.Coding;
import jea.alg.coding.CodingType;
import jea.alg.coding.binary.BinaryCoding;
import jea.alg.coding.binary.BinaryRecombinationType;
import jea.alg.coding.real.RealCoding;
import jea.alg.coding.real.RealRecombinationType;
import jea.alg.selection.FitnessSelectionType;
import jea.alg.selection.ParentSelectionType;
import jea.alg.selection.PresumptionType;

public class EvolutionSingleton {
	
	private FitnessFunction fitnessFunc;
	
	private FitnessSelectionType fitnessSelType;
	
	private ParentSelectionType parentSelType;
	private PresumptionType presumptType;
	private int memberCount;
	private int geneCount;
	
	private double lowestValue;
	private double heighestValue;
	private int chainLength;
	private double granularity;
	
	private CodingType codingType;
	private Coding coding;
	
	private boolean useGrayCode;
	
	private BinaryRecombinationType binaryRecombType;
	private RealRecombinationType realRecombType;
	
	private int maxThreads;
	
	private static EvolutionSingleton instance;
	
	private EvolutionSingleton() {
		fitnessSelType = FitnessSelectionType.Highest;
		parentSelType = parentSelType.rouletteSelection;
		presumptType = presumptType.ranking;
		memberCount = 5;
		geneCount = 0;
		codingType = CodingType.binary;
		coding = new BinaryCoding();
		binaryRecombType = BinaryRecombinationType.random;
		realRecombType = RealRecombinationType.random;
		maxThreads = 1;
	}
	
	public static EvolutionSingleton getInstance() {
		if(instance == null)
			instance = new EvolutionSingleton();
		return instance;
	}
	
	public FitnessFunction getFitnessFunction() {
		return fitnessFunc;
	}
	
	public void setFitnessFunction(FitnessFunction genePool) {
		this.fitnessFunc = genePool;
	}
	
	public void calcFitness(Permutation permutation) {
		fitnessFunc.calcFitness(permutation);
	}
	
	public FitnessSelectionType getFitnessSelType() {
		return fitnessSelType;
	}
	
	public void setFitnessSelType(FitnessSelectionType fitnessSelType) {
		this.fitnessSelType = fitnessSelType;
	}
	
	public ParentSelectionType getParentSelType() {
		return parentSelType;
	}
	
	public void setParentSelType(ParentSelectionType parentSelType) {
		this.parentSelType = parentSelType;
	}
	
	public PresumptionType getPresumptType() {
		return presumptType;
	}
	
	public void setPresumptType(PresumptionType presumptType) {
		this.presumptType = presumptType;
	}
	
	public int getMemberCount() {
		return memberCount;
	}
	
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	
	public double getLowestValue() {
		return lowestValue;
	}
	
	public void setLowestValue(double lowestValue) {
		this.lowestValue = lowestValue;
		calcGranularity();
	}
	
	public double getHeighestValue() {
		return heighestValue;
	}
	
	public void setHeighestValue(double heighestValue) {
		this.heighestValue = heighestValue;
		calcGranularity();
	}
	
	public int getChainLength() {
		return chainLength;
	}
	
	public void setChainLength(int chainLength) {
		this.chainLength = chainLength;
		calcGranularity();
	}
	
	public CodingType getCodingType() {
		return codingType;
	}
	
	public void setCodingType(CodingType codingType) {
		this.codingType = codingType;
		if(codingType == CodingType.binary)
			coding  = new BinaryCoding();
		else
			coding = new RealCoding();
	}
	
	public Coding getCoding() {
		return coding;
	}
	
	public int getGeneCount() {
		return geneCount;
	}
	
	public void setGeneCount(int geneCount) {
		this.geneCount = geneCount;
	}
	
	public BinaryRecombinationType getBinaryRecombType() {
		return binaryRecombType;
	}
	
	public void setBinaryRecombType(BinaryRecombinationType binaryRecombType) {
		this.binaryRecombType = binaryRecombType;
	}
	
	public RealRecombinationType getRealRecombType() {
		return realRecombType;
	}
	
	public void setRealRecombType(RealRecombinationType realRecombType) {
		this.realRecombType = realRecombType;
	}
	
	private void calcGranularity() {
		granularity = (heighestValue - lowestValue) / (Math.pow(2, chainLength) - 1);
	}
	
	public double getGranularity() {
		return granularity;
	}
	
	public void useGrayCode(boolean grayCode) {
		useGrayCode = grayCode;
	}
	
	public boolean useGraycode() {
		return useGrayCode;
	}
	
	public int getMaxThreads() {
		return maxThreads;
	}
	
	public void setMaxThreads(int maxThreads) {
		if(maxThreads > 0)
			this.maxThreads = maxThreads;
		else
			this.maxThreads = 1;
	}
}

package jea.alg;

import jea.alg.coding.Coding;
import jea.alg.coding.CodingType;
import jea.alg.coding.binary.BinaryRecombinationType;
import jea.alg.coding.real.RealRecombinationType;
import jea.alg.selection.FitnessSelectionType;
import jea.alg.selection.ParentSelectionType;
import jea.alg.selection.PresumptionType;

public class EvolutionSingleton {
	
	private Model model;
	
	private int maxThreads;
	
	private static EvolutionSingleton instance;
	
	private EvolutionSingleton() {
		model = null;
		maxThreads = 1;
	}
	
	public static EvolutionSingleton getInstance() {
		if(instance == null)
			instance = new EvolutionSingleton();
		return instance;
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	/*public FitnessFunction getFitnessFunction() {
		return model.fitnessFunc;
	}
	
	public void setFitnessFunction(FitnessFunction genePool) {
		model.fitnessFunc = genePool;
	}*/
	
	public void calcFitness(Permutation permutation) {
		model.getFitnessFunction().calcFitness(permutation);
	}
	
	public FitnessSelectionType getFitnessSelType() {
		return model.fitnessSelType;
	}
	
	public ParentSelectionType getParentSelType() {
		return model.parentSelType;
	}
	
	public PresumptionType getPresumptType() {
		return model.presumptType;
	}
	
	public int getMemberCount() {
		return model.memberCount;
	}
	
	public double getLowestValue() {
		return model.getLowestValue();
	}
	
	public double getHeighestValue() {
		return model.getHeighestValue();
	}
	
	public int getChainLength() {
		return model.getChainLength();
	}
	
	public CodingType getCodingType() {
		return model.getCodingType();
	}
	
	public Coding getCoding() {
		return model.getCoding();
	}
	
	public int getGeneCount() {
		return model.geneCount;
	}
	
	public BinaryRecombinationType getBinaryRecombType() {
		return model.binaryRecombType;
	}
	
	public RealRecombinationType getRealRecombType() {
		return model.realRecombType;
	}
	
	public double getMutation() {
		if(model.mutation > 1.0)
			model.mutation = 1.0;
		else if(model.mutation < 0.0)
			model.mutation = 0.0;
		return model.mutation;
	}
	
	public double getGranularity() {
		return model.getGranularity();
	}
	
	public boolean useGraycode() {
		return model.useGrayCode;
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

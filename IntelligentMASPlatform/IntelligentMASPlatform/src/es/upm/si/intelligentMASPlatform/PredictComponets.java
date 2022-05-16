package es.upm.si.intelligentMASPlatform;

import java.io.Serializable;

import weka.core.Instance;
import weka.core.Instances;

public class PredictComponets implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Instances dataset;
	private Instance instance;

	public Instances getDataset() {
		return dataset;
	}

	public void setDataset(Instances dataset) {
		this.dataset = dataset;
	}

	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	
}

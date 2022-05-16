package es.upm.si.intelligentMASPlatform;

import java.io.File;
import java.io.Serializable;

public class TrainComponents implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File dataset;
	private String classificationMethod;

	public File getDataset() {
		return dataset;
	}

	public void setDataset(File dataset) {
		this.dataset = dataset;
	}

	public String getClassificationMethod() {
		return classificationMethod;
	}

	public void setClassificationMethod(String classificationMethod) {
		this.classificationMethod = classificationMethod;
	}
	
}

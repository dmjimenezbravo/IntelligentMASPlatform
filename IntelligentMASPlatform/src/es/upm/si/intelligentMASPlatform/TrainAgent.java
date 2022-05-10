package es.upm.si.intelligentMASPlatform;

import java.io.File;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import weka.classifiers.Classifier;

public class TrainAgent extends Agent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	private String classificationMethod;
	private Classifier classifier;
	protected CyclicBehaviourTrain trainBehaviour;
	
	public TrainAgent(){
		super();
		this.trainBehaviour = new CyclicBehaviourTrain();
	}
	
	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Training");
		sd.setType("Training");
		sd.addLanguages(new SLCodec().getName());
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		}
		catch(FIPAException e) {
			System.err.println("Agent " + this.getLocalName() + ": " + e.getMessage());
		}
		
		this.addBehaviour(this.getTrainBehaviour()); 
	}
	
	public CyclicBehaviourTrain getTrainBehaviour() {
		return trainBehaviour;
	}

	public void setTrainBehaviour(CyclicBehaviourTrain trainBehaviour) {
		this.trainBehaviour = trainBehaviour;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getClassificationMethod() {
		return classificationMethod;
	}

	public void setClassificationMethod(String classificationMethod) {
		this.classificationMethod = classificationMethod;
	}
	

	public Classifier getClassifier() {
		return classifier;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}
}

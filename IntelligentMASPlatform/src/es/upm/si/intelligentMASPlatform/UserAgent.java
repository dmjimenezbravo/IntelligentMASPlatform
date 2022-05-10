package es.upm.si.intelligentMASPlatform;

import java.io.File;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import weka.classifiers.Classifier;

public class UserAgent extends Agent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean canPredict;
	private boolean canTrain;
	private File file;
	private String classificationMethod;
	private Classifier classifier;
	protected CyclicBehaviourSendTrainPredict trainPredictBehaviour;
	protected CyclicBehaviourLaunchWindow launchBehaviour;
	
	public UserAgent(){
		super();
		this.canPredict = false;
		this.canTrain = false;
		this.trainPredictBehaviour = new CyclicBehaviourSendTrainPredict();
		this.launchBehaviour = new CyclicBehaviourLaunchWindow();
	}

	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("User communication");
		sd.setType("User communication");
		sd.addLanguages(new SLCodec().getName());
		dfd.addServices(sd);
		
		try
		{
			DFService.register(this, dfd);
		}
		catch(FIPAException e)
		{
			System.err.println("Agent " + this.getLocalName() + ": " + e.getMessage());
		}

		this.addBehaviour(this.getTrainPredictBehaviour());
		this.addBehaviour(this.getLaunchBehaviour());
		
		UserJFrame userJFrame = new UserJFrame(this);
		userJFrame.setVisible(true);
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

	public CyclicBehaviourSendTrainPredict getTrainPredictBehaviour() {
		return trainPredictBehaviour;
	}

	public void setTrainPredictBehaviour(CyclicBehaviourSendTrainPredict trainPredictBehaviour) {
		this.trainPredictBehaviour = trainPredictBehaviour;
	}
	public boolean isCanPredict() {
		return canPredict;
	}

	public void setCanPredict(boolean canPredict) {
		this.canPredict = canPredict;
	}

	public boolean isCanTrain() {
		return canTrain;
	}

	public void setCanTrain(boolean canTrain) {
		this.canTrain = canTrain;
	}

	public CyclicBehaviourLaunchWindow getLaunchBehaviour() {
		return launchBehaviour;
	}

	public void setLaunchBehaviour(CyclicBehaviourLaunchWindow launchBehaviour) {
		this.launchBehaviour = launchBehaviour;
	}
}


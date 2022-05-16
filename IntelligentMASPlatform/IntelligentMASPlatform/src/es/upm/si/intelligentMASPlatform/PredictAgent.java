package es.upm.si.intelligentMASPlatform;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import weka.classifiers.Classifier;

public class PredictAgent extends Agent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Classifier classifier;
	protected CyclicBehaviourRecieveModel receiveModelBehaviour;
	protected CyclicBehaviourPredict predictBehaviour;
	
	public PredictAgent(){
		super();
		this.receiveModelBehaviour = new CyclicBehaviourRecieveModel();
		this.predictBehaviour = new CyclicBehaviourPredict();
	}
	
	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Prediction");
		sd.setType("Prediction");
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

		this.addBehaviour(this.getReceiveModelBehaviour());
		this.addBehaviour(this.getPredictBehaviour());
	}

	public Classifier getClassifier() {
		return classifier;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}

	public CyclicBehaviourRecieveModel getReceiveModelBehaviour() {
		return receiveModelBehaviour;
	}

	public void setReceiveModelBehaviour(CyclicBehaviourRecieveModel receiveModelBehaviour) {
		this.receiveModelBehaviour = receiveModelBehaviour;
	}

	public CyclicBehaviourPredict getPredictBehaviour() {
		return predictBehaviour;
	}

	public void setPredictBehaviour(CyclicBehaviourPredict predictBehaviour) {
		this.predictBehaviour = predictBehaviour;
	}
	
	
}

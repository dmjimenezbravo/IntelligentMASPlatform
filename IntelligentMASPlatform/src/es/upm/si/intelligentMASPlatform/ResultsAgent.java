package es.upm.si.intelligentMASPlatform;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ResultsAgent extends Agent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double prediction;
	protected CyclicBehaviourRecievePrediction recievePredictionBehaviour;
	protected CyclicBehaviourNotifyUserAgent notifyBehaviour;
		
	public ResultsAgent(){
		super();
		this.recievePredictionBehaviour = new CyclicBehaviourRecievePrediction();
		this.notifyBehaviour = new CyclicBehaviourNotifyUserAgent();
	}
	
	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Results communication");
		sd.setType("Results communication");
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

		this.addBehaviour(this.getRecievePredictionBehaviour());
		this.addBehaviour(this.getNotifyBehaviour());
	}

	public double getPrediction() {
		return prediction;
	}

	public void setPrediction(double prediction) {
		this.prediction = prediction;
	}

	public CyclicBehaviourRecievePrediction getRecievePredictionBehaviour() {
		return recievePredictionBehaviour;
	}

	public void setRecievePredictionBehaviour(CyclicBehaviourRecievePrediction recievePredictionBehaviour) {
		this.recievePredictionBehaviour = recievePredictionBehaviour;
	}

	public CyclicBehaviourNotifyUserAgent getNotifyBehaviour() {
		return notifyBehaviour;
	}

	public void setNotifyBehaviour(CyclicBehaviourNotifyUserAgent notifyBehaviour) {
		this.notifyBehaviour = notifyBehaviour;
	}
}

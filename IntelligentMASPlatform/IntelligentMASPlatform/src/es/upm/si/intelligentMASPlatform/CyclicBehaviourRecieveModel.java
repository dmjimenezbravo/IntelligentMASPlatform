package es.upm.si.intelligentMASPlatform;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import weka.classifiers.Classifier;

public class CyclicBehaviourRecieveModel extends CyclicBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() {
		try {
			ACLMessage msg = this.myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
			
			if(msg != null) {
				Classifier classifier = (Classifier) msg.getContentObject();
				((PredictAgent) this.myAgent).setClassifier(classifier);
			}else {
				this.block(); 
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

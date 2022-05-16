package es.upm.si.intelligentMASPlatform;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CyclicBehaviourRecievePrediction extends CyclicBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() {
		try {
			ACLMessage msg = this.myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
			
			if(msg != null) {
				Double prediction = (Double) msg.getContentObject();
				((ResultsAgent) this.myAgent).setPrediction(prediction);
				
				((ResultsAgent) this.myAgent).setResultsJFrame(new ResultsJFrame((ResultsAgent) this.myAgent));
				
				((ResultsAgent) this.myAgent).getResultsJFrame().setVisible(true);;
			}else {
				this.block(); 
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

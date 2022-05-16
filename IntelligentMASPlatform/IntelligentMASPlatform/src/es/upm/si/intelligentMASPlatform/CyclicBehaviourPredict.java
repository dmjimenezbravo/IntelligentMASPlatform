package es.upm.si.intelligentMASPlatform;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import weka.core.Instance;
import weka.core.Instances;

public class CyclicBehaviourPredict extends CyclicBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() {
		try {
			ACLMessage msg = this.myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
			
			if(msg != null) {
				PredictComponets predictComponets = (PredictComponets) msg.getContentObject();
				Instances data = predictComponets.getDataset();
				Instance instance = predictComponets.getInstance();
				data.setClassIndex(data.numAttributes() - 1);
				instance.setDataset(data);
				
				Double prediction = ((PredictAgent) this.myAgent).getClassifier().classifyInstance(instance);
				
				ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
				msg2.addReceiver(new AID("ResultsAgent", AID.ISLOCALNAME));
				msg2.setContentObject(prediction);
				this.myAgent.send(msg2);				
			}else {
				this.block(); 
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

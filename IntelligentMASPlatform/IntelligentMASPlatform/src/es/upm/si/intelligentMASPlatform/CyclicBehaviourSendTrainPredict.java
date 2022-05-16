package es.upm.si.intelligentMASPlatform;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CyclicBehaviourSendTrainPredict extends CyclicBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() {
		try {
			ACLMessage msg = this.myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
			
			if(msg != null) {
				if(msg.getContent().equals("Train")) {				
					TrainComponents trainComponents = new TrainComponents();
					trainComponents.setClassificationMethod(((UserAgent) this.myAgent).getClassificationMethod());
					trainComponents.setDataset(((UserAgent) this.myAgent).getFile());
					
					ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
					msg2.addReceiver(new AID("TrainAgent", AID.ISLOCALNAME));
					msg2.setContentObject(trainComponents);
					this.myAgent.send(msg2);
				}
				else {
					if(msg.getContentObject() instanceof PredictComponets) {
						ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
						msg2.addReceiver(new AID("PredictAgent", AID.ISLOCALNAME));
						msg2.setContentObject(msg.getContentObject());
						this.myAgent.send(msg2);
					}
				}
			}else {
				this.block(); 
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

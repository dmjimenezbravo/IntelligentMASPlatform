package es.upm.si.intelligentMASPlatform;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CyclicBehaviourNotifyUserAgent extends CyclicBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() {
		try {
			ACLMessage msg = this.myAgent.receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchContent("Notify")));
			
			if(msg != null) {
				if(msg.getContent().equals("Notify")) {
					ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
					msg2.addReceiver(new AID("UserAgent", AID.ISLOCALNAME));
					msg2.setContent(msg.getContent());
					this.myAgent.send(msg2);
				}		
			}else {
				this.block(); 
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

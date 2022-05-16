package es.upm.si.intelligentMASPlatform;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CyclicBehaviourLaunchWindow extends CyclicBehaviour{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public void action() {
			try {
				ACLMessage msg = this.myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
				
				if(msg != null) {
					if(msg.getContent().equals("Notify")) {
						((UserAgent) this.myAgent).getUserJFrame().setVisible(true);
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

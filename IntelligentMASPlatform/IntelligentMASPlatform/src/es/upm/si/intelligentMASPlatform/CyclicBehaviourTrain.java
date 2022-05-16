package es.upm.si.intelligentMASPlatform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Random;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.rules.JRip;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class CyclicBehaviourTrain extends CyclicBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() {
		try {
			ACLMessage msg = this.myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
			
			if(msg != null) {
				TrainComponents trainComponets = new TrainComponents();
				trainComponets = (TrainComponents) msg.getContentObject();
				((TrainAgent) this.myAgent).setFile(trainComponets.getDataset());
				((TrainAgent) this.myAgent).setClassificationMethod(trainComponets.getClassificationMethod());
				this.train();
			}else {
				this.block(); 
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void train() {
		try {
			Instances data;
			data = new Instances(new BufferedReader(new FileReader(((TrainAgent) this.myAgent).getFile())));
			data.setClassIndex(data.numAttributes() - 1);
			
			Classifier localClassifier = null;
			if(((TrainAgent) this.myAgent).getClassificationMethod().equals("Random Forest")) {
				localClassifier = new RandomForest();	
			}
			else if(((TrainAgent) this.myAgent).getClassificationMethod().equals("JRip")) {
				localClassifier = new JRip();
			}
			else { //Bayes Net
				localClassifier = new BayesNet();
			}
			
			localClassifier.buildClassifier(data);
			Evaluation evaluation = new Evaluation(data);
			evaluation.crossValidateModel(localClassifier, data, 10, new Random(1));
			((TrainAgent) this.myAgent).setClassifier(localClassifier);
			
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContentObject((Serializable) ((TrainAgent) this.myAgent).getClassifier());
			msg.addReceiver(new AID("PredictAgent", AID.ISLOCALNAME));
			this.myAgent.send(msg);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package es.upm.si.intelligentMASPlatform;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ResultsJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsJFrame frame = new ResultsJFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResultsJFrame(final ResultsAgent resultsAgent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 297, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPredictionHeader = new JLabel("The prediction has the value:");
		lblPredictionHeader.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPredictionHeader.setBounds(10, 11, 222, 14);
		contentPane.add(lblPredictionHeader);
		
		JLabel lblPrediction = new JLabel("" + String.valueOf(resultsAgent.getPrediction()) + ".");
		lblPrediction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrediction.setBounds(10, 36, 222, 14);
		contentPane.add(lblPrediction);
		
		JButton btnAcept = new JButton("Acept");
		btnAcept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(new AID("UserAgent", AID.ISLOCALNAME));
				msg.setContent("Notify");
				resultsAgent.send(msg);
				
				setVisible(false);
				dispose();
			}
		});
		btnAcept.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAcept.setBounds(80, 74, 123, 29);
		contentPane.add(btnAcept);
	}
}

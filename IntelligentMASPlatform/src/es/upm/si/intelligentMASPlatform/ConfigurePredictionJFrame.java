package es.upm.si.intelligentMASPlatform;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ConfigurePredictionJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSepalLength;
	private JTextField textFieldSepalWidth;
	private JTextField textFieldPetalLength;
	private JTextField textFieldPetalWidth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurePredictionJFrame frame = new ConfigurePredictionJFrame(null);
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
	public ConfigurePredictionJFrame(final UserAgent userAgent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSepalLength = new JLabel("Sepal length in cm:");
		lblSepalLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSepalLength.setBounds(10, 10, 157, 25);
		contentPane.add(lblSepalLength);
		
		textFieldSepalLength = new JTextField();
		textFieldSepalLength.setBounds(186, 12, 157, 25);
		contentPane.add(textFieldSepalLength);
		textFieldSepalLength.setColumns(10);
		
		JLabel lblSepalWidth = new JLabel("Sepal width in cm:");
		lblSepalWidth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSepalWidth.setBounds(10, 47, 157, 25);
		contentPane.add(lblSepalWidth);
		
		textFieldSepalWidth = new JTextField();
		textFieldSepalWidth.setColumns(10);
		textFieldSepalWidth.setBounds(186, 49, 157, 25);
		contentPane.add(textFieldSepalWidth);
		
		JLabel lblPetalLength = new JLabel("Petal length in cm:");
		lblPetalLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPetalLength.setBounds(10, 82, 157, 25);
		contentPane.add(lblPetalLength);
		
		textFieldPetalLength = new JTextField();
		textFieldPetalLength.setColumns(10);
		textFieldPetalLength.setBounds(186, 84, 157, 25);
		contentPane.add(textFieldPetalLength);
		
		JLabel lblPetalWidth = new JLabel("Petal width in cm:");
		lblPetalWidth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPetalWidth.setBounds(10, 117, 157, 25);
		contentPane.add(lblPetalWidth);
		
		textFieldPetalWidth = new JTextField();
		textFieldPetalWidth.setColumns(10);
		textFieldPetalWidth.setBounds(186, 119, 157, 25);
		contentPane.add(textFieldPetalWidth);
		
		JButton btnPredict = new JButton("Predict");
		btnPredict.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textFieldPetalWidth.getText() != null && textFieldPetalLength.getText() != null && textFieldSepalLength.getText() != null && textFieldSepalWidth.getText() != null) {
						Instance instance = new DenseInstance(4);
						instance.setValue(0, Double.parseDouble(textFieldSepalLength.getText()));
						instance.setValue(1, Double.parseDouble(textFieldSepalWidth.getText()));
						instance.setValue(2, Double.parseDouble(textFieldPetalLength.getText()));
						instance.setValue(3, Double.parseDouble(textFieldPetalWidth.getText()));
						
						Instances data = new Instances(new BufferedReader(new FileReader(((UserAgent) userAgent).getFile())));
						data.setClassIndex(data.numAttributes() - 1);
						
						PredictComponets predictComponets = new PredictComponets();
						predictComponets.setDataset(data);
						predictComponets.setInstance(instance);
						
						ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
						msg.addReceiver(new AID("UserAgent", AID.ISLOCALNAME));
						msg.setContentObject((Serializable) predictComponets);
						userAgent.send(msg);
						
						setVisible(false);
						dispose();
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Missing arguments.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(), "Format errors.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPredict.setBounds(123, 159, 108, 37);
		contentPane.add(btnPredict);
	}
}

package es.upm.si.intelligentMASPlatform;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class UserJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean datasetLoaded;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserJFrame frame = new UserJFrame(null);
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
	public UserJFrame(final UserAgent userAgent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 23, 674, 202);
		contentPane.add(panel);
		panel.setLayout(null);

		final JTextPane textPaneDataset = new JTextPane();
		textPaneDataset.setBounds(60, 86, 277, 28);
		panel.add(textPaneDataset);
		
		this.datasetLoaded = userAgent.isCanPredict();
		JButton btnSelectDataset = new JButton("Select dataset");
		btnSelectDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fileChooser = new JFileChooser();
		            fileChooser.showOpenDialog(fileChooser);
		            String path = fileChooser.getSelectedFile().getAbsolutePath();
		            File file = new File(path);
		            textPaneDataset.setText(file.getPath());
		            userAgent.setFile(file);
		            datasetLoaded = true;
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				
			}
		});
		btnSelectDataset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelectDataset.setBounds(347, 86, 141, 28);
		panel.add(btnSelectDataset);
		
		final JComboBox<String> comboBoxMLAlgorithm = new JComboBox<String>();
		comboBoxMLAlgorithm.setBounds(60, 125, 428, 28);
		panel.add(comboBoxMLAlgorithm);
		comboBoxMLAlgorithm.addItem("Random Forest");
		comboBoxMLAlgorithm.addItem("JRip");
		comboBoxMLAlgorithm.addItem("Bayes Net");
		
		JButton btnTrain = new JButton("Train");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!datasetLoaded || String.valueOf(comboBoxMLAlgorithm.getSelectedItem()).equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Missing arguments", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					userAgent.setClassificationMethod(String.valueOf(comboBoxMLAlgorithm.getSelectedItem()));
					userAgent.setCanTrain(true);
					userAgent.setCanPredict(true);
					
					ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
					msg.addReceiver(new AID("UserAgent", AID.ISLOCALNAME));
					msg.setContent("Train");
					userAgent.send(msg);
				}
			}
		});
		btnTrain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTrain.setBounds(510, 86, 101, 67);
		panel.add(btnTrain);
		
		JLabel lblTrain = new JLabel("Train a machine learning model:");
		lblTrain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrain.setBounds(59, 48, 305, 28);
		panel.add(lblTrain);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 225, 674, 132);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPredict = new JLabel("Predict with a trained machine learning model:");
		lblPredict.setBounds(66, 23, 372, 20);
		panel_1.add(lblPredict);
		lblPredict.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnPredict = new JButton("Predict instance");
		btnPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((!datasetLoaded && String.valueOf(comboBoxMLAlgorithm.getSelectedItem()).equals("")) || !userAgent.isCanPredict()) {
					JOptionPane.showMessageDialog(new JFrame(), "Missing arguments", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					ConfigurePredictionJFrame predictionJFrame = new ConfigurePredictionJFrame(userAgent);
					predictionJFrame.setVisible(true);
					setVisible(false);
					dispose();
				}
			}
		});
		btnPredict.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPredict.setBounds(66, 53, 545, 29);
		panel_1.add(btnPredict);
	}
}

package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMenu{
	
	public GUIMenu(){
		/**Menu*/
		JFrame menuFrame = new JFrame();
		/**Labels and corresponding fields*/
		JLabel xLabel = new JLabel("x = ");
		JLabel yLabel = new JLabel("y = ");
		JLabel zLabel = new JLabel("z = ");
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);
		
		/**Choices*/
		Choice sortingChoice = new Choice();
		Choice itemChoice = new Choice();
				
		/**StartButton*/
		JButton startButton = new JButton("Start");
		class ButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				System.out.println(sortingChoice.getSelectedItem());
				System.out.println(itemChoice.getSelectedItem());
				System.out.println("works");
				double x = Double.parseDouble(xField.getText());
				double y = Double.parseDouble(yField.getText());
				double z = Double.parseDouble(zField.getText());
				//error because of not yet existant container class
				//Container container = new Container(x, y, z);
				System.out.println(x);
				System.out.println(y);
				System.out.println(z);
			}
		}
		ButtonListener listener = new ButtonListener();
		startButton.addActionListener(listener);
		/**Panels*/
		JPanel fieldPanel = new JPanel();
		JPanel startPanel = new JPanel();
		JPanel choicePanel = new JPanel();
		
		/**Setting Layouts*/
		choicePanel.setLayout(new GridLayout(2,1));
		fieldPanel.setLayout(new GridLayout(3,2));
		menuFrame.setLayout(new GridLayout(2,1));
		
		/**Adding Choices*/
		sortingChoice.add("Greedy");
		sortingChoice.add("Dynamic");
		sortingChoice.add("HillClimber");
		sortingChoice.add("Random");
		sortingChoice.add("Depth-First Tree Search");
		itemChoice.add("A,B,C packages");
		itemChoice.add("P,L,T pentominoes");
		
		/**Adding appropriate components to their panels*/ 
		choicePanel.add(sortingChoice);
		choicePanel.add(itemChoice);
		startPanel.add(startButton);
		fieldPanel.add(xLabel);
		fieldPanel.add(xField);
		fieldPanel.add(yLabel);
		fieldPanel.add(yField);
		fieldPanel.add(zLabel);
		fieldPanel.add(zField);
		
		/**Adding panels to menuFrame*/
		menuFrame.add(fieldPanel, BorderLayout.CENTER);
		menuFrame.add(choicePanel, BorderLayout.CENTER);
		menuFrame.add(startPanel, BorderLayout.SOUTH);
	
		
		/**Finalizing Frame*/
		menuFrame.setTitle("3D Knapsack Problem");
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.pack();
		menuFrame.setVisible(true);
		
	}
	
	/**instance fields*/
	/**Frame Sizes*/
	final int FRAME_WIDTH = 600;
	final int FRAME_HEIGHT = 500;
	
}
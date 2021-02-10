/**
 * View and controller components
 * 
 * @author Philip Lin
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.InputMismatchException;
import javax.swing.*;

@SuppressWarnings("serial")
public class SuperLottoSwing extends JFrame implements ActionListener{
		
	// ContentPane panels:
	private JPanel menuPanel;
	private JPanel dialoguePanel;
	
	// JPanel components:
	private JLabel quantityLabel;
	private JTextField quantityTextField;
	private JButton luckyButton, resetButton, printButton;
	private JLabel dialogueLabel;
	
	private String tickets;
	
	public SuperLottoSwing(){
				
		setTitle("SuperLottoPlus");
		getContentPane().setPreferredSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize swing components
		menuPanel = new JPanel();
		dialoguePanel = new JPanel();
		quantityLabel = new JLabel("Number of Tickets: ");
		quantityTextField = new JTextField(2);
		luckyButton = new JButton("I'm Feeling Lucky");
		resetButton = new JButton("Reset");
		printButton = new JButton("Print");
		dialogueLabel = new JLabel();
		
		// Add action listeners to buttons
		luckyButton.addActionListener(this);
		resetButton.addActionListener(this);
		printButton.addActionListener(this);
		
		// Add components to menu panel
		menuPanel.setBackground(Color.ORANGE);
		menuPanel.add(quantityLabel);
		menuPanel.add(quantityTextField);
		menuPanel.add(luckyButton);
		menuPanel.add(resetButton);
		menuPanel.add(printButton);
		
		// Add components to dialogue panel
		dialoguePanel.setBackground(Color.WHITE);
		dialoguePanel.add(dialogueLabel);
		
	}
	
	/**
	 * Initializes the program by adding components to the content pane
	 */
	public void initialize(){
		
		// Set initial values for components
		quantityTextField.setText("1");
		dialoguePanel.setBackground(Color.WHITE);
		dialogueLabel.setText("Play the Lottery!");
		
		// Add panels to content pane, pack, and set to visible
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(menuPanel, BorderLayout.NORTH);
		getContentPane().add(dialoguePanel, BorderLayout.CENTER);	
		pack();
		setVisible(true);
	}
	
	/**
	 * Handles events from the buttons in the menu panel
	 * Handles exceptions with input from the quantity text field
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
		// I'm Feeling Lucky - prints out a number of lottery tickets based on quantity from text field
		if( e.getSource() == luckyButton ){
			
			tickets = "<html>Tickets: <br>";
			
			try{		
				int quantity = Integer.parseInt( quantityTextField.getText().trim() );
				if(quantity < 1)
					throw new LottoLimitException("Sorry, there is a 1 lotto ticket minimum!");
				
				for( int i = 1; i <= quantity; i++ )
					tickets += (SuperLottoPlus.printTicket( SuperLottoPlus.generateNumbers() ) + "<br>");
				
				dialoguePanel.setBackground(Color.YELLOW);
				dialogueLabel.setText( tickets );
			}
			catch( InputMismatchException | NumberFormatException ex){
				dialogueLabel.setText("Sorry, you entered an incorrect value. Numerical numbers only please.");
			}
			catch( LottoLimitException lle ){
				dialogueLabel.setText(lle.getMessage());
			}		
		}
		
		// Reset - reinitializes the dialogue panel to its original state
		if( e.getSource() == resetButton ){

			initialize();
		}
		
		// Print - prints lotto tickets to lotto.txt file
		if( e.getSource() == printButton ){
			
			try {
				PrintWriter writer = new PrintWriter("superlotto.txt");;
				writer.println(tickets);
				writer.close();
			}
			catch (FileNotFoundException ex){ ex.printStackTrace(); }
		}	
	}

}
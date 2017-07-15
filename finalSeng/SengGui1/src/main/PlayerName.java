package main;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PlayerName {
	//private static String[];
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * Creates a Joption pane to get each players name and number of pets
	 * @param game 
	 * @param playerNum - current index of player. used to identify them before they recieve a name 
	 * @param msgS - optoinal message to be displayed, use "" if the message should be blank
	 */
	public PlayerName(Game game, int playerNum, String msgS){
		//JFrame welcome = new JFrame("Petimals");
		JTextField playerName = new JTextField();
		JLabel msg = new JLabel(msgS);
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));

		
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(3,2));
		myPanel.add(new JLabel("Player "+ playerNum +" Name"));
		myPanel.add(playerName);
		myPanel.add(new JLabel("Number of pets"));
		myPanel.add(comboBox);
		myPanel.add(msg);
		

		Object[] options = {"Get Started"};
		int test = JOptionPane.showOptionDialog(null, myPanel,"Please enter player name", JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);

		
		if (test == JOptionPane.OK_OPTION){
			game.tempString = playerName.getText();
			game.tempInt = Integer.parseInt((String)comboBox.getSelectedItem());
		}
		
		
	}

}

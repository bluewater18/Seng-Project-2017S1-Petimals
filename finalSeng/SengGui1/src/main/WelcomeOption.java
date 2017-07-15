package main;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


public class WelcomeOption {
	
	/**
	 * Creates a JOption pane allowing for the number of days and players to bet set from a combobox object
	 * @param game the game object that holds all the game information
	 */
	public WelcomeOption(Game game){
		JComboBox comboBox_players = new JComboBox();
		comboBox_players.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		JComboBox comboBox_days = new JComboBox();
		comboBox_days.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,2));
		myPanel.add(new JLabel("Number of Days:"),0,0);
		myPanel.add(comboBox_days,0,1);
		myPanel.add(new JLabel("Number of Players:"),1,0);
		myPanel.add(comboBox_players,1,1);

		Object[] options = {"Get Started"};
		int test = JOptionPane.showOptionDialog(null, myPanel,"Please enter player and day number", JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
		if (test == JOptionPane.OK_OPTION){
			game.setPlayers(Integer.parseInt((String)comboBox_players.getSelectedItem()));
			game.setDays(Integer.parseInt((String)comboBox_days.getSelectedItem()));
		}
		
	}

}

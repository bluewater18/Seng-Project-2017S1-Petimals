package main;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GameGui {
	/**
	 * Current Player
	 */
	private Player player;
	/**
	 * Pet that is selected will show stats and complete actions for this pet
	 */
	private Pet selectedPet;
	/**
	 * current Day number
	 */
	private int day;
	/**
	 * Selected Toy from the players inventory. Used to play with pets
	 */
	private Toy currentToy;
	/**
	 * Selected food from the players inventory. Used to feed pets
	 */
	private Food currentFood;
	/**
	 * Currently selected toy in the shop
	 */
	private Toy shopToy;
	/**
	 * currently selected food in the shop
	 */
	private Food shopFood;
	/**
	 * combined price for the items selected in the shop
	 */
	private int shopSum;
	

	private JFrame frame;
	private JLabel costSumLabel;

	/**
	 * Launch the application.
	 * @param gameC - current game
	 */
	public void runWindow(Game gameC) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGui window = new GameGui(gameC);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * calls initialize
	 * @param game - current game
	 */
	public GameGui(Game game) {
		initialize(game);
	}
	/**
	 * disposes of the objects frame when called
	 */
	public void endd(){
		this.frame.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game - current game
	 */
	private void initialize(Game game) {
		player = game.currPlayer;
		selectedPet = player.pets.get(0);
		day = game.getdayNum();
		
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 637, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 634, 461);
		frame.getContentPane().add(tabbedPane);
		
		tabbedPaneUpdate(tabbedPane, game);
	}
		
/////////////////////////////////////////////////////////
	
	
	/**
	 * Calculates the sum of the food and toy object the player currently has selected in the shop
	 * @return - the sum of the selected food and toy in the shop
	 */
	private int calcSum() {
		int cost = 0;
		// TODO Auto-generated method stub
		if(shopToy != null){
			cost += shopToy.getCost();
		}
		if(shopFood != null){
			cost += shopFood.getCost();
		}
		return cost;
	}

	
	/**
	 * Updates the shop panel only. This is used to change the icons showing and the players current balance after they interact eith the controller
	 * @param shopPanel Panel holding all the shop objects
	 * @param game game object that holds all the game info
	 */
	private void shopPanelRefresh(JPanel shopPanel, Game game) {
		shopPanel.removeAll();
		
		calcSum();
		costSumLabel = new JLabel("$" + shopSum);
		costSumLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		costSumLabel.setForeground(new Color(0, 204, 0));
		costSumLabel.setBounds(112, 99, 46, 14);
		shopPanel.add(costSumLabel);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getBalance() >= shopSum){
					player.spend(shopSum);
					try{
						
						player.addToy(shopToy);
						
					}
					catch(Exception noToy){}
					try{
						player.addFood(shopFood);
						
						
					}
					catch(Exception noFood){}
					
					
					
					
				}
				else{
					JOptionPane.showMessageDialog(null, "You do not have enough Money");}
				shopToy = null;
				shopFood = null;
				shopSum = calcSum();
				shopPanelRefresh(shopPanel, game);
				
			}
		});
		btnBuy.setBounds(112, 331, 133, 44);
		shopPanel.add(btnBuy);
		
		JLabel currentToyShop = new JLabel("");
		try{
			currentToyShop.setIcon(new ImageIcon(this.getClass().getResource(shopToy.getImgString())));
		}
		catch(Exception noToy){
			currentToyShop.setIcon(null);
		}
		currentToyShop.setBounds(94, 157, 64, 64);
		shopPanel.add(currentToyShop);
		
		JLabel currentFoodShop = new JLabel("");
		try{
			
			currentFoodShop.setIcon(new ImageIcon(this.getClass().getResource(shopFood.getImgString())));
		}
		catch(Exception noFood){
			
			currentFoodShop.setIcon(null);
		}
		currentFoodShop.setBounds(181, 157, 64, 64);
		shopPanel.add(currentFoodShop);
		
		JLabel chewToyLbl = new JLabel("");
		chewToyLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopToy = game.toys.get(0);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		chewToyLbl.setIcon(new ImageIcon(this.getClass().getResource(game.toys.get(0).getImgString())));
		chewToyLbl.setBounds(380, 11, 64, 64);
		shopPanel.add(chewToyLbl);
		
		JLabel pebbleLbl = new JLabel("");
		pebbleLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopToy = game.toys.get(1);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		
		JLabel chewToyDesc = new JLabel(game.toys.get(0).shopString());
		chewToyDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chewToyDesc.setForeground(new Color(255, 255, 255));
		chewToyDesc.setBounds(354, 101, 92, 14);
		shopPanel.add(chewToyDesc);
		
		
		
		pebbleLbl.setIcon(new ImageIcon(this.getClass().getResource(game.toys.get(1).getImgString())));
		pebbleLbl.setBounds(454, 11, 64, 64);
		shopPanel.add(pebbleLbl);
		
		JLabel pebbleDesc = new JLabel(game.toys.get(1).shopString());
		pebbleDesc.setForeground(Color.WHITE);
		pebbleDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		pebbleDesc.setBounds(446, 101, 92, 14);
		shopPanel.add(pebbleDesc);
		
		JLabel boneLbl = new JLabel("");
		boneLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopToy = game.toys.get(2);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});		
		boneLbl.setIcon(new ImageIcon(this.getClass().getResource(game.toys.get(2).getImgString())));
		boneLbl.setBounds(542, 11, 64, 64);
		shopPanel.add(boneLbl);
		JLabel boneDesc = new JLabel(game.toys.get(2).shopString());
		boneDesc.setForeground(Color.WHITE);
		boneDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boneDesc.setBounds(527, 99, 92, 14);
		shopPanel.add(boneDesc);
		
		
		
		
		JLabel snowLbl = new JLabel("");
		snowLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopToy = game.toys.get(3);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		
		snowLbl.setIcon(new ImageIcon(this.getClass().getResource(game.toys.get(3).getImgString())));
		snowLbl.setBounds(380, 127, 64, 64);
		shopPanel.add(snowLbl);
		
		JLabel snowDesc = new JLabel(game.toys.get(3).shopString());
		snowDesc.setForeground(Color.WHITE);
		snowDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		snowDesc.setBounds(375, 207, 92, 14);
		shopPanel.add(snowDesc);
		
		
		
		JLabel laserLbl = new JLabel("");
		laserLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopToy = game.toys.get(4);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		JLabel laserDesc = new JLabel(game.toys.get(4).shopString());
		laserDesc.setForeground(Color.WHITE);
		laserDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		laserDesc.setBounds(446, 207, 92, 14);
		shopPanel.add(laserDesc);
		
		laserLbl.setIcon(new ImageIcon(this.getClass().getResource(game.toys.get(4).getImgString())));
		laserLbl.setBounds(454, 127, 64, 64);
		shopPanel.add(laserLbl);
		
		JLabel playWheelLbl = new JLabel("");
		playWheelLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopToy = game.toys.get(5);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		playWheelLbl.setIcon(new ImageIcon(this.getClass().getResource(game.toys.get(5).getImgString())));
		playWheelLbl.setBounds(542, 127, 64, 64);
		shopPanel.add(playWheelLbl);
		
		JLabel wheelDesc = new JLabel(game.toys.get(5).shopString());
		wheelDesc.setForeground(Color.WHITE);
		wheelDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		wheelDesc.setBounds(542, 207, 92, 14);
		shopPanel.add(wheelDesc);
		
		JLabel kibbleLbl = new JLabel("");
		kibbleLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopFood = game.foods.get(0);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		
		
		kibbleLbl.setIcon(new ImageIcon(this.getClass().getResource(game.foods.get(0).getImgString())));
		kibbleLbl.setBounds(380, 235, 64, 64);
		shopPanel.add(kibbleLbl);
		
		JLabel kibbleDesc = new JLabel(game.foods.get(0).shopString());
		kibbleDesc.setForeground(Color.WHITE);
		kibbleDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		kibbleDesc.setBounds(375, 315, 92, 14);
		shopPanel.add(kibbleDesc);
		
		JLabel rawmeatLbl = new JLabel("");
		rawmeatLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopFood = game.foods.get(1);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		
		
		rawmeatLbl.setIcon(new ImageIcon(this.getClass().getResource(game.foods.get(1).getImgString())));
		rawmeatLbl.setBounds(454, 235, 64, 64);
		shopPanel.add(rawmeatLbl);
		
		JLabel rawmeatDesc = new JLabel(game.foods.get(1).shopString());
		rawmeatDesc.setForeground(Color.WHITE);
		rawmeatDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rawmeatDesc.setBounds(454, 315, 92, 14);
		
		JLabel fishLbl = new JLabel("");
		fishLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopFood = game.foods.get(2);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		
		
		shopPanel.add(rawmeatDesc);
		fishLbl.setIcon(new ImageIcon(this.getClass().getResource(game.foods.get(2).getImgString())));
		fishLbl.setBounds(542, 235, 64, 64);
		shopPanel.add(fishLbl);
		
		JLabel fishDesc = new JLabel(game.foods.get(2).shopString());
		fishDesc.setForeground(Color.WHITE);
		fishDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fishDesc.setBounds(542, 315, 92, 14);
		shopPanel.add(fishDesc);
		
		
		
		
		
		JLabel grassLbl = new JLabel("");
		grassLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopFood = game.foods.get(3);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		
		grassLbl.setIcon(new ImageIcon(this.getClass().getResource(game.foods.get(3).getImgString())));
		grassLbl.setBounds(380, 340, 64, 64);
		shopPanel.add(grassLbl);
		
		JLabel grassDesc = new JLabel(game.foods.get(3).shopString());
		grassDesc.setForeground(Color.WHITE);
		grassDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		grassDesc.setBounds(375, 415, 92, 14);
		shopPanel.add(grassDesc);
		
		
		
		JLabel catnipLbl = new JLabel("");
		catnipLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopFood = game.foods.get(4);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		
		catnipLbl.setIcon(new ImageIcon(this.getClass().getResource(game.foods.get(4).getImgString())));
		catnipLbl.setBounds(454, 340, 64, 64);
		shopPanel.add(catnipLbl);
		
		JLabel catnipDesc = new JLabel(game.foods.get(4).shopString());
		catnipDesc.setForeground(Color.WHITE);
		catnipDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		catnipDesc.setBounds(454, 415, 92, 14);
		shopPanel.add(catnipDesc);
		
		
		JLabel algaeLbl = new JLabel("");
		algaeLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shopFood = game.foods.get(5);
				shopSum = calcSum();
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
				//refresh shop Panel
			}

			
		});
		algaeLbl.setBounds(542, 340, 64, 64);
		algaeLbl.setIcon(new ImageIcon(this.getClass().getResource(game.foods.get(5).getImgString())));
		shopPanel.add(algaeLbl);
		
		JLabel algaeDesc = new JLabel(game.foods.get(5).shopString());
		algaeDesc.setForeground(Color.WHITE);
		algaeDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		algaeDesc.setBounds(542, 415, 92, 14);
		shopPanel.add(algaeDesc);
		
		JButton cancelOrderBtn = new JButton("Cancel Order");
		cancelOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopToy = null;
				shopFood = null;
				shopSum = 0;
				//tabbedPaneUpdate(tabbedPane, game);
				shopPanelRefresh(shopPanel, game);
			}
		});
		
		
		
		
		
		
		JLabel balance = new JLabel("Balance: " + player.getBalance());
		balance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		balance.setForeground(new Color(255, 255, 255));
		balance.setBackground(new Color(0, 0, 0));
		balance.setBounds(10, 296, 115, 23);
		shopPanel.add(balance);
		cancelOrderBtn.setBounds(112, 388, 133, 23);
		shopPanel.add(cancelOrderBtn);
		
		JLabel shopBG = new JLabel("");
		shopBG.setBounds(0, 0, 629, 433);
		shopPanel.add(shopBG);
		shopBG.setIcon(new ImageIcon(this.getClass().getResource("/shopBG.png")));
		
		
		shopPanel.repaint();
		
	}
	
	/**
	 * Updates the tabbed panels in the gui
	 * @param tabbedPane - current tabbedPane that stores all the panels
	 * @param game - current game object
	 */
	private void tabbedPaneUpdate(JTabbedPane tabbedPane, Game game){
		tabbedPane.removeAll();
		
		JPanel statPanel = new JPanel();
		tabbedPane.addTab("Stats", null, statPanel, null);
		statPanel.setLayout(null);
		
		JPanel shopPanel = new JPanel();
		tabbedPane.addTab("Shop", null, shopPanel, null);
		shopPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 25, 450, 200);
		statPanel.add(panel);
		
		//NEED TO Implement
		//show selected pets stats in the stat screen
		switch(player.getPetNum()){
		case(3):
			if (player.pets.get(2).getSickness()){
				
				JLabel pet3Sick = new JLabel("");
				pet3Sick.setIcon(new ImageIcon(this.getClass().getResource("/sick.gif")));
				pet3Sick.setBounds(372, 20, 24, 24);
				panel.add(pet3Sick);
			}
		if (player.pets.get(2).getMisbehaving()){
			
			JLabel pet3MB = new JLabel("");
			pet3MB.setIcon(new ImageIcon(this.getClass().getResource("/mb.gif")));
			pet3MB.setBounds(342, 20, 24, 24);
			panel.add(pet3MB);
		}
			JLabel pet3Name = new JLabel(player.pets.get(2).getName());
			pet3Name.setFont(new Font("Tahoma", Font.PLAIN, 18));
			pet3Name.setForeground(new Color(255, 255, 255));
			pet3Name.setBounds(335, 150, 96, 20);
			panel.add(pet3Name);
			JLabel pet3Img = new JLabel("");
			pet3Img.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selectedPet = player.pets.get(2);
					tabbedPaneUpdate(tabbedPane, game);
					//statPanelUpdate(statPanel);
				}
			});
			if(player.pets.get(2).getDead()){
				pet3Img.setIcon(new ImageIcon(this.getClass().getResource(player.pets.get(2).getSpecie().getDeadImg())));
				
			}
			else{
			pet3Img.setIcon(new ImageIcon(this.getClass().getResource(player.pets.get(2).getSpecie().getImage96())));
			}
			pet3Img.setBounds(312, 25, 96, 96);
			panel.add(pet3Img);
		case(2): 
			if (player.pets.get(1).getSickness()){
				
				JLabel pet2Sick = new JLabel("");
				pet2Sick.setIcon(new ImageIcon(this.getClass().getResource("/sick.gif")));
				pet2Sick.setBounds(236, 20, 24, 24);
				panel.add(pet2Sick);
			}
		if (player.pets.get(1).getMisbehaving()){
			
			JLabel pet2MB = new JLabel("");
			pet2MB.setIcon(new ImageIcon(this.getClass().getResource("/mb.gif")));
			pet2MB.setBounds(206, 20, 24, 24);
			panel.add(pet2MB);
		}
			JLabel pet2Name = new JLabel(player.pets.get(1).getName());
			pet2Name.setFont(new Font("Tahoma", Font.PLAIN, 18));
			pet2Name.setForeground(new Color(255, 255, 255));
			pet2Name.setBounds(215, 150, 96, 20);
			panel.add(pet2Name);
			JLabel pet2Img = new JLabel("");
			pet2Img.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selectedPet = player.pets.get(1);
					tabbedPaneUpdate(tabbedPane, game);
					//statPanelUpdate(statPanel);
				}
			});
			if(player.pets.get(1).getDead()){
				pet2Img.setIcon(new ImageIcon(this.getClass().getResource(player.pets.get(1).getSpecie().getDeadImg())));
			}
			else{
			pet2Img.setIcon(new ImageIcon(this.getClass().getResource(player.pets.get(1).getSpecie().getImage96())));
			}
			pet2Img.setBounds(190, 25, 96, 96);
			panel.add(pet2Img);
		case(1):
			
			if (player.pets.get(0).getSickness()&&player.pets.get(0).getDead()){
				
				JLabel pet1Sick = new JLabel("");
				pet1Sick.setIcon(new ImageIcon(this.getClass().getResource("/sick.gif")));
				pet1Sick.setBounds(105, 20, 24, 24);
				panel.add(pet1Sick);
			}
		if (player.pets.get(0).getMisbehaving()&&!player.pets.get(0).getDead()){
			
			JLabel pet1MB = new JLabel("");
			pet1MB.setIcon(new ImageIcon(this.getClass().getResource("/mb.gif")));
			pet1MB.setBounds(60, 20, 24, 24);
			panel.add(pet1MB);
		}
			JLabel pet1Name = new JLabel(player.pets.get(0).getName());
			pet1Name.setFont(new Font("Tahoma", Font.PLAIN, 18));
			pet1Name.setForeground(new Color(255, 255, 255));
			pet1Name.setBounds(70, 150, 96, 20);
			
			panel.add(pet1Name);
			JLabel pet1Img = new JLabel("");
		pet1Img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedPet = player.pets.get(0);
				tabbedPaneUpdate(tabbedPane, game);
				//statPanelUpdate(statPanel);
			}
		});
		if(player.pets.get(0).getDead()){
			pet1Img.setIcon(new ImageIcon(this.getClass().getResource(player.pets.get(0).getSpecie().getDeadImg())));
			
		}
		else{
		pet1Img.setIcon(new ImageIcon(this.getClass().getResource(player.pets.get(0).getSpecie().getImage96())));
		//panel.remove(pet1Sick);
		//panel.add(pet1MB);
		}
		pet1Img.setBounds(30, 25, 96, 96);
		panel.add(pet1Img);
		
		
		}
		
		
		JLabel bgLabel = new JLabel("");
		bgLabel.setIcon(new ImageIcon(this.getClass().getResource("/bg.gif")));
		bgLabel.setBounds(0, 0, 450, 200);
		panel.add(bgLabel);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(450, 0, 179, 354);
		statPanel.add(actionPanel);
		actionPanel.setLayout(null);
		
		JButton playBtn = new JButton("Play");
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get Toy Choice
				if (selectedPet.canAct()){
				try{
					selectedPet.play(currentToy);
				}
				catch(Exception playEx)
				{
					JOptionPane.showMessageDialog(null, "Please ensure you have a toy selected");
					selectedPet.setActions(selectedPet.getActions()+1);
				}
				tabbedPaneUpdate(tabbedPane, game);
				//statPanelUpdate(statPanel);
				}
				else{JOptionPane.showMessageDialog(null, selectedPet.getName()+" cannot act at this time");}
				
			}
		});
		playBtn.setBounds(10, 11, 159, 30);
		actionPanel.add(playBtn);
		
		JButton feedBtn = new JButton("Feed");
		feedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedPet.canAct()){
					try{
						selectedPet.feed(currentFood);
					}
					catch(Exception playEx)
					{
						JOptionPane.showMessageDialog(null, "Please ensure you have some food selected");
						selectedPet.setActions(selectedPet.getActions()+1);
					}
					tabbedPaneUpdate(tabbedPane, game);
					//statPanelUpdate(statPanel);
					}
					else{JOptionPane.showMessageDialog(null, selectedPet.getName()+" cannot act at this time");}
					
				}
			}
		);
		feedBtn.setBounds(10, 52, 159, 30);
		actionPanel.add(feedBtn);
		
		JButton toiletBtn = new JButton("Toilet");
		toiletBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedPet.canAct()){
				selectedPet.goToilet();
				tabbedPaneUpdate(tabbedPane, game);
				//statPanelUpdate(statPanel);
				}
				else{JOptionPane.showMessageDialog(null, selectedPet.getName()+" cannot act at this time");}
			}
		});
		toiletBtn.setBounds(10, 93, 159, 30);
		actionPanel.add(toiletBtn);
		
		JButton disciplineBtn = new JButton("Discipline");
		disciplineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedPet.canAct()&&selectedPet.getMisbehaving()){
				selectedPet.discipline();
				tabbedPaneUpdate(tabbedPane, game);
				//statPanelUpdate(statPanel);
				}
				else{JOptionPane.showMessageDialog(null, selectedPet.getName()+" cannot act at this time or is not misbehaving");}
			}
		});
		disciplineBtn.setBounds(10, 134, 159, 30);
		actionPanel.add(disciplineBtn);
		
		JButton vetBtn = new JButton("Vet");
		vetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedPet.canAct()&&selectedPet.getSickness()&&player.getBalance()>=20){
					selectedPet.vet();
					tabbedPaneUpdate(tabbedPane, game);
					//statPanelUpdate(statPanel);
					//shopPanelUpdate(shopPanel);
					
					}
					else{JOptionPane.showMessageDialog(null, selectedPet.getName()+" cannot act at this time or is not sick");}
	
			}
		});
		vetBtn.setBounds(10, 175, 159, 30);
		actionPanel.add(vetBtn);
		
		JButton refreshButton = new JButton("Refresh Screen");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPaneUpdate(tabbedPane, game);
			}
		});
		refreshButton.setBounds(10, 313, 159, 30);
		actionPanel.add(refreshButton);
		
		JButton reviveBtn = new JButton("Revive");
		reviveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//IMPLEMENT REVIVE
				if(selectedPet.getDead() && selectedPet.isReviveable()){
					selectedPet.revive();
					tabbedPaneUpdate(tabbedPane, game);
				}
				else if (!selectedPet.getDead()){JOptionPane.showMessageDialog(null, selectedPet.getName()+" is Already alive");}
				else{JOptionPane.showMessageDialog(null, selectedPet.getName()+" cannot be revived");
					
				}
			}
		});
		
		JButton sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedPet.canAct()){
					selectedPet.sleep();
					tabbedPaneUpdate(tabbedPane, game);
				}
				else{JOptionPane.showMessageDialog(null, selectedPet.getName()+" cannot act at this time");}
				
			}
		});
		sleepButton.setBounds(10, 216, 159, 30);
		actionPanel.add(sleepButton);
		reviveBtn.setBounds(10, 255, 159, 30);
		actionPanel.add(reviveBtn);
		
		JLabel lblWelcomePlayer = new JLabel("Welcome " + player.getName());
		lblWelcomePlayer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcomePlayer.setBounds(0, 0, 282, 26);
		statPanel.add(lblWelcomePlayer);
		
		JLabel lblDay = new JLabel("Day "+day);
		lblDay.setBounds(292, 0, 159, 26);
		statPanel.add(lblDay);
		
		JPanel currPetPanel = new JPanel();
		currPetPanel.setBounds(0, 225, 282, 208);
		statPanel.add(currPetPanel);
		currPetPanel.setLayout(null);
		
		JLabel lblSelectedPetStats = new JLabel(selectedPet.getName() + " Stats");
		lblSelectedPetStats.setBounds(10, 10, 150, 18);
		currPetPanel.add(lblSelectedPetStats);
		
		JLabel lblActions = new JLabel(selectedPet.getActions() + "/2 Actions Remaining");
		lblActions.setBounds(10, 30, 200, 18);
		currPetPanel.add(lblActions);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setBounds(10, 50, 150, 18);
		currPetPanel.add(lblHealth);
		
		
		
		JLabel lblHunger = new JLabel("Hunger:");
		lblHunger.setBounds(10, 70, 150, 18);
		currPetPanel.add(lblHunger);
		
		
		JLabel lblEnergy = new JLabel("Energy:");
		lblEnergy.setBounds(10, 90, 150, 18);
		currPetPanel.add(lblEnergy);
		
		JLabel lblMood = new JLabel("Mood:");
		lblMood.setBounds(10, 110, 150, 18);
		currPetPanel.add(lblMood);
		
		JLabel lblToilet = new JLabel("Toilet:");
		lblToilet.setBounds(10, 130, 150, 18);
		currPetPanel.add(lblToilet);
		
		JLabel lblWeight = new JLabel("Weight: " + selectedPet.getWeightInt() + " lbs");
		lblWeight.setBounds(10, 150, 150, 18);
		currPetPanel.add(lblWeight);
		
		JLabel reviveable = new JLabel("Reviveable: "+ selectedPet.isReviveable());
		reviveable.setBounds(10, 170, 160, 18);
		currPetPanel.add(reviveable);
		
		
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setForeground(new Color(255, 0, 0));
		healthBar.setValue(selectedPet.getHealthInt());
		healthBar.setBounds(75, 50, 150, 14);
		currPetPanel.add(healthBar);
		
		JProgressBar hungerBar = new JProgressBar();
		hungerBar.setForeground(new Color(184, 134, 11));
		hungerBar.setValue(selectedPet.getHungerInt());
		hungerBar.setBounds(75, 70, 150, 14);
		currPetPanel.add(hungerBar);
		
		JProgressBar energyBar = new JProgressBar();
		energyBar.setForeground(new Color(255, 255, 0));
		energyBar.setValue(selectedPet.getEnergyInt());
		energyBar.setBounds(75, 90, 150, 14);
		currPetPanel.add(energyBar);
		
		
		JProgressBar moodBar = new JProgressBar();
		moodBar.setForeground(new Color(199, 21, 133));
		moodBar.setValue(selectedPet.getMoodInt());
		moodBar.setBounds(75, 110, 150, 14);
		currPetPanel.add(moodBar);
		
		JProgressBar toiletBar = new JProgressBar();
		toiletBar.setForeground(new Color(30, 144, 255));
		toiletBar.setValue(selectedPet.getToiletInt());
		toiletBar.setBounds(75, 130, 150, 14);
		currPetPanel.add(toiletBar);
		
		/*
		JLabel weightNum = new JLabel(selectedPet.getWeightInt()+" lbs");
		weightNum.setBounds(65, 186, 46, 14);
		currPetPanel.add(weightNum);
		*/
		
		
		
		
		
		JButton endTurnButton = new JButton("End Turn");
		endTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				endd();
				synchronized(Main.waiter){
					Main.waiter.notifyAll();
				}
			}
		});
		endTurnButton.setBounds(460, 365, 159, 57);
		statPanel.add(endTurnButton);
		
		Panel invPanel = new Panel();
		invPanel.setBounds(282, 225, 168, 208);
		statPanel.add(invPanel);
		invPanel.setLayout(null);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInventory.setBounds(10, 11, 136, 20);
		invPanel.add(lblInventory);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(10, 36, 90, 14);
		invPanel.add(lblBalance);
		
		JLabel balanceAmount = new JLabel("$"+player.getBalance());
		balanceAmount.setBounds(100, 36, 46, 14);
		invPanel.add(balanceAmount);
		
		JLabel lblToys = new JLabel("Toys");
		lblToys.setBounds(10, 57, 46, 14);
		invPanel.add(lblToys);
		
		//Implement Listeners to get Food and Toy Items
		JList foodList = new JList();
		foodList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				currentFood = player.food.get(foodList.getSelectedIndex());
			}
		});
		foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel modelFood = new DefaultListModel();
		for(int i = 0; i < player.food.size(); i++){
			modelFood.addElement(player.food.get(i));
		}
		foodList.setModel(modelFood);
		//foodList.setSelectedIndex(0);
		foodList.setBounds(10, 150, 136, 47);
		
		
		JList toyList = new JList();
		toyList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				currentToy = player.toys.get((toyList.getSelectedIndex()));
			}
		});
		toyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel modelToy = new DefaultListModel();
		for(int i = 0; i < player.toys.size(); i++){
			modelToy.addElement(player.toys.get(i));
		}
		toyList.setModel(modelToy);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 69, 136, 53);
		//toyList.setSelectedIndex(0);
		toyList.setBounds(10, 75, 136, 47);
		scrollPane.add(toyList);
		invPanel.add(scrollPane);
		
		JLabel lblFood = new JLabel("Food");
		lblFood.setBounds(10, 135, 46, 14);
		invPanel.add(lblFood);
		
		ScrollPane foodScrollPane = new ScrollPane();
		foodScrollPane.setBounds(10, 148, 136, 50);
		foodScrollPane.add(foodList);
		invPanel.add(foodScrollPane);
		
		//SHOP PANEL
		shopPanelRefresh(shopPanel, game);
		
		
		//HELP PANEL
		JPanel helpPanel = new JPanel();
		//helpPanel.setBackground(new Color(255, 204, 204));
		tabbedPane.addTab("Help", null, helpPanel, null);
		helpPanel.setLayout(null);
		
		JLabel helpHeader = new JLabel("<html><h3>So You Need Help</h3></html>");
		helpHeader.setFont(new Font("Tahoma", Font.PLAIN, 16));
		helpHeader.setHorizontalAlignment(SwingConstants.CENTER);
		helpHeader.setBounds(10, 11, 186, 48);
		helpPanel.add(helpHeader);
		
		
		JLabel helpBody = new JLabel("<html>Petimals is a simple turn based game where you can have a maximum of 3 pets.<br>Each pet gets two actions per turn, use these actions to keep your pet alive<br>If, however one of your pets dies you can revive it once, resetting it to its base stats.<br>Pets have favourite food and toys which will give a better stat increase compared to other types.<br>Random events may occur such as your pet getting sick or misbehaving, these will be indicated by icons on yout pet Remove these as quickly as possible as they have a large impact on your score.");
		helpBody.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpBody.setVerticalAlignment(SwingConstants.TOP);
		helpBody.setBounds(10, 70, 609, 352);
		helpPanel.add(helpBody);
		
		
		tabbedPane.repaint();
	}
	
		
	
}

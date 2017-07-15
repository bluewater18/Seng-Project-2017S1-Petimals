package main;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PetChooser {
	/**
	 * Player whom is choosing their pets
	 */
	 public Player player;
	/**
	 * Deafult specie enum set to rock, changed by interaction with player
	 */
	public Specie selected = Specie.ROCK;
	
	
	/**
	 * Specie of pet 1
	 */
	private Specie pet1Set;
	/**
	 * Specie of pet 1
	 */
	private Specie pet2Set;
	/**
	 * Specie of pet 1
	 */
	private Specie pet3Set;
	
	
	private JFrame frame;
	private JTextField pet1Name;
	private JTextField pet2Name;
	private JTextField pet3Name;

	/**
	 * Launch the application.
	 * @param gameC - current game
	 */
	public void runWindow(Game gameC) {
		//game = gameC;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetChooser window = new PetChooser(gameC);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * dispose of the gui window when finished
	 */
	public void endd(){
		this.frame.dispose();
	}

	/**
	 * Create the application.
	 * @param game - current game
	 */
	public PetChooser(Game game) {
		initialize(game);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Game game) {
		
		Image select1 = new ImageIcon(this.getClass().getResource("/cross_96.png")).getImage();
		Image select2 = new ImageIcon(this.getClass().getResource("/cross_96.png")).getImage();
		Image select3 = new ImageIcon(this.getClass().getResource("/cross_96.png")).getImage();
		Image bg = new ImageIcon(this.getClass().getResource("/bg.gif")).getImage();
		
		
		//Get 64*64 images for pet selection labels
		Image rock64 = new ImageIcon(this.getClass().getResource("/rock_64.gif")).getImage();
		Image penguin64 = new ImageIcon(this.getClass().getResource("/penguin_64.gif")).getImage();
		Image wolf64 = new ImageIcon(this.getClass().getResource("/wolf_64.gif")).getImage();
		Image cat64 = new ImageIcon(this.getClass().getResource("/cat_64.gif")).getImage();
		Image dog64 = new ImageIcon(this.getClass().getResource("/dog_64.gif")).getImage();
		Image rabbit64 = new ImageIcon(this.getClass().getResource("/rabbit_64.gif")).getImage();
		
		Player player = game.currPlayer;
		
		switch(player.getPetNum()){
		case 3: select3 = new ImageIcon(this.getClass().getResource("/box.png")).getImage();
		case 2: select2 = new ImageIcon(this.getClass().getResource("/box.png")).getImage();
		case 1: select1 = new ImageIcon(this.getClass().getResource("/box.png")).getImage();
		}
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel petPanel = new JPanel();
		petPanel.setBounds(0, 0, 450, 200);
		frame.getContentPane().add(petPanel);
		petPanel.setLayout(null);
		
		JPanel specieOverview = new JPanel();
		
		
		JLabel petBG = new JLabel("");
		petBG.setBounds(0, 0, 450, 200);
		petBG.setIcon(new ImageIcon(bg));
		
		
		
		
		JLabel pet1 = new JLabel("");
		pet1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				pet1.setIcon(new ImageIcon(this.getClass().getResource(selected.getImage96())));
				pet1Set = selected;
				
				petPanel.repaint();
			}
		});
		pet1.setIcon(new ImageIcon(select1));
		pet1.setBounds(30, 25, 96, 96);
		petPanel.add(pet1);
		
		JLabel pet2 = new JLabel("");
		pet2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				pet2.setIcon(new ImageIcon(this.getClass().getResource(selected.getImage96())));
				pet2Set = selected;
				
				petPanel.repaint();
			}
		});
		pet2.setIcon(new ImageIcon(select2));
		pet2.setBounds(176, 25, 96, 96);
		petPanel.add(pet2);
		
		JLabel pet3 = new JLabel("");
		pet3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				pet3.setIcon(new ImageIcon(this.getClass().getResource(selected.getImage96())));
				pet3Set = selected;
				
				petPanel.repaint();
			}
		});
		pet3.setIcon(new ImageIcon(select3));
		pet3.setBounds(312, 25, 96, 96);
		petPanel.add(pet3);
		
		pet1Name = new JTextField();
		pet1Name.setBounds(30, 153, 96, 20);
		petPanel.add(pet1Name);
		pet1Name.setColumns(10);
		
		pet2Name = new JTextField();
		pet2Name.setColumns(10);
		pet2Name.setBounds(176, 153, 96, 20);
		petPanel.add(pet2Name);
		
		pet3Name = new JTextField();
		pet3Name.setColumns(10);
		pet3Name.setBounds(312, 153, 96, 20);
		petPanel.add(pet3Name);
		
		petPanel.add(petBG);
		switch(player.getPetNum()){
		case 1: petPanel.remove(pet2);petPanel.remove(pet2Name);
		case 2: petPanel.remove(pet3);petPanel.remove(pet3Name);
		
		default: break;
		}
		
		
		JPanel petSelectPanel = new JPanel();
		petSelectPanel.setBounds(449, 0, 185, 461);
		frame.getContentPane().add(petSelectPanel);
		petSelectPanel.setLayout(null);
		
		JLabel rock = new JLabel("");
		rock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selected = Specie.ROCK;
				specieOverview.removeAll();
				specieOverviewRefresh(specieOverview);
				specieOverview.repaint();
				
			}
		});
		rock.setIcon(new ImageIcon(rock64));
		rock.setBounds(60, 11, 64, 64);
		petSelectPanel.add(rock);
		
		JLabel penguin = new JLabel("");
		penguin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selected = Specie.PENGUIN;
				specieOverview.removeAll();
				specieOverviewRefresh(specieOverview);
				specieOverview.repaint();
				
			}
		});
		penguin.setIcon(new ImageIcon(penguin64));
		penguin.setBounds(60, 79, 64, 64);
		petSelectPanel.add(penguin);
		
		JLabel wolf = new JLabel("");
		wolf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selected = Specie.WOLF;
				specieOverview.removeAll();
				specieOverviewRefresh(specieOverview);
				specieOverview.repaint();
				
			}
		});
		wolf.setIcon(new ImageIcon(wolf64));
		wolf.setBounds(60, 154, 64, 64);
		petSelectPanel.add(wolf);
		
		JLabel cat = new JLabel("");
		cat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selected = Specie.CAT;
				specieOverview.removeAll();
				specieOverviewRefresh(specieOverview);
				specieOverview.repaint();
				
			}
		});
		cat.setIcon(new ImageIcon(cat64));
		cat.setBounds(60, 236, 64, 64);
		petSelectPanel.add(cat);
		
		JLabel dog = new JLabel("");
		dog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selected = Specie.DOG;
				specieOverview.removeAll();
				specieOverviewRefresh(specieOverview);
				specieOverview.repaint();
				
			}
		});
		dog.setIcon(new ImageIcon(dog64));
		dog.setBounds(60, 311, 64, 64);
		petSelectPanel.add(dog);
		
		JLabel rabbit = new JLabel("");
		rabbit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selected = Specie.RABBIT;
				specieOverview.removeAll();
				specieOverviewRefresh(specieOverview);
				specieOverview.repaint();
				
			}
		});
		rabbit.setIcon(new ImageIcon(rabbit64));
		rabbit.setBounds(60, 386, 64, 64);
		petSelectPanel.add(rabbit);
		
		
		specieOverview.setBounds(0, 228, 268, 233);
		frame.getContentPane().add(specieOverview);
		specieOverview.setLayout(null);
		specieOverviewRefresh(specieOverview);
		
		
		JLabel welcomeLabel = new JLabel("Welcome "+ game.currPlayer.getName()  +", please select your " + game.currPlayer.getPetNum() + " pets");
		welcomeLabel.setBounds(10, 203, 350, 14);
		frame.getContentPane().add(welcomeLabel);
		
		JLabel lblwelcometoGetStarted = new JLabel("<html><h3>Welcome</h3>To Get started please browse the pets on the right by clicking on them to see their stats.<br>When you have decided on what pet you wish to have please click on a corresponding box above.</html>");
		lblwelcometoGetStarted.setVerticalAlignment(SwingConstants.TOP);
		lblwelcometoGetStarted.setBounds(278, 228, 160, 220);
		frame.getContentPane().add(lblwelcometoGetStarted);
		
		JButton btnImDone_1 = new JButton("Lets Get Started!");
		btnImDone_1.setBounds(278, 435, 160, 23);
		frame.getContentPane().add(btnImDone_1);
		btnImDone_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				switch(player.getPetNum()){
				case 3:

						if(!Main.petNames.contains(pet3Name.getText())){
					player.addPetWithName(pet3Set, pet3Name.getText());
					Main.petNames.add(pet3Name.getText());
					
						}

				case 2:

						if(!Main.petNames.contains(pet2Name.getText())){
							player.addPetWithName(pet2Set, pet2Name.getText());
							Main.petNames.add(pet2Name.getText());
							
								}
								else{throw new Exception();}

				case 1:

						if(!Main.petNames.contains(pet1Name.getText())){
							player.addPetWithName(pet1Set, pet1Name.getText());
							Main.petNames.add(pet1Name.getText());
							synchronized(Main.waiter){
								Main.waiter.notifyAll();
							}

							
							endd();
							
						}

								else{throw new Exception();}
					
				}
				
			}
				catch(Exception e){player.removePets();
				JOptionPane.showMessageDialog(null, "Please make sure you have selected all your pets and they have unique names");
				}
			}
			
		});
		
		
		
	}
	
	/**
	 * used to refresh the panel so that the stats change when the player interacts with the GUI
	 * @param specieOverview 
	 */
	private void specieOverviewRefresh(JPanel specieOverview){
		JLabel lblStartingStats = new JLabel("Starting Stats For: " + selected.getSpecieStr());
		lblStartingStats.setBounds(10, 11, 257, 18);
		specieOverview.add(lblStartingStats);
		
		JLabel lblSpecie = new JLabel("Specie: " + selected.getSpecieStr());
		lblSpecie.setBounds(10, 30, 195, 18);
		specieOverview.add(lblSpecie);
		
		JLabel lblDescription = new JLabel("Description: " + selected.getDesc());
		lblDescription.setBounds(10, 50, 240, 18);
		specieOverview.add(lblDescription);
		
		JLabel lblHealthLvl = new JLabel("Health: " + selected.getHealth().getLevel());
		lblHealthLvl.setBounds(10, 70, 195, 18);
		specieOverview.add(lblHealthLvl);
		
		JLabel lblEnergy = new JLabel("Energy: " + selected.getEnergy().getLevel());
		lblEnergy.setBounds(10, 90, 195, 18);
		specieOverview.add(lblEnergy);
		
		JLabel lblHunger = new JLabel("Hunger: " + selected.getHealth().getLevel());
		lblHunger.setBounds(10, 110, 195, 18);
		specieOverview.add(lblHunger);
		
		JLabel lblToilet = new JLabel("Toilet: " + selected.getToilet().getLevel());
		lblToilet.setBounds(10, 130, 195, 18);
		specieOverview.add(lblToilet);
		
		JLabel lblWeight = new JLabel("Weight: " + selected.getWeight().getLevel());
		lblWeight.setBounds(10, 150, 195, 18);
		specieOverview.add(lblWeight);
}
}

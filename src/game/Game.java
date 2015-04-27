package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JFrame {
	private int NUM_TARGETS = 10;
	// Max and min sizes for targets
	private final int TAR_MAX = 40;
	private final int TAR_MIN = 20;

	// Window dimensions
	private final int WIDTH = 700;
	private final int HEIGHT = 700;
	private final int HUD_WIDTH = 150;

	private JPanel playPanel;
	private HUD hud;
	
	private Challenge challenge;
	private Player player;
	private ArrayList<Target> targets;
	private boolean isChallenge;
	private final int numTypesofTargets = 2;
	
	public static void main(String[] args){
		String startMsg = "The angle of attack is formed by the cyan and green lines.\n" +
				"Enter an angle and start shooting!\n"; // Probably tell user about keyboard shortcuts
		JOptionPane.showMessageDialog(null, startMsg);
		new Game();
	}

	public Game() {
		targets = new ArrayList<Target>();
		player = new Player(250,600);
		challenge = new Challenge();
		isChallenge = false; 
		spawnTargets();
		initGUI();
	}

	public void initGUI(){ 
		setTitle("Angle Game!");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 

		hud = new HUD(this);
		hud.setPreferredSize(new Dimension(HUD_WIDTH ,0));
		add(hud, BorderLayout.WEST);

		playPanel = new PlayPanel(targets, player);
		add(playPanel, BorderLayout.CENTER);
		getContentPane().setBackground(Color.black);
		setVisible(true);
		hud.getAnglePanel().requestFocus();
	} 

	public void spawnTargets() {
		Random rand = new Random();
		boolean contains = false;
		for(int i = targets.size(); i < NUM_TARGETS; i++) {	// initially 0, but will respawn missing ones otherwise
			int x = rand.nextInt(WIDTH - HUD_WIDTH - TAR_MAX) + 1;
			int y = rand.nextInt(HEIGHT - 200) + 1;
			int width = rand.nextInt(TAR_MAX) + TAR_MIN;
			int height = rand.nextInt(TAR_MAX) + TAR_MIN;   
			Target tar = randomTarget(x,y,width,height,player.getBasePoint());
			// Loops through all targets and makes sure none contain new random tar
			for(Target j : targets) {
				if(j.contains(tar)) {
					contains = true;
					break;
				}
			}
			// If random tar doesn't intersect with any other target, add
			if(!contains)
				targets.add(tar);
			contains = false;
		}
	}

	private Target randomTarget(int x, int y, int width, int height, Point playerCoord) {
		Random rand = new Random();  
		switch(rand.nextInt(numTypesofTargets)) 
		{ 
			case 0: 
			return new RectangleTarget(x, y, width, height, player.getBasePoint()); 
			case 1: 
			return new TriangleTarget(x, y, width, height, player.getBasePoint());  
		}  
		 
		return randomTarget(x, y, width, height, player.getBasePoint());
	}

	public void playerShoots() {
		player.shoot();
		repaint();
		JOptionPane.showMessageDialog(null, "Laser Fired!");
		player.doneShooting();
		repaint();
		double angle = player.getAngle();
		boolean allMissed = true;
		for(int i = 0; i < targets.size(); i++) {
			Target t = targets.get(i);
			if(t.isHit(angle)) {
				allMissed = false;
				targets.remove(i);
				t = null;	// how do we destroy the target?
				i = i - 1;	// go back one so you don't skip an element
				
				if(isChallenge) {
					String msg = "";
					if(challenge.checkChallenge(player)) {
						player.addToScore(10);
						msg = "Challenge Successful, Good Job! +10 Extra Points";
					}
					else {
						player.addToScore(-20);
						msg = "-10 Points, Challenge Failed!";
					}
					JOptionPane.showMessageDialog(null, msg);
				}
				player.addToScore(10);		// If hit, increment score.
				hud.updateScore();
			}
			hud.getAnglePanel().requestFocusInWindow();
		}
		if(isChallenge && allMissed) {
			player.addToScore(-20);
			JOptionPane.showMessageDialog(null, "-10 Points, Challenge Missed!");
		}
		spawnTargets(); // respawn targets, for NUM_TARGETS - targets.size()
		int oneInFour = new Random().nextInt(4);
		if(oneInFour == 0) {		// if random int is 0, (25% chance) then give a new challenge
			String chal = challenge.getChallenge(); 
			if(chal != "") {
				hud.updateChallenge(chal);
				isChallenge = true;
			}
		}
		else {
			hud.updateChallenge("");
			isChallenge = false;	// turn challenge "off" after success/failure
		}
	}
	public ArrayList<Target> getTargets() { return targets; }
	public Player getPlayer() { return player; }
}

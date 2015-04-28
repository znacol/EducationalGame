package game;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

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
	
	private Missle missle; 
	private Timer timer;
	
	
	public static void main(String[] args){
		String startMsg = "The angle of attack is formed by the cyan and green lines.\n" +
				"Enter an angle and start shooting!\n" + 
				"\nKeyboard Shortcuts: \nUP/DOWN Arrows Change Angle. Spacebar Shoots."; // Probably tell user about keyboard shortcuts
		JOptionPane.showMessageDialog(null, startMsg);
		new Game();
	}

	public Game() {
		targets = new ArrayList<Target>();
		player = new Player(250,600);
		challenge = new Challenge();
		isChallenge = false; 
		spawnTargets(); 
		missle = new Missle(player.calcBarrelEnd().getX(), player.calcBarrelEnd().getY()); 
		timer = new Timer(10, new TimerListener());
		initGUI(); 
		
	}

	public void initGUI(){ 
		setTitle("Angle Game!");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 

		hud = new HUD(this);
		hud.setPreferredSize(new Dimension(HUD_WIDTH ,0));
		add(hud, BorderLayout.WEST);

		playPanel = new PlayPanel(targets, player, missle, timer);
		add(playPanel, BorderLayout.CENTER);
		getContentPane().setBackground(Color.black);
		setVisible(true);
		hud.getAnglePanel().requestFocus();
	} 

	public void spawnTargets() {
		Random rand = new Random();
		boolean contains = false;
		while(targets.size() < NUM_TARGETS) {
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
			default:
				return new RectangleTarget(x, y, width, height, player.getBasePoint());
		}  
	}

	public void playerShoots() { 
		shootTheMissle();
		double angle = player.getAngle();
		boolean allMissed = true;
		String msg = "";
		int challengeMultiplier = 0;
		ArrayList<Target> hitTars = new ArrayList<Target>();
		for(int i = 0; i < targets.size(); i++) {
			Target t = targets.get(i);
			if(t.isHit(angle)) {
				allMissed = false;
				hitTars.add(t);
				if(isChallenge) {
					if(challenge.checkChallenge(player)) {
						player.addToScore(10);
						challengeMultiplier++;
						if(challengeMultiplier == 1) {
							msg = "Challenge Successful, Good Job! +10 Extra Points";	
						} else {
							msg = challengeMultiplier + " challenge hits: +" + (20 * challengeMultiplier) + " Points!!";
						}
					}
					else {
						player.addToScore(-20);
						msg = "-10 Points, Challenge Failed!";
					}
				}
				player.addToScore(10);		// If hit, increment score.
			}
			hud.getAnglePanel().requestFocusInWindow();
		}
		for(Target hit : hitTars) {
			targets.remove(hit);
		}
		spawnTargets(); // respawn targets, for NUM_TARGETS - targets.size()
		if(isChallenge) {
			if(allMissed) {
				player.addToScore(-10);
				msg = "-10 Points, Challenge Missed!";
			}
			JOptionPane.showMessageDialog(null, msg);
		}
		hud.updateScore();
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
	 
	public void shootTheMissle() {
		// velocity, angle etc. would go here
		// do NOT put a loop in here; set end conditions in helper
		timer.start();
	}
	
	private void shootHelper() {
		// more complex logic is probably needed to determine when to stop
		if (missle.getY() < Missle.SIZE){
			timer.stop();  
			missle.setX(player.calcBarrelEnd().getX());  
			missle.setY(player.calcBarrelEnd().getY());
		}
		else {
			missle.move(player.checkAngle());
		}
	}
	
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// this is probably the only place you need a repaint
			repaint(); 
			// calls a helper to do most of the logic
			shootHelper();
		}
	}
	
}

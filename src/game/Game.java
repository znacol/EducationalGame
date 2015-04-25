package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
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

	private JPanel hud, playPanel;

	Player player;
	ArrayList<Target> targets;
	

	public static void main(String[] args){
		new Game();
	}

	public Game() {
		targets = new ArrayList<Target>();
		player = new Player(250,600);
		spawnTargets();
		initGUI();
	}

	public void initGUI(){ 
		setTitle("Geometry Game!");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 

		hud = new HUD(this);
		hud.setPreferredSize(new Dimension(HUD_WIDTH ,0));
		add(hud, BorderLayout.WEST);

		playPanel = new PlayPanel(targets, player);
		add(playPanel, BorderLayout.CENTER);

		setVisible(true);
	} 

	public void spawnTargets() {
		Random rand = new Random();
		boolean contains = false;
		for(int i = targets.size(); i < NUM_TARGETS; i++) {	// initially 0, but will respawn missing ones otherwise
			int x = rand.nextInt(WIDTH - HUD_WIDTH - TAR_MAX) + 1;
			int y = rand.nextInt(HEIGHT - 200) + 1;
			int width = rand.nextInt(TAR_MAX) + TAR_MIN;
			int height = rand.nextInt(TAR_MAX) + TAR_MIN;
			RectangleTarget tar = new RectangleTarget(x, y, width, height, player.getBasePoint());
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

	public void repaintGame(){
		repaint();
	}
	public ArrayList<Target> getTargets() { return targets; }

	public Player getPlayer() {return player;}

	public void playerShoots() {
		player.shoot();
		double angle = player.getAngle();
		for(int i = 0; i < targets.size(); i++) {
			Target t = targets.get(i);
			if(t.isHit(angle)) {
				targets.remove(i);
				t = null;	// how do we destroy the target?
				i = i - 1;	// go back one so you don't skip an element
				System.out.println("test");
			}
		}
		NUM_TARGETS = targets.size();
		spawnTargets(); // respawn targets, for NUM_TARGETS - targets.size()
		repaint();
		
	}

}

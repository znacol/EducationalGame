package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

// This class is intended to draw the Player and Targets on screen
// We decided this would keep Game.java more manageable

public class PlayPanel extends JPanel {
	
	private ArrayList<Target> targets;
	private Player player;
	
	public PlayPanel(ArrayList<Target> targets, Player player) {
		this.player = player;
		this.targets = targets;
		setBorder(new EtchedBorder());
		setBackground(Color.BLACK);
	}
	
	public void updateTargets() {}
	
	public void updateAngle() {}
	
	@Override
	public void paintComponent(Graphics g) {
		for(Target i : targets){
			i.draw(g);
		}
		player.draw(g);
	}
}

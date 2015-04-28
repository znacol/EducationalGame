package game;

//import Ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.Timer;

// This class is intended to draw the Player and Targets on screen
// We decided this would keep Game.java more manageable

public class PlayPanel extends JPanel {
	
	private ArrayList<Target> targets;
	private Player player; 
	private Missle missle; 
	private Timer timer;
	
	public PlayPanel(ArrayList<Target> targets, Player player, Missle missle, Timer timer) {
		this.player = player;
		this.targets = targets;  
		this.missle = missle; 
		this.timer = timer;
		/*missle = new Missle(player.calcBarrelEnd().getX(), player.calcBarrelEnd().getY()); 
		timer = new Timer(10, new TimerListener());*/
		setBorder(new EtchedBorder());
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		for(Target i : targets){
			i.draw(g);
		}
		player.draw(g);  
		if(!timer.isRunning()) {
			missle.setX(player.calcBarrelEnd().getX());  
			missle.setY(player.calcBarrelEnd().getY());  
			missle.draw(g);
		} 
		else
			missle.draw(g);
	}  
	
	/*public void shootTheMissle() {
		// velocity, angle etc. would go here
		// do NOT put a loop in here; set end conditions in helper
		timer.start();
	}
	
	private void shootHelper() {
		// more complex logic is probably needed to determine when to stop
		if (missle.getY() < missle.SIZE)
			timer.stop();
		else {
			missle.move(player.checkAngle());
		}
	}
	
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// this is probably the only place you need a repaint 
			shootHelper();
			repaint(); 
			// calls a helper to do most of the logic
			shootHelper();
		}
	}*/
}

package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int score;
	private int barrelAngle;
	
	public Player() {
		score = 0;
	}

	public void setBarrelAngle(int barrelAngle) {
		this.barrelAngle = barrelAngle;
	}

	public int checkAngle() {return barrelAngle;}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){return score;}
	
	// Player is just a rectangle for now. Need to change.
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(800, 800, 30, 30);
	}

}

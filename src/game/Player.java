package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Player {
	private int score;
	private int barrelAngle;
	private static int baseDimension = 50;
	private int x, y;
	private int barrelLength = 50;
	private Point barrelStart, barrelEnd;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		score = 0;
		barrelStart = new Point(x + baseDimension / 2, y + baseDimension / 2);
		barrelEnd = calcBarrelEnd();
	}
	
	// Use trig to calculate where the end point for the line of the barrel
	public Point calcBarrelEnd() {
		Point e = new Point();
		double y = Math.sin(barrelAngle) * barrelLength;
		y += barrelStart.getY();
		double x = Math.cos(barrelAngle) * barrelLength;
		x += barrelStart.getX();
		e.x = (int) x;
		e.y = (int) y;
		return e;
	}
	
	public Player() { score = 0; }

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
		g.drawRect(x, y, baseDimension, baseDimension);
		int x1 = (int) barrelStart.getX();
		int y1 = (int) barrelStart.getY();
		int x2 = (int) barrelEnd.getX();
		int y2 = (int) barrelEnd.getY();
		g.drawLine(x1, y1, x2, y2);			// draw the line for the Barrel
	}

}

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
		init();
	}
	
	public void init() {
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
		e.setLocation(x, y);
		return e;
	}
	
	public Player() { 
		x = 250;
		y = 600;
		init();	
	}

	public void setBarrelAngle(int barrelAngle) {
		this.barrelAngle = barrelAngle;
		barrelEnd = calcBarrelEnd(); // recalculate the endpoints for drawing the barrel
	}

	// Should return the calculated angle from the points forming the graphic barrel! ?
	public int checkAngle() {
		double dx = Math.abs(barrelStart.getX() - barrelEnd.getX());
		double dy = Math.abs(barrelStart.getY() - barrelEnd.getY());
		double cosCalc = Math.acos(dx / barrelLength);
		double sinCalc = Math.asin(dy / barrelLength);
		double avg = (cosCalc + sinCalc) / 2;
		return (int) avg;
	}
	
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
	
	public int getAngle() { return barrelAngle; }
}

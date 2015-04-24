package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Player {
	private int score;
	private double barrelAngle;
	private static int baseDimension = 50;
	private int x, y;	// the initial point for drawRect() for the "base" of the Player/tank
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
		// take shift off y param to make barrelStart on top edge of Player base
		barrelEnd = calcBarrelEnd();
	}
	
	// Use trig to calculate the end point for the line of the barrel based on angle and length
	public Point calcBarrelEnd() {
		Point e = new Point();
		double y = Math.sin(-Math.toRadians(barrelAngle)) * barrelLength;
		y += barrelStart.getY();
		double x = Math.cos(Math.toRadians(barrelAngle)) * barrelLength;
		x += barrelStart.getX();
		e.setLocation(x, y);	// allows setting Point x/y as double
		return e;
	}
	
	public Player() { 
		x = 250;
		y = 600;
		init();	
	}

	// take in the new barrelAngle, set the instance's and then recalculate the endpoint for the barrel's line
	public void setBarrelAngle(double barrelAngle) {
		this.barrelAngle = barrelAngle;
		barrelEnd = calcBarrelEnd(); // recalculate the endpoints for drawing the barrel
	}

	public Point getBasePoint() {
		return barrelStart;
	}
	
	// Should return the calculated angle from the points forming the graphic barrel! ?
	public double checkAngle() {
		double dx = (barrelEnd.getX() - barrelStart.getX());
		// double dy = (barrelEnd.getY() - barrelStart.getY());
		double cosCalc = Math.toDegrees(Math.acos(dx / barrelLength));
		// double sinCalc = Math.toDegrees(Math.asin(dy / barrelLength));
		// double avg = (Math.abs(cosCalc) + Math.abs(sinCalc)) / 2;
		// avg = Math.round(avg); probably wouldn't want to round to compare to a double either
		// returning cosCalc and not the average of arcsin and arccos calculations
		// did not want to deal with arcsin and the difference in 1st and 2nd quandrants
		return cosCalc;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){return score;}
	
	// Player is a rectangle with a line.
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(x, y, baseDimension, baseDimension);
		int x1 = (int) barrelStart.getX();
		int y1 = (int) barrelStart.getY();
		int x2 = (int) barrelEnd.getX();
		int y2 = (int) barrelEnd.getY();
		g.drawLine(x1, y1, x2, y2);			// draw the line for the Barrel
	}
	
	public double getAngle() { return barrelAngle; }

	public void shoot() {
		// TODO Auto-generated method stub
		
	}
}

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Player {
	private int score;
	private double barrelAngle;
	private static int baseDimension = 50;
	private int x, y;	// the initial point for drawRect() for the "base" of the Player/tank
	private int barrelLength = 75;
	private Point barrelStart, barrelEnd;
	private static Color barrelColor = Color.GREEN;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		score = 0;
		init();
	}
	public Player() { 
		x = 250;
		y = 600;
		score = 0;
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

	// Return the calculated angle from the points forming the graphic barrel
	public double checkAngle() {
		double dx = (barrelEnd.getX() - barrelStart.getX());
		double cosCalc = Math.toDegrees(Math.acos(dx / barrelLength));
		return cosCalc;
	}

	// Player is a rectangle with a line.
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(x, y, baseDimension, baseDimension);
		int x1 = (int) barrelStart.getX();
		int y1 = (int) barrelStart.getY();
		int x2 = (int) barrelEnd.getX();
		int y2 = (int) barrelEnd.getY();
		g.setColor(Color.cyan);
		g.drawLine(x1, y1, x1 + 50, y1);	// draw a line with which the barrel will form the angle
		g.setColor(barrelColor);
		g.drawLine(x1, y1, x2, y2);			// draw the line for the Barrel
		String theta = Integer.toString((int)Math.round(barrelAngle)) + "\u00b0";	// angle and degree symbol
		g.drawString(theta, x1 - 10, y1 + 20);	// draw the string representation of the barrelAngle
	}

	public void shoot() {
		// What does this need to do?? playerShoot() in Game.java does all that is needed to shoot.
		// Maybe can draw trajectory line of "missile"?
		barrelLength = 600;
		barrelEnd = calcBarrelEnd();
		barrelColor = Color.RED;
	}
	
	public void doneShooting() {
		barrelLength = 75;
		barrelEnd = calcBarrelEnd();
		barrelColor = Color.GREEN;
	}

	public void addToScore(int points){ this.score = score + points; }
	public double getAngle() { return barrelAngle; }
	public int getScore() { return score; }
	public Point getBasePoint() { return barrelStart; }

	// take in the new barrelAngle, set the instance's and then recalculate the endpoint for the barrel's line
	public void setBarrelAngle(double barrelAngle) {
		this.barrelAngle = barrelAngle % 360;
		barrelEnd = calcBarrelEnd();
	}
}

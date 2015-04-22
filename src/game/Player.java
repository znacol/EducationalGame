package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Player {
	private int score;
	private int barrelAngle;
	private static int baseDimension = 50;
	private int x, y;	// the initial point for drawRect() for the "base" of the Player/tank
	private int barrelLength = 50;
	private Point barrelStart, barrelEnd;
	private Game game;
	
	public Player(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
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
		double pythag = Math.sqrt(Math.pow(Math.abs(barrelStart.getX() - x), 2) + Math.pow(Math.abs(barrelStart.getY() - y), 2));
		if(Math.abs(barrelLength - pythag) >= 0.00001) {
			System.out.println(pythag + " = x^2 + y^2 does not equal the barrelLength when calculating new endpoints...");
		}
		e.setLocation(x, y);	// allows setting Point x/y as double
		return e;
	}
	
	public Player() { 
		x = 250;
		y = 600;
		init();	
	}

	// take in the new barrelAngle, set the instance's and then recalculate the endpoint for the barrel's line
	public void setBarrelAngle(int barrelAngle) {
		this.barrelAngle = barrelAngle;
		barrelEnd = calcBarrelEnd(); // recalculate the endpoints for drawing the barrel
		game.repaintGame();
	}

	// Should return the calculated angle from the points forming the graphic barrel! ?
	public int checkAngle() {
		double dx = Math.abs(barrelStart.getX() - barrelEnd.getX());
		double dy = Math.abs(barrelStart.getY() - barrelEnd.getY());
		double cosCalc = Math.toDegrees(Math.acos(dx / barrelLength));
		double sinCalc = Math.toDegrees(Math.asin(dy / barrelLength));
		System.out.println("dx: " + dx + ", dy: " + dy);
		System.out.println("arccos(x/barrelLength): " + cosCalc + ", arcsin(y/barrelLength): " + sinCalc);
		double avg = (cosCalc + sinCalc) / 2;
		return (int) avg;
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
	
	public int getAngle() { return barrelAngle; }
}

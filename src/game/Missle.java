package game;

import java.awt.Color;
import java.awt.Graphics;

public class Missle {

	private double x, y;
	public static int SIZE = 5;
	public static int VELOCITY = 15;
	private Player player;
	
	public Missle(double x, double y, Player player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}
	
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	} 
	
	public void setX(double x) { 
		this.x = x;
	} 
	
	public void setY(double y) { 
		this.y = y;
	}
	
	// move a certain number of pixels (VELOCITY) in the correct angle
	public void move(double barrelAngle) {
		double rad = Math.toRadians(barrelAngle);
		y -= VELOCITY * Math.sin(rad);	// ball should always go up if angle is between 0 and 180
		x += (VELOCITY * Math.cos(rad));
	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		double rad = player.getAngle();
		g.drawLine((int)x, (int)y, (int)(x + Math.cos(Math.toRadians(rad)) * 800), (int)(y + Math.sin(-Math.toRadians(rad)) * 800));
	}
}

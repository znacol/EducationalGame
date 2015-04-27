package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.util.Random;

public abstract class Target {
	
	protected Color color;
	protected double minHittableAngle, maxHittableAngle;
	
	public Target() {
		color = randomColor();
	}
	
	protected Color randomColor() {
		Random rand = new Random();
		float r = rand.nextFloat(), g = rand.nextFloat(), b = rand.nextFloat();
		return new Color(r, g, b);
	}

	public abstract boolean contains(Point p);
	
	public boolean contains(Target t) 
	{  
		Shape a = getShape(); 
		Shape b = t.getShape(); 
		if(a.intersects(b.getBounds2D())) 
			return true;

		return false;
	}
	
	public abstract void draw(Graphics g);
	
	public boolean isHit(double barrelAngle) {
		return minHittableAngle <= barrelAngle && barrelAngle <= maxHittableAngle;
	}
	
//	public abstract void calcMinHitAngle(Point barrelBase);
//	public abstract void calcMaxHitAngle(Point barrelBase);
	
	public double getMinHitAngle() { return minHittableAngle; }
	
	public double getMaxHitAngle() { return maxHittableAngle; } 
	
	public abstract Shape getShape();
}

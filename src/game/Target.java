package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public abstract class Target {
	
	protected Color color;
	
	public Target() {
		color = randomColor();
	}
	
	protected Color randomColor() {
		Random rand = new Random();
		float r = rand.nextFloat(), g = rand.nextFloat(), b = rand.nextFloat();
		return new Color(r, g, b);
	}

	public abstract boolean contains(Point p);
	
	public abstract boolean contains(Target t);
	
	public abstract void draw(Graphics g);
}

package game;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Target {

	public abstract boolean contains(Point p);
	
	public abstract boolean contains(Target t);
	
	public abstract void draw(Graphics g);
}

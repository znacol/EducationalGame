package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class RectangleTarget extends Target {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public RectangleTarget(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean contains(Point p) {
		Rectangle rect = new Rectangle(x, y, width, height);
		if(rect.contains(p))
			return true;
		return false;
	}

	@Override
	public boolean contains(Target t) {
		//Right now only checks if RectangleTarget contains other RectangleTarget- need to change if implementing other shapes
		Rectangle rect = new Rectangle(x, y, width, height);
		RectangleTarget other = (RectangleTarget)(t);
		Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);
		if(rect.intersects(otherRect))
			return true;
		return false;
	}
	
	// Should randomize color of target.
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
}

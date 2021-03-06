package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Arrays;

public class RectangleTarget extends Target {
	private int x;
	private int y;
	private int width;
	private int height;

	// This constructor shouldn't be used other than backward compatibility for testing
	public RectangleTarget(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		calcHitRange(new Point(0,0));
	}

	public RectangleTarget(int x, int y, int width, int height, Point playerCoord) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		calcHitRange(playerCoord);
	}
	/*
	 * test max and min angle for the four corners of the square
	 * case 1 top left
	 * case 2 top right
	 * case 3 bottom left
	 * case 4 bottom right
	*/
	private void calcHitRange(Point barrelBase) {
		double baseX = barrelBase.getX();
		double baseY = barrelBase.getY();
		double temp[] = {0,0,0,0}; 
		//case 1
		double dx = baseX - x;
		double dy = baseY - y;
		temp[0] = calculations(dx, dy);
		//case 2
		dx = baseX - (x + width);
		dy = baseY - y;
		temp[1] = calculations(dx, dy);
		//case 3
		dx = baseX - x;
		dy = baseY - (y + height);
		temp[2] = calculations(dx, dy);
		//case 4
		dx = baseX - (x + width);
		dy = baseY - (y + height);
		temp[3] = calculations(dx, dy);
		Arrays.sort(temp);
		minHittableAngle = temp[0];
		maxHittableAngle = temp[3];
	}
	
	/* ArcTan calculates a positive angle from 0-90, range needed 0 to 180
	 * if dx is larger than zero it is an obtuse angle
	 * to adjust for this subtract angle from 90 mult by 2
	 * add it to original angle to get angle past 90
	 * If dx is negative simply take abs 
	 * */
	public double calculations(double dx, double dy){
		boolean setPast = false;
		if(dx > 0){
			setPast = true;
		}
		else dx = Math.abs(dx);
		double angle = Math.toDegrees(Math.atan(dy / dx));
		if (setPast == true){
			double temp = 90 - angle;
			angle += temp*2;
		}
		return Math.abs(angle);
	}

	@Override
	public boolean contains(Point p) {
		Rectangle rect = new Rectangle(x, y, width, height);
		if(rect.contains(p))
			return true;
		return false;
	}
 
	// Should randomize color of target.
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawRect(x, y, width, height);
	}

	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	@Override
	public Shape getShape() {
		return new Rectangle(x,y,width,height);
	}
}

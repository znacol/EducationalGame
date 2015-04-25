package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

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

	private void calcHitRange(Point playerCoord) {
		//calcMinHitAngle(playerCoord);
		
		//calcMaxHitAngle(playerCoord);
		calcMaxMin(playerCoord);
		//adjustForArea(); do not need at the moment = PENDING DELETE
		System.out.println("Min: " + minHittableAngle + " Max:" + maxHittableAngle);
		
		
	}

	/*private void adjustForArea() {
		if (minHittableAngle > maxHittableAngle){
			double temp = maxHittableAngle;
			maxHittableAngle = minHittableAngle;
			minHittableAngle = temp;
		}
		
	}
*/
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
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	@Override
	public void calcMinHitAngle(Point barrelBase) {
		boolean setPast = false;
		double baseX = barrelBase.getX();
		double baseY = barrelBase.getY();
		double dx = baseX - (x + width);
		if(dx > 0){
			setPast = true;
		}else dx = Math.abs(dx);
		double dy = baseY - (y + height);
		double angle = Math.toDegrees(Math.atan(dy / dx));
		if (setPast == true){
			double temp = 90 - angle;
			angle += temp*2;
		}
		minHittableAngle = Math.abs(angle);
		
	}

	@Override
	public void calcMaxHitAngle(Point barrelBase) {
		boolean setPast = false;
		double baseX = barrelBase.getX();
		double baseY = barrelBase.getY();
		double dx = baseX - x;
		if(dx > 0){
			setPast = true;
		}
		else dx = Math.abs(dx);
		double dy = baseY - y;
		double angle = Math.toDegrees(Math.atan(dy / dx));
		if (setPast == true){
			double temp = 90 - angle;
			angle += temp*2;
		}
		maxHittableAngle = Math.abs(angle);
		
	}
	
	public void calcMaxMin(Point barrelBase){
		double baseX = barrelBase.getX();
		double baseY = barrelBase.getY();
		double temp[] = {0,0,0,0}; 
		//case 1
		double dx = baseX - x;
		double dy = baseY - y;
		temp[0] = calulations(dx, dy);
		//case 2
		dx = baseX - (x + width);
		dy = baseY - y;
		temp[1] = calulations(dx, dy);
		//case 3
		dx = baseX - x;
		dy = baseY - (y + height);
		temp[2] = calulations(dx, dy);
		//case 4
		dx = baseX - (x + width);
		dy = baseY - (y + height);
		temp[3] = calulations(dx, dy);
		Arrays.sort(temp);
		minHittableAngle = temp[0];
		maxHittableAngle = temp[3];
		
	}
	public double calulations(double dx, double dy){
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
}

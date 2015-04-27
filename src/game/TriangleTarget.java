package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Arrays;

public class TriangleTarget extends Target { 
	private int width;
	private int height; 
	private int[] xPts; 
	private int[] yPts;

	public TriangleTarget(int x, int y, int width, int height, Point playerCoord) {
		super();
		this.width = width;
		this.height = height; 
		xPts = new int[3]; 
		yPts = new int[3];
		xPts[0] = x; xPts[1] = x + width; xPts[2] = x + width / 2; 
		yPts[0] = y; yPts[1] = y; yPts[2] = y + height;
		calcHitRange(playerCoord);
	}
	
	private void calcHitRange(Point barrelBase) {
		double baseX = barrelBase.getX();
		double baseY = barrelBase.getY();
		double temp[] = {0,0,0}; 
		//case 1
		double dx = baseX - xPts[0];
		double dy = baseY - yPts[0];
		temp[0] = calculations(dx, dy);
		//case 2
		dx = baseX - xPts[1];
		dy = baseY - yPts[1];
		temp[1] = calculations(dx, dy);
		//case 3
		dx = baseX - xPts[2];
		dy = baseY - yPts[2];
		temp[2] = calculations(dx, dy);
		Arrays.sort(temp); 
		minHittableAngle = temp[0];
		maxHittableAngle = temp[2];
	} 
	
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
		Polygon pol = new Polygon(xPts, yPts, 3);
		if(pol.contains(p))
			return true; 
		
		return false;
	}

	public void draw(Graphics g) {
			g.setColor(color); 
			g.fillPolygon(xPts, yPts, 3); 	
	}

	public int getX() {return xPts[0];}
	public int getY() {return yPts[0];} 
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	@Override
	public Shape getShape() { 
		return new Polygon(xPts, yPts, 3);
	}

}

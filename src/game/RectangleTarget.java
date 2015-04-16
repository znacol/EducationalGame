package game;

import java.awt.Point;

public class RectangleTarget extends Target {

	public RectangleTarget(int x, int y, int width, int height) {
		
	}

	@Override
	public boolean contains(Point p) {
		return false;
	}

	@Override
	public boolean contains(Target t) {
		return false;
	}
}

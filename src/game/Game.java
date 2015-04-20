package game;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private final int NUM_TARGETS = 10;
	// Max and min sizes for targets
	private final int TAR_MAX = 20;
	private final int TAR_MIN = 8;

	// Window dimensions
	private final int WIDTH = 800;
	private final int HEIGHT = 800;

	ArrayList<Target> targets;

	public Game() {
		targets = new ArrayList<Target>();
	}

	public void spawnTargets() {
		Random rand = new Random();
		boolean contains = false;
		for(int i = 0; i < NUM_TARGETS; i++) {
			int x = rand.nextInt(WIDTH) + HUD.WIDTH;
			int y = rand.nextInt(HEIGHT) + 1;
			int width = rand.nextInt(TAR_MAX) + TAR_MIN;
			int height = rand.nextInt(TAR_MAX) + TAR_MIN;
			RectangleTarget tar = new RectangleTarget(x, y, width, height);
			
			// Loops through all targets and makes sure none contain new random tar
			for(Target j : targets) {
				if(j.contains(tar)) {
					contains = true;
					break;
				}
			}
			// If random tar doesn't intersect with any other target, add
			if(!contains)
				targets.add(tar);
			contains = false;
		}
	}

	public ArrayList<Target> getTargets(){
		return targets;
	}

}

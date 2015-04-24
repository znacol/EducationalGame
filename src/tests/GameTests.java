package tests;

import static org.junit.Assert.*;

import game.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTests {

	private static Game game; 
	
	@BeforeClass	// initialize a Game with a list of targets
	public static void setUp() {
		game = new Game();
	}

	
	@Test // when we expect the shot to hit the target, it is hit and destroyed
	public void hitDestroys() {
		// get the angle at which a target from the list should be destroyed at
		ArrayList<Target> tars = game.getTargets();
		Player p = game.getPlayer();
		for(int i = 0; i < tars.size(); i++) {
			Target t = tars.get(i);
			double min = t.getMinHitAngle();
			double max = t.getMaxHitAngle();
			double theta = (min + max) / 2;
			System.out.println(theta);
			p.setBarrelAngle(theta);
			game.playerShoots();
			tars = game.getTargets();
			for(Target living : tars) {
				assertNotEquals(t, living);
			}
		}
	}
	
	@Test // when we miss, we actually miss
	public void missedShot() {
		// get the angles at which all targets from the list should be destroyed at
		ArrayList<Target> tars = game.getTargets();
		double[][] hitRanges = new double[tars.size()][2];
		for(int i = 0; i < tars.size(); i++) {
			Target t = tars.get(i);
			double minHit = t.getMinHitAngle();
			double maxHit = t.getMaxHitAngle();
			hitRanges[i][0] = minHit;
			hitRanges[i][1] = maxHit;
		}
		Player p = game.getPlayer();
		Random rand = new Random();
		// set the barrel angle to anything but these
		for(int i = 0; i < 100; i++) {
			// test 100 missed shots: make sure no target was hit
			// HOW DO WE IMPLEMENT THIS? Is it better to test visually?
		}
		fail("Not Yet Testing Anything Useful");
	}
	
	/*
	 * Ideas for later tests:
	 * 	- Challenge Target is not behind another target
	 *  - All targets reachable by available angles
	 * 
	 * 
	 * 
	 */
	
	@Test // target contain method returns expected boolean
	public void targetContainsPoint(){
		Target tar = new RectangleTarget(0, 0, 5, 5);	
		// Square is easiest target to test contains for, should add other Target implementations as we go
		Point inside = new Point(2,3);
		Point outside = new Point(8, 8);
		assertTrue(tar.contains(inside));
		assertFalse(tar.contains(outside));
		
		// tar = new OvalTarget(......
		// tar = new PolygonTarget(.....
	}
	
	@Test	// that targets do not spawn over each other
	public void twoTargetsContain() {
		Target tarOne = new RectangleTarget(0, 0, 10, 10);
		Target tarTwo = new RectangleTarget(5,5,15,15);
		assertTrue(tarOne.contains(tarTwo));
		
		tarTwo = new RectangleTarget(25,25,5,5);
		assertFalse(tarOne.contains(tarTwo));
	}
	
	@Test	// that when barrelAngle is set, it changes to appropriate angle (including the gui coords)
	public void correctBarrelAngle() {
		Player p = new Player();
		p.setBarrelAngle(0);
		double angle = p.checkAngle();
		assertEquals(angle, 0, 0.75);
		
		p.setBarrelAngle(30);
		angle = p.checkAngle();
		assertEquals(angle, 30, 0.75);
		
		p.setBarrelAngle(60);
		angle = p.checkAngle();
		assertEquals(angle, 60, 0.75);
		
		p.setBarrelAngle(120);
		angle = p.checkAngle();
		assertEquals(angle, 120, 0.75);
		
		p.setBarrelAngle(23);
		angle = p.checkAngle();
		assertEquals(angle, 23, 0.75);
	}
	
	@Test // that no targets overlap
	public void noTargetsOverlap() {
		ArrayList<Target> targets = game.getTargets();
		Target iTar, jTar;
		// For all targets in the list, make sure no targets after it in the list overlap
		for(int i = 0; i < targets.size(); i++) {
			iTar = targets.get(i);
			for(int j = i + 1; j < targets.size(); j++) {
				jTar = targets.get(j);
				assertFalse(iTar.contains(jTar));
			}
		}
	}
	
	/*
	 * 		Decide that these are probably unnecessary tests
	 * 
	
	@Test // that targets spawn in desired panel/area 
	public void targetsOnScreen() {
		fail("Not yet implemented");
	}
	
	@Test	// that targets do not spawn on tank/HUD
	public void noTargetsOnOtherComponents() {
		fail("Not yet implemented");
	} 
	
	*
	*
	*/
}

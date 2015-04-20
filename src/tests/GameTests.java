package tests;



import static org.junit.Assert.*;

import game.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GameTests {

	private Game game; 
	
	@Before	// initialize a Game with a list of targets
	public void setUp() {
		game = new Game();
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
		int angle = p.checkAngle();
		assertEquals(angle, 0);
		
		p.setBarrelAngle(30);
		angle = p.checkAngle();
		assertEquals(angle, 30);
		
		p.setBarrelAngle(60);
		angle = p.checkAngle();
		assertEquals(angle, 60);
		
		p.setBarrelAngle(120);
		angle = p.checkAngle();
		assertEquals(angle, 120);
		
		p.setBarrelAngle(23);
		angle = p.checkAngle();
		assertEquals(angle, 23);
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

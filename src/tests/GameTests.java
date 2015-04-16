package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTests {

	@Before	// initialize a Game with a list of targets
	public void setUp() {
		
	}

	@Test // target contain method returns expected boolean
	public void targetContains(){
		fail("Not yet implemented");
	}
	
	@Test // that targets spawn in desired panel/area 
	public void targetsOnScreen() {
		fail("Not yet implemented");
	}
	
	@Test	// that targets do not spawn over each other
	public void noTargetsOverlap() {
		fail("Not yet implemented");
	}
	
	@Test	// that targets do not spawn on tank/HUD
	public void noTargetsOnOtherComponents() {
		fail("Not yet implemented");
	}

	@Test	// that when barrelAngle is set, it changes to appropriate angle (including the gui coords)
	public void correctBarrelAngle() {
		fail("Not yet implemented");
	}
}

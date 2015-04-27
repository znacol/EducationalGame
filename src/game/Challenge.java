package game;

import java.util.Random;

public class Challenge {
	private String challenges[] = {"Hit with an obtuse angle!", "Hit with an acute angle!"};
	private String currentChal;

	// If game has certain number of targets left, randomly generate challenge question.
	public String getChallenge() {
		Random rand = new Random();
		int index = rand.nextInt(challenges.length);
		currentChal = challenges[index];
		return currentChal;
	}

	// Check if player actually completed challenge (only 2 challenges at the moment).
	public boolean checkChallenge(Player player) {
		switch(currentChal) {
		case "Hit with an obtuse angle!":
			return player.getAngle() > 90;
		case "Hit with an acute angle!":
			return player.getAngle() < 90;
		default:
			return false;
		}
	}
}

package game;

import java.util.Random;

public class Challenge {
	private String challenges[] = {"Hit with an obtuse angle!", "Shoot with an acute angle!"};
	private String currentChal;

	// If game has certain number of targets left, randomly generate challenge question.
	public String isChallengeTurn(int numTarsLeft) {
		if (numTarsLeft % 2 == 0) {
			Random rand = new Random();
			int index = rand.nextInt(challenges.length);
			currentChal = challenges[index];
			return currentChal;
		}
		return "";
	}

	// Check if player actually completed challenge (only 2 challenges at the moment).
	public boolean checkChallenge(Player player) {
		boolean check = false;
		switch(currentChal) {
		case "Hit with an obtuse angle!":
			if(player.getAngle() > 90) check = true;
			break;
		case "Shoot with an acute angle!":
			if(player.getAngle() < 90) check = true;
			break;
		default:
			break;
		}
		return check;
	}
}

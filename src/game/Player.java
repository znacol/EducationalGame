package game;

public class Player {
	private int score;
	private int barrelAngle;

	public void setBarrelAngle(int barrelAngle) {
		this.barrelAngle = barrelAngle;
	}

	public int checkAngle() {return barrelAngle;}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){return score;}

}

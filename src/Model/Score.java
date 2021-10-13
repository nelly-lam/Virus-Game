package Model;

public class Score {
	
	private int currentScore;
	private int bestScore;
	
	public Score() {
		currentScore = 0;
		bestScore = 0;
	}

	public int getBestScore() { return bestScore; }
	public void setBestScore(int bestScore) { this.bestScore = bestScore; }
	public int getCurrentScore() { return currentScore; }
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	

}

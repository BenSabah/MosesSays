import java.util.Random;

public class MosesSaysEngine {

	// Game variables.
	private StringBuilder Sequence;
	private int numberOfOptions;
	private Random rnd;
	private boolean isGameOver;
	private boolean hasGuessedLast;

	public MosesSaysEngine(int numberOfOptions) {
		this.numberOfOptions = numberOfOptions;
		Sequence = new StringBuilder();
		rnd = new Random();
		hasGuessedLast = true;
		isGameOver = false;
		rollNext();
	}

	public void rollNext() {
		if (hasGuessedLast) {
			Sequence.append(rnd.nextInt(numberOfOptions) + 1);
			hasGuessedLast = false;
		}
	}

	public String getSequence() {
		return Sequence.toString();
	}

	public boolean checkSequenceSoFar(StringBuilder progress) {
		if (isGameOver) {
			return false;
		}

		if (progress.length() > Sequence.length()) {
			isGameOver = true;
			return false;
		}

		for (int i = 0; i < progress.length(); i++) {
			if (Sequence.charAt(i) != progress.charAt(i)) {
				isGameOver = true;
				return false;
			}
		}
		hasGuessedLast = true;
		return true;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public int getSizeOfSequence() {
		return Sequence.length();
	}

	public void resetGame() {
		Sequence.setLength(0);
		hasGuessedLast = true;
		isGameOver = false;
		rollNext();
	}
}

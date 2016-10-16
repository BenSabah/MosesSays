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
		rnd = new Random();
		Sequence = new StringBuilder();
		resetGame();
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
		return checkSequence(progress, progress.length());
	}

	public boolean checkSequenceFull(StringBuilder progress) {
		return checkSequence(progress, Sequence.length());
	}

	private boolean checkSequence(StringBuilder progress, int untilIndex) {
		if (isGameOver) {
			return false;
		}

		if (progress.length() > Sequence.length()) {
			isGameOver = true;
			return false;
		}

		if (progress.length() < untilIndex) {
			isGameOver = true;
			return false;
		}

		for (int i = 0; i < untilIndex; i++) {
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

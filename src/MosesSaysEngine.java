import java.util.Random;

public class MosesSaysEngine {

	// Game variables.
	private StringBuilder history;
	private int numberOfOptions;
	private Random rnd;
	private boolean isGameOver;
	private boolean hasGuessedLast;

	public MosesSaysEngine(int numberOfOptions) {
		history = new StringBuilder();
		rnd = new Random();
		hasGuessedLast = true;
		isGameOver = false;
		rollNext();
	}

	public void rollNext() {
		if (hasGuessedLast) {
			history.append(rnd.nextInt(numberOfOptions));
			hasGuessedLast = false;
		}
	}

	public String getSequence() {
		return history.toString();
	}

	private boolean checkValue(int index, char value) {
		try {
			return (history.charAt(index) == value) ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean checkSequence(String str) {
		if (str.length() != history.length()) {
			isGameOver = true;
			return false;
		}

		for (int i = 0; i < history.length(); i++) {
			if (!checkValue(i, str.charAt(i))) {
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
}

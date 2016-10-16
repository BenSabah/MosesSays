import java.util.Scanner;

public class MosesSaysAscii {
	public static void main(String[] args) {

		int options = 4; // TODO get from args.

		MosesSaysEngine engine = new MosesSaysEngine(options);
		Scanner input = new Scanner(System.in);
		StringBuilder in = new StringBuilder();
		boolean beingNewGame = true;

		do {
			while (!engine.isGameOver()) {
				in.setLength(0);
//				engine.rollNext();
				System.out.println("enter the following: " + engine.getSequence());
				in.append(input.nextLine().trim());
				engine.checkSequenceFull(in);
			}

			System.out.println("Game Over !, would you like to play again ? (type \"n\" to exit)");
			String answer = input.nextLine().trim();

			beingNewGame = true;
			if (answer.equals("n")) {
				beingNewGame = false;
			}
		} while (beingNewGame);
		input.close();
	}
}

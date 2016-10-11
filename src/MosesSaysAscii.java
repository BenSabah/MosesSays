import java.util.Scanner;

public class MosesSaysAscii {
	public static void main(String[] args) {

		int options = 4; // TODO get from args.

		MosesSaysEngine engine = new MosesSaysEngine(options);
		Scanner input = new Scanner(System.in);
		String in;

		while (!engine.isGameOver()) {
			engine.rollNext();
			System.out.println("enter the following: " + engine.getSequence());
			in = input.nextLine().trim();
			// engine.checkSequenceSoFar(in);
		}
		input.close();
		System.out.println("Game Over !");

	}
}

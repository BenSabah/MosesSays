import java.util.Scanner;

public class MosesSaysAscii {
	public static void main(String[] args) {

		int options = 4; // TODO get from args.

		MosesSaysEngine game = new MosesSaysEngine(options);
		Scanner input = new Scanner(System.in);
		String in;

		while (!game.isGameOver()) {
			game.rollNext();
			System.out.println("enter the following: " + game.getSequence());
			in = input.nextLine().trim();
			game.checkSequence(in);
		}
		input.close();
		System.out.println("Game Over !");

	}
}

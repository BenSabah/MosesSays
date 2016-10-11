import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	// Dimension screenSize = new Dimension(Utils.getScreenWidth() / 3, Utils.getsScreenHeight() / 3);
	static Dimension screenSize = new Dimension(300, 300);

	public static void main(String[] args) {
		// The number of buttons.
		int howManyButtons = 4;
		Dimension xy = Utils.getBestButtonsArrangments(screenSize, howManyButtons);
		xy = new Dimension(2, 2); // TODO fix this ^^^

		// Setting up the looks.
		Utils.setSystemStyle();

		// Starting and setting the game.
		MosesSaysGui game = new MosesSaysGui(xy.height, xy.width);
		game.setSize(screenSize);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("Moses Says");
		game.centerWindow();
		game.setVisible(true);
		game.startGame();
	}
}

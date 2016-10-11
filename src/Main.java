import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// Setting up the looks.
		Utils.setSystemStyle();

		// Starting and setting the game.
		MosesSaysGui game = new MosesSaysGui(7, new Dimension(500, 500));
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("Moses Says");
		game.centerWindow();
		game.setVisible(true);
		game.startGame();
	}
}

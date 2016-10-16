import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// Setting up the looks.
		Utils.setSystemStyle();

		// Starting and setting the game.
		MosesSaysGui game = new MosesSaysGui(6, new Dimension(600, 400));
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("Moses Says");
		game.centerWindow();
		game.setVisible(true);
		game.startGame();
	}
}

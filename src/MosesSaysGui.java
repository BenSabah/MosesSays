import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MosesSaysGui extends JFrame {
	private static final long serialVersionUID = 1026073012531573465L;

	// Timing & volume Variables.
	final static int SOUND_TIME = 500;
	final static long WAIT_TIME_AFTER_GOOD_GUESS = 2000;
	final static long WAIT_TIME_BEFORE_NEW_GAME = 1000;
	final static double SOUND_VOLUME = 1.0;

	// Game variables.
	StringBuilder input = new StringBuilder();;
	MosesSaysEngine engine;

	// Game panel setup.
	JPanel gamePanel;
	Font gameButtonFont = new Font("Tahoma", Font.BOLD, 50);
	Vector<myJButton> buttons = new Vector<myJButton>();

	// Reset panel Setup.
	JPanel resetPanel;
	Font resetButtonFont = new Font("Tahoma", Font.BOLD, 85);
	myJButton resetButton;

	int howManyButtons;
	int numButtonsHor, numButtonsVer;

	public MosesSaysGui(int howManyButtons, Dimension dim) {
		// Calculate the grid layout.
		this.setSize(dim);
		this.howManyButtons = howManyButtons;
		Dimension t = Utils.getBestButtonsArrangment(dim, howManyButtons);
		numButtonsHor = t.width;
		numButtonsVer = t.height;

		// Starting a new game.
		engine = new MosesSaysEngine(howManyButtons);

		// Setup the panels.
		setupResetPanel();
		setupGamePanel();

		// adding the game panel to the frame.
		this.add(gamePanel);
	}

	private void setupResetPanel() {
		// Setting the grid layout;
		resetPanel = new JPanel();
		resetPanel.setLayout(new GridLayout(1, 1));

		// Adding the reset button.
		int borderSize = Utils.calcBorderSize(getSize(), 1, 1);
		resetButton = new myJButton(0, null, resetButtonFont, borderSize);
		resetButton.setText("<html><center><h1>game over !</h1><h3>click to RESET</h3><center></html>");
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				switchPanels(resetPanel, gamePanel);
				validate();
				repaint();
				startGame();
			}
		});
		resetPanel.add(resetButton);
	}

	private void setupGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(numButtonsVer, numButtonsHor));
		int borderSize = Utils.calcBorderSize(getSize(), numButtonsHor, numButtonsVer);

		for (int i = 1; i <= howManyButtons; i++) {
			myJButton b = new myJButton(i, null, gameButtonFont, borderSize);
			b.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					// first activate the button and get its value.
					activateButton(b);

					// Get & check the latest input.
					input.append(b.getText());
					engine.checkSequenceSoFar(input);

					// Check if game over.
					if (engine.isGameOver()) {
						switchPanels(gamePanel, resetPanel);
						engine.resetGame();
						input.setLength(0);

						// If got the same number of characters
					} else if (input.length() == engine.getSizeOfSequence()) {
						Utils.pauseTime(WAIT_TIME_AFTER_GOOD_GUESS);
						input.setLength(0);
						engine.rollNext();
						doNextRound();
					} else {
						// do nothing, input not yet complete.
					}
				}
			});

			buttons.addElement(b);
			gamePanel.add(b);
		}
	}

	public synchronized void activateButton(myJButton b) {
		Thread t1 = new Thread(new Runnable()
		{
			public void run() {
				b.setBrighter();
				b.validate();
				b.repaint();
				validate();
				repaint();
				Utils.soundTone(100 * (1 + b.id), SOUND_TIME, SOUND_VOLUME);
				b.setRegular();
				b.validate();
				b.repaint();
				validate();
				repaint();
			}
		});
		t1.start();
	}

	private void switchPanels(JPanel oldPanel, JPanel newPanel) {
		remove(oldPanel);
		add(newPanel);
		validate();
		repaint();
	}

	private void doNextRound() {
		String s = engine.getSequence();

		for (int i = 0; i < s.length(); i++) {
			int buttonValue = s.charAt(i) - '0';
			myJButton b = buttons.get(buttonValue - 1);
			activateButton(b);
			Utils.pauseTime(SOUND_TIME);
		}
	}

	public void centerWindow() {
		setLocation((Utils.getScreenWidth() - getWidth()) / 2, (Utils.getScreenHeight() - getHeight()) / 2);
	}

	public void startGame() {
		Thread t = new Thread(new Runnable()
		{
			public void run() {
				Utils.pauseTime(WAIT_TIME_BEFORE_NEW_GAME);
				doNextRound();
			}
		});
		t.start();
	}

	class myJButton extends JButton {
		private static final long serialVersionUID = 3016883932249096163L;
		Color original, brighter, darker;
		int id;

		public myJButton(int id, Color color, Font font, int borderSize) {
			this.id = id;
			if (color == null) {
				original = Utils.getRandomColor().brighter();
			}
			brighter = original.brighter();
			darker = original.darker();
			setBorder(new LineBorder(darker, borderSize, true));
			setForeground(Utils.getContrastColor(original));
			setContentAreaFilled(false);
			setOpaque(true);
			setFont(font);
			setText(id + "");
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setBackground(original);
		}

		public synchronized void setBrighter() {
			setBackground(brighter);
		}

		public synchronized void setRegular() {
			setBackground(original);
		}
	}
}

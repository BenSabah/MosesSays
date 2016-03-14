import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class MosesSaysGui extends JFrame {

	public static void main(String[] args) {
		int options = 4; // TODO get from args.
		MosesSaysGui gameG = new MosesSaysGui(options);
	}

	// Window variables.
	private static final long serialVersionUID = 1026073012531573465L;
	// Dimension screenSize = new Dimension(430, 430);
	Dimension screenSize = new Dimension(GuiUtils.getScreenWidth() / 2, GuiUtils.getsScreenHeight() / 2);

	JPanel panel = new JPanel();

	Vector<JButton> buttons = new Vector<JButton>();;
	private int numberOfOptions;
	double spacer = 10;

	public MosesSaysGui(int numberOfOptions) {

		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);

		this.numberOfOptions = numberOfOptions;
		// Starting the window.
		GuiUtils.setSystemStyle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSize);
		setLocation((GuiUtils.getScreenWidth() - getWidth()) / 2, (GuiUtils.getsScreenHeight() - getHeight()) / 2);
		setResizable(false);
		setTitle("Moses Says");
		setVisible(true);

		// Dimension xy = getBestButtonsArrangments(screenSize, numberOfOptions);
		Dimension xy = new Dimension(4, 8);

		Dimension dim = getContentPane().getSize();

		double buttonWidth = (dim.width - ((xy.width + 1) * spacer)) / xy.width;
		double buttonHeight = (dim.height - ((xy.height + 1) * spacer)) / xy.height;

		for (int y = 0; y < xy.height; y++) {
			for (int x = 0; x < xy.width; x++) {

				JButton b = new JButton();
				b.setText(y * xy.width + x + 1 + "");
				b.setSize((int) buttonWidth, (int) buttonHeight);
				b.setLocation((int) (x * (spacer + buttonWidth) + spacer),
						(int) (y * (spacer + buttonHeight) + spacer));
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
				b.setBorder(new CompoundBorder(null, new EmptyBorder(0, 0, 0, 0)));
				// b.setEnabled(false);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.print(b.getText() + ",");
					}
				});
				add(b);
				buttons.addElement(b);
			}
		}
		panel.setLocation(0, 0);
		panel.setEnabled(true);
		// add(panel);
		// panel.repaint();
		// this.repaint();
		// this.pack();
	}

	public static Dimension getBestButtonsArrangments(Dimension dim, int howManyButtons) {
		int upDown = dim.height;
		int leftRight = dim.width;

		return new Dimension(howManyButtons, 1);
	}
}

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

/**
 * This class handles all the GUI of the program that we want to display to the user while running the CryptoChat
 * program.
 * 
 * Happy cow says: "Muuuuuuu.."
 * 
 * @author Ben Sabah.
 */

class Utils {

	/**
	 * Calling this method tries to change the style of the JFrame that invokes it to the current system style.
	 */
	static void setSystemStyle() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
	}

	/**
	 * This method returns the working screen width.
	 * 
	 * @return The width of the working screen.
	 */
	static int getScreenWidth() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	/**
	 * This method returns the working screen height.
	 * 
	 * @return The height of the working screen.
	 */
	static int getsScreenHeight() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	static void tone(int hz, int msecs, double vol) {
		try {
			byte[] buf = new byte[1];
			AudioFormat af = new AudioFormat(7000f, 8, 1, true, false);
			SourceDataLine sdl;
			sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
			for (int i = 0; i < msecs * 8; i++) {
				double angle = i / (7000f / hz) * 2.0 * Math.PI;
				buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
				sdl.write(buf, 0, 1);
			}
			sdl.drain();
			sdl.stop();
			sdl.close();
		} catch (LineUnavailableException e) {
		}
	}

	/**
	 * Calling this method produce a system beep.
	 */
	static void doBeep() {
		Toolkit.getDefaultToolkit().beep();
	}

	/**
	 * This Class handle all the pop-up messages.
	 */
	static class PopUpMessages {
		static final int EMPTY = -1;
		static final int FAIL = 0;
		static final int SUCCESS = 1;
		static final int ERROR = 2;
		static final int QUESTION = 3;

		/**
		 * Calling this method produce a popup window with the given title, message and symbol.
		 * 
		 * @param title
		 *            The title of the popup window.
		 * @param msg
		 *            The message inside the popup window.
		 * @param type
		 *            the type of popup (the icon that appear in the popup window).
		 */
		public static void popupMsg(String title, String msg, int type) {
			doBeep();
			JOptionPane.showMessageDialog(null, msg, title, type);
		}

		/**
		 * launching a specific popup window (error popup).
		 * 
		 * @param msg
		 *            The message we want to display in the popup window.
		 */
		public static void errorMsg(String msg) {
			popupMsg("ERROR:", msg, ERROR);
		}
	}

	/**
	 * launching a specific popup window (error popup).
	 * 
	 * @param msg
	 *            The message we want to display in the popup window.
	 */
	static Color getRandomColor() {
		return new Color((int) (Math.random() * Integer.MAX_VALUE));
	}

	static String HTMLSize(String str, int size) {
		return "<html><font size=" + size + ">" + str + "</font></html>";
	}

	static String removeHTMLTags(String str) {
		return str.replaceAll("\\<.*?>", "");
	}

	static Dimension getBestButtonsArrangments(Dimension dim, int howManyButtons) {
		return null;
	}

	public static void pauseTime(long miliSeconds) {
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e1) {
		}
	}

	public static Color getOppositeColor(Color color) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();

		return new Color(255 - r, 255 - g, 255 - b);
	}
}
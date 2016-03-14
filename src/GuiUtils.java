import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import java.awt.Toolkit;
import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

/**
 * This class handles all the GUI of the program that we want to display to the user while running the CryptoChat
 * program.
 * 
 * Happy cow says: "Muuuuuuu.."
 * 
 * @author Ben Sabah.
 */

class GuiUtils {

	/**
	 * Calling this method opens the file selector window, and returns the selected file as a File object.
	 * 
	 * @return The File object of the selected file.
	 */
	static File fileSelector() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		return fc.getSelectedFile();
	}

	/**
	 * Calling this method opens the file selector window, and returns the selected path of the selected folder as a
	 * File object.
	 * 
	 * @return The File object of the selected folder.
	 */
	static File folderSelector() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Please select a folder");
		fc.setCurrentDirectory(new File(getDesktopPath()));

		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}

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

	/**
	 * This method returns the system desktop path.
	 * 
	 * @return The path to the system's desktop.
	 */
	static String getDesktopPath() {
		return FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
	}

	/**
	 * Calling this method produce a system beep.
	 */
	static void doBeep() {
		Toolkit.getDefaultToolkit().beep();
	}

	/**
	 * This method opens a give path (file or folder) with its default application.
	 * 
	 * @param path
	 *            The path of the file\folder we wish to open.
	 * @throws IOException
	 *             Thrown when there is any issue opening the given path.
	 */
	static void openPath(String path) throws IOException {
		Desktop.getDesktop().open(new File(path));
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
	 * Throwable custom Exception if GUI related exceptions present themselves.
	 */
	@SuppressWarnings("serial")
	static class GuiException extends Exception {
		public GuiException() {
			super();
		}
	}

	public final static class ClipboardHandler implements ClipboardOwner {

		public void lostOwnership(Clipboard aClipboard, Transferable aContents) {
		}

		/**
		 * Add the given string to the Clip-board, just as pressing CTRL+C.
		 * 
		 * @param str
		 *            The string we want to copy to the Clip-board.
		 */
		public void setClipboardContents(String str) {
			StringSelection stringSelection = new StringSelection(str);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, this);
		}

		/**
		 * Get the String residing on the clip-board.
		 *
		 * @return any text found on the Clip-board; if none found, return an empty String.
		 * @throws GuiException
		 *             Thrown when can't access the Clip-board
		 * 
		 */
		public String getClipboardContents() throws GuiException {
			String result = "";
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(null);
			boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
			if (hasTransferableText) {
				try {
					result = (String) contents.getTransferData(DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException | IOException e) {
					throw new GuiException();
				}
			}
			return result;
		}
	}
}
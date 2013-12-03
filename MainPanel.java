import java.awt.Color;

import javax.swing.JPanel;

/**
 * This class creates the JPanel for the User Interface of the Union Find.
 * 
 * @author jshmtthwclrk
 * @version 1.0		9 November 2013
 */
public class MainPanel {
	/** Panel that displays the 2-D array */
	JPanel main = new JPanel();
	/** Background color */
	Color bg = new Color(218,165,32);
	
	/**
	 * Constructor that sets up the Main Panel for Union Find Visualization.
	 */
	public MainPanel() {
		main.setSize(600, 600);
		main.setBackground(bg);
		main.setVisible(true);
	} // end constructor
} // end class MainPanel

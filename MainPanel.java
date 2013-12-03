import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class MainPanel extends JFrame {
	
	/** Panel that contains all the separate panels */
	public JFrame frame = new JFrame();
	public JPanel main = new JPanel();
	/** Panel that displays the 2-D array */
	private JPanel grid = new JPanel();
	/** Dis Panel does da choosin */
	private JPanel radioBox = new JPanel();
	/** The Side Panel of Information!!! */
	private JPanel side = new JPanel();
	/** Background color */
	private Color GOLD = new Color(218,165,32);
	Grid grd = new Grid();
	
	public JPanel[][] pArray;
	
	// User Interaction Panel
	private JRadioButton union = new JRadioButton("Union Find");
	private JRadioButton qUnion = new JRadioButton("Quick Union Find");
	private JButton execute = new JButton("Execute");
	private JLabel sizeLabel = new JLabel("Enter size:");
	private JTextField sizeText = new JTextField("", 10);
	
	// Side Panel Information
	private JLabel select = new JLabel("Selection: ");
	private String sizeString;
	private JLabel currSize = new JLabel("Size: ");
	private int theSize = 0;
	
	public WeightedCompressionQuickUnion qf;
	public QuickFind uf;

	public Percolation<DynamicConnectivity> perk;
	
	// Timer Stuff
	//private javax.swing.Timer timer = new Timer(1, this);
	private int timerCount = 0;
	private JLabel timerLabel = new JLabel("Timer: " + timerCount + " ms");
	
	/** Main method */
	public static void main(String [] args) {
		@SuppressWarnings("unused")
		MainPanel mainPanel = new MainPanel();
	} // end main method
	
	/**
	 * Constructor that sets up the Main Panel for Union Find Visualization.
	 */
	public MainPanel() {
		frame.setSize(800, 700);
		frame.setResizable(false);
		frame.setTitle("Union Find");
		
		main.setSize(800,700);
		main.setLayout(null);
		
		@SuppressWarnings("unused")
		RadioBox rdobx = new RadioBox();
		@SuppressWarnings("unused")
		SidePanel sdpnl = new SidePanel();
		
		main.add(grid);
		main.add(radioBox);
		main.add(side);
		
		frame.add(main);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // end constructor
	

	private class Grid {
		
		/**
		 * Constructor that sets up the Grid Panel.
		 */
		public Grid() {
			
			GridLayout gridGrid = new GridLayout();
			grid.setSize(600, 600);
			grid.setLayout(gridGrid);
			grid.setBackground(GOLD);
			grid.setLocation(0, 0);
			
			grid.setVisible(true);
		} // end constructor
		
		private void initalizeGrid(int size) {
			
			pArray = new JPanel[size][size];
			JPanel swagPanel = new JPanel();
			grid.removeAll();
			grid.updateUI();
			grid.setLayout(new GridLayout());
			swagPanel.setBackground(GOLD);
			swagPanel.setSize(600, 600);
			GridLayout tileGrid = new GridLayout(size, size, 1, 1);
			tileGrid.minimumLayoutSize(swagPanel);
			swagPanel.setLayout(tileGrid);
			swagPanel.setVisible(true);
			
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					JPanel panel = new JPanel(/*new GridBagLayout()*/);
					panel.setBackground(Color.DARK_GRAY);
					//panel.setSize(new Dimension((600/size), (600/size)));
					panel.setVisible(true);
					swagPanel.add(panel);
					pArray[row][col] = panel;
				} // end nested for loop
			} // end for loop
			grid.add(swagPanel);
		} // end method initalizeGrid(int)
		
		private void updateGrid() {
			// random number stuff
			int randRow = (int)(Math.random() * ((theSize - 0) + 1));
			int randCol = (int)(Math.random() * ((theSize - 0) + 1));
			
			// if(! .isOpen) .open; else updateGrid();
			if(!perk.isOpen(randRow, randCol)) {
				perk.open(randRow, randCol);
				pArray[randRow][randCol].setBackground(Color.BLUE);
			}
			else updateGrid();
		}
		
	} // end inner class Grid
	
	/**
	 * Private Inner Class for User Interaction Panel
	 * @author Joshua Clark
	 *
	 */
	private class RadioBox {
		
		/**
		 * Constructor
		 */
		public RadioBox() {
			
			/** Panel that handles interactions with user. */
			JPanel subPanel = new JPanel();
			
			radioBox.setSize(600, 100);
			radioBox.setBackground(Color.LIGHT_GRAY);
			radioBox.setLocation(0, 600);
			radioBox.setLayout(new BorderLayout());
			
			union.setSelected(true);
			
			execute.addActionListener(new ActionListener() {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public void actionPerformed(ActionEvent e) {
					sizeString = sizeText.getText();
					if(isNumeric(sizeString)) {
						execute.setEnabled(false);
						currSize.setText("Size: " + sizeString);
						theSize = Integer.parseInt(sizeString);
						grd.initalizeGrid(theSize);
						if(union.isSelected()) {
							select.setText("Selection: Union");
							uf = new QuickFind(theSize * theSize + 2);
							perk = new Percolation(uf, theSize);
						} else if(qUnion.isSelected()) {
							select.setText("Selection: Quick Union");
							qf = new WeightedCompressionQuickUnion(theSize * theSize + 2);
							perk = new Percolation(qf, theSize);
						}
						while(!perk.percolates()) grd.updateGrid();
						timerLabel.setText("Timer: " + perk.calculateTimeTaken() + " ms");
						repaint();
					} else {
						JOptionPane.showMessageDialog(frame, 
								"Please enter a valid number and press Execute",
								"Invalid Input", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
			JPanel north = new JPanel();
			north.setPreferredSize(new Dimension(600,20));
			
			ButtonGroup bGroup = new ButtonGroup();
			bGroup.add(union);
			bGroup.add(qUnion);
			subPanel.add(union);
			subPanel.add(qUnion);
			subPanel.add(sizeLabel);
			subPanel.add(sizeText);
			subPanel.add(execute);
			
			radioBox.add(north, BorderLayout.NORTH);
			radioBox.add(subPanel, BorderLayout.CENTER);
			
			radioBox.setVisible(true);
		} // end constructor
		
		/**
		 * Takes in a string and returns a boolean response depending upon
		 * whether or not the string is a number.
		 * @param str
		 * @return
		 */
		public boolean isNumeric (String str) {
			return str.matches("\\d+");
		} // end method isNumeric(String)
	} // end inner class RadioBox
	
	/**
	 * Private Inner Class for Side Information Panel
	 * @author Joshua Clark
	 *
	 */
	private class SidePanel {
		
		/**
		 * Constructor
		 */
		public SidePanel() {
			side.setSize(200, 700);
			side.setLocation(601, 0);
			side.setBackground(Color.CYAN);
			
			side.add(timerLabel);
			side.add(select);
			side.add(currSize);
		} // end constructor
	} // end inner class SidePanel
	
} // end class

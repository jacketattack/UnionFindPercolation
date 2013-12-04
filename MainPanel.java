import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * The Grand-Daddy of them all!
 * 
 * @author jshmtthwclrk
 *
 */
public class MainPanel extends JFrame {
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = -7236160691262901215L;
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
	private Grid grd = new Grid();
	private JPanel swagPanel = new JPanel();
	
	public JPanel[][] pArray;
	
	// User Interaction Panel
	private JRadioButton union = new JRadioButton("Union Find");
	private JRadioButton qUnion = new JRadioButton("Quick Union Find");
	private JButton execute = new JButton("Execute");
	private JLabel gridSizeLabel = new JLabel("Enter size:");
	private JTextField gridSizeText = new JTextField("", 5);
	private JLabel numRunsLabel = new JLabel("Number of runs:");
	private JTextField numRunsText = new JTextField("1", 5);
	private String numRunsString;
	
	// Side Panel Information
	private JLabel select = new JLabel("Selection: ");
	private String sizeString;
	private JLabel currSize = new JLabel("Grid Size: ");
	private int theSize = 0;
	private JLabel blank1 = new JLabel(" ");
	private JLabel blank2 = new JLabel(" ");
	private JLabel blank3 = new JLabel(" ");
	private JLabel blank4 = new JLabel(" ");
	
	public WeightedCompressionQuickUnion qf;
	public QuickFind uf;
	
	private int randRow;
	private int randCol;

	public Percolation<DynamicConnectivity> perk;
	
	private JLabel percent = new JLabel("Last Percent open: 0%");
	private DecimalFormat df = new DecimalFormat("#.000");
	
	private JLabel avgPercent = new JLabel("Avg Percent open: 0%");
	private JLabel avgTime = new JLabel("Avg Run Time: ");
	private JLabel runs = new JLabel("Number of Runs: ");
	private double avgPercentNum;
	private long avgTimeNum;
	
	// Timer Stuff
	//private javax.swing.Timer timer = new Timer(1, this);
	private int timerCount = 0;
	private JLabel timerLabel = new JLabel("Last Run Time: " + timerCount + " ms");
	
	//Build Percent List
	private String[] percentList;
	private JLabel percentListTitle = new JLabel("");
	private JLabel percentListLabel0 = new JLabel("");
	private JLabel percentListLabel1 = new JLabel("");
	private JLabel percentListLabel2 = new JLabel("");
	private JLabel percentListLabel3 = new JLabel("");
	private JLabel percentListLabel4 = new JLabel("");
	private JLabel percentListLabel5 = new JLabel("");
	private JLabel percentListLabel6 = new JLabel("");
	private JLabel percentListLabel7 = new JLabel("");
	private JLabel percentListLabel8 = new JLabel("");
	private JLabel percentListLabel9 = new JLabel("");
	
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
			grid.setBackground(Color.DARK_GRAY);
			grid.setLocation(0, 0);
			
			grid.setVisible(true);
		} // end constructor
		
		private void initalizeGrid(int size) {
			
			pArray = new JPanel[size][size];
			
			grid.removeAll();
			grid.updateUI();
			swagPanel.removeAll();
			swagPanel.updateUI();
			grid.setLayout(new GridLayout());
			swagPanel.setBackground(GOLD);
			swagPanel.setSize(600, 600);
			GridLayout tileGrid = new GridLayout(size, size, 1, 1);
			tileGrid.minimumLayoutSize(swagPanel);
			swagPanel.setLayout(tileGrid);
			swagPanel.setVisible(true);
			
			percentListLabel0.setText("");
			percentListLabel1.setText("");
			percentListLabel2.setText("");
			percentListLabel3.setText("");
			percentListLabel4.setText("");
			percentListLabel5.setText("");
			percentListLabel6.setText("");
			percentListLabel7.setText("");
			percentListLabel8.setText("");
			percentListLabel9.setText("");
			
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
			randRow = (int)(Math.random() * ((theSize - 1) + 1));
			randCol = (int)(Math.random() * ((theSize - 1) + 1));
			
			// if(! .isOpen) .open; else updateGrid();
			if(perk.isOpen(randRow, randCol) == false) {
				perk.open(randRow, randCol);
				pArray[randRow][randCol].setBackground(Color.BLUE);
			}
			
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
			
			radioBox.setSize(800, 100);
			radioBox.setBackground(Color.LIGHT_GRAY);
			radioBox.setLocation(0, 600);
			radioBox.setLayout(new BorderLayout());
			
			union.setSelected(true);
			
			execute.addActionListener(new ActionListener() {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public void actionPerformed(ActionEvent e) {
					//swagPanel.setBackground(GOLD);
					sizeString = gridSizeText.getText();
					numRunsString = numRunsText.getText();
					if(isNumeric(sizeString) && Integer.parseInt(sizeString) <=60 
							&& Integer.parseInt(sizeString) >= 2 && isNumeric(numRunsString) 
							&& Integer.parseInt(numRunsString) >= 1) {
						int numRuns = Integer.parseInt(numRunsString);
						percentList = new String[numRuns];
						int i = 0;
						avgPercentNum = 0;
						avgTimeNum = 0;
						while(numRuns > 0) {
							execute.setEnabled(false);
							currSize.setText("Grid Size: " + sizeString + " x " + sizeString);
							theSize = Integer.parseInt(sizeString);
							grd.initalizeGrid(theSize);
							if(union.isSelected()) {
								select.setText("Selection: Union");
								uf = new QuickFind(theSize * theSize + 2);
								perk = new Percolation(uf, theSize);
								perk.startTimer();
							} else if(qUnion.isSelected()) {
								select.setText("Selection: Quick Union");
								qf = new WeightedCompressionQuickUnion(theSize * theSize + 2);
								perk = new Percolation(qf, theSize);
								perk.startTimer();
							}
							while(perk.percolates() == false) {
								grd.updateGrid();
							}
							perk.endTimer();
							avgPercentNum = ((perk.percentOn()) + avgPercentNum);
							String tempPercent = df.format(perk.percentOn() * 100);
							percentList[i] = tempPercent;
							avgTimeNum = (perk.calculateTimeTaken() + avgTimeNum);
							timerLabel.setText("Last Run Time: " + perk.calculateTimeTaken() + " ms");
							percent.setText("Last Percent open: " + df.format(perk.percentOn() * 100) + "%");
							execute.setEnabled(true);
							numRuns--;
							i++;
							//System.out.println(df.format(perk.percentOn() * 100));
							
						}
						avgPercentNum = avgPercentNum / Integer.parseInt(numRunsString);
						avgTimeNum = avgTimeNum / Integer.parseInt(numRunsString);
						avgPercent.setText("Avg Percent open: " + df.format(avgPercentNum * 100) + "%");
						avgTime.setText("Avg Run Time: " + avgTimeNum + " ms");
						runs.setText("Number of Runs: " + numRunsString);
						int k;
						if(Integer.parseInt(numRunsString) < 10){
							k = Integer.parseInt(numRunsString);
						}
						else {
							k=10;
						}
						
						for(int j = i-k; j<i; j++){
							if(j == i-k) percentListLabel0.setText("   " +percentList[i-k] + "%");
							if(j == i-k+1) percentListLabel1.setText("   " +percentList[i-k+1] + "%");
							if(j == i-k+2) percentListLabel2.setText("   " +percentList[i-k+2] + "%");
							if(j == i-k+3) percentListLabel3.setText("   " +percentList[i-k+3] + "%");
							if(j == i-k+4) percentListLabel4.setText("   " +percentList[i-k+4] + "%");
							if(j == i-k+5) percentListLabel5.setText("   " +percentList[i-k+5] + "%");
							if(j == i-k+6) percentListLabel6.setText("   " +percentList[i-k+6] + "%");
							if(j == i-k+7) percentListLabel7.setText("   " +percentList[i-k+7] + "%");
							if(j == i-k+8) percentListLabel8.setText("   " +percentList[i-k+8] + "%");
							if(j == i-k+9) percentListLabel9.setText("   " +percentList[i-k+9] + "%");
						}
						percentListTitle.setText("Last "+ k +" Percentages: ");
						repaint();
					} else {
						JOptionPane.showMessageDialog(frame, 
								"Please enter a valid number between 2 and 60, then press Execute again.",
								"Invalid Input", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
			JPanel north = new JPanel();
			north.setPreferredSize(new Dimension(800,20));
			
			ButtonGroup bGroup = new ButtonGroup();
			bGroup.add(union);
			bGroup.add(qUnion);
			subPanel.add(union);
			subPanel.add(qUnion);
			subPanel.add(gridSizeLabel);
			subPanel.add(gridSizeText);
			subPanel.add(numRunsLabel);
			subPanel.add(numRunsText);
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
			side.setSize(200, 600);
			side.setLocation(601, 0);
			side.setLayout(new BoxLayout(side, BoxLayout.PAGE_AXIS));
			//side.setBackground(Color.LIGHT_GRAY);
			//JPanel padding = new JPanel();
			//padding.setSize(5, 5);
			//side.add(padding);
			timerLabel.setFont(new Font("San-Serif", Font.PLAIN, 14));
			select.setFont(new Font("San-Serif", Font.PLAIN, 14));
			currSize.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percent.setFont(new Font("San-Serif", Font.PLAIN, 14));
			avgPercent.setFont(new Font("San-Serif", Font.PLAIN, 14));
			avgTime.setFont(new Font("San-Serif", Font.PLAIN, 14));
			runs.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel9.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel8.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel7.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel6.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel5.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel4.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel3.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel2.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel1.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListLabel0.setFont(new Font("San-Serif", Font.PLAIN, 14));
			percentListTitle.setFont(new Font("San-Serif", Font.PLAIN, 14));
			side.add(select);
			side.add(currSize);
			side.add(runs);
			side.add(blank1);
			side.add(timerLabel);
			side.add(avgTime);
			side.add(blank2);
			side.add(percent);
			side.add(avgPercent);
			side.add(blank3);
			side.add(percentListTitle);
			side.add(percentListLabel0);
			side.add(percentListLabel1);
			side.add(percentListLabel2);
			side.add(percentListLabel3);
			side.add(percentListLabel4);
			side.add(percentListLabel5);
			side.add(percentListLabel6);
			side.add(percentListLabel7);
			side.add(percentListLabel8);
			side.add(percentListLabel9);
			
		} // end constructor
	} // end inner class SidePanel
	
} // end class

/**
 * This class is the back-end for the Percolation problem 
 * we are attempting to solve with different implementations
 * of Union-Find.
 * 
 * It is generic also allowing us to test slower versions 
 * of Union-Find compared to better, faster versions.
 * 
 * @author trevor
 *
 * @param <T>
 */
public class Percolation<T extends DynamicConnectivity> {
	private T dynamicConnector;
	private boolean[][] grid;
	private int size;
	private int virtualTopNode;
	private int virtualBottomNode;
	
	/**
	 * Constructs a Percolation object. One note is that the
	 * dynamic connector passed in must be of size equal
	 * to (size * size) + 2. This is so we can have virtual top
	 * and bottom nodes.
	 * 
	 * @param dynamicConnector the union-find backing for percolation
	 * @param size - dimensions of grid
	 */
	public Percolation(T dynamicConnector, int size) {
		this.size = size;
		this.dynamicConnector = dynamicConnector;
		grid = new boolean[size][size];
		// these two nodes are used to check if any spot in top
		// row is connected to any spot in bottom row
		virtualTopNode = 0; // this will be connected to all "on" in top row
		virtualBottomNode = (size * size) + 1; // connected to all "on" in bottom row
	}
	
	public void open(int i, int j) {
		if ( i < 0 || i > size - 1 || j < 0 || j > size - 1) {
			throw new IndexOutOfBoundsException("i and/or j are out of bounds");
		} else {
			
			// first let's set this spot on our grid to "on"
			grid[i][j] = true;
			// now we must union it with all of its orthagonal
			// neighbors who are also "on"
			
			if ( i == 0) { // on top row, so connect to top "virtual node"
				dynamicConnector.union(to1D(i, j), virtualTopNode);
				if ( grid[i + 1][j]) { // only need to union with grid below top row
					dynamicConnector.union(to1D(i,j), to1D(i + 1, j));
				}
			} else if ( i == size - 1) { // on bottom row, connect to bottom "virtual node"
				dynamicConnector.union(to1D(i, j), virtualBottomNode);
				if ( grid[i - 1][j]) { // only need to union bottom row with grid above it
					dynamicConnector.union(to1D(i, j), to1D(i - 1, j));
				}
			} else {
	              if (j == (size-1)) {
	                    if ((grid[i][j-1]))       
	                        dynamicConnector.union((to1D(i, j)),(to1D(i, j-1)));
	                    if ((grid[i-1][j]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i-1 , j)));
	                    if ((grid[i+1][j]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i+1, j)));
	                }
	                else if (j==0) {
	                    if ((grid[i][j+1]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i, j+1)));
	                    if ((grid[i-1][j]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i-1, j)));
	                    if ((grid[i+1][j]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i+1, j)));
	                }
	                else {
	                    if ((grid[i][j-1]))
	                    	dynamicConnector.union((to1D(i, j)),(to1D(i, j-1)));
	                    if ((grid[i][j+1]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i, j+1)));
	                    if ((grid[i-1][j]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i-1, j)));
	                    if ((grid[i+1][j]))
	                    	dynamicConnector.union(((to1D(i, j))),(to1D(i+1, j)));
	                }  
			}
			
		}	
	}
	
	/**
	 * Simply tells you whether or not that grid space
	 * has been turned 'on'
	 * 
	 * @param i row position in 2d array
	 * @param j column position in 2d array
	 * @return true if on, false otherwise
	 */
	public boolean isOpen(int i, int j) {
		return grid[i][j];
	}
	
	/**
	 * This returns true if there is a percolation (connection
	 * from bottom to top)
	 * 
	 * @return true if some spot on bottom row connects to top row
	 */
	public boolean percolates() {
		return dynamicConnector.connected(virtualBottomNode, virtualTopNode);
	}
	
	
	/**
	 * Dynamic Connector takes in only one number for 
	 * doing a union. So convert the i and j of 2D array
	 * into one linear number with row-major order.
	 * 
	 * @param i row position in 2D array
	 * @param j column position in 2D array
	 * @return number id of that grid space in dynamic connector
	 */
	private int to1D(int i, int j) {
		return ( (i * size) + j);
	}
	
	
	
}
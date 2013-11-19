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
public class Percolation<T extends DyanmicConnectivity> {
	private T dynamicConnector;
	private boolean[][] grid;
	private int size;
	
	/**
	 * Constructs a Percolation object. One note is that the
	 * dyanmic Connector passed 
	 * @param dynamicConnector
	 * @param size
	 */
	public Percolation(T dynamicConnector, int size) {
		this.size = size;
		this.dynamicConnector = dynamicConnector;
		grid = new boolean[size][size];
	}
	
	
	
}

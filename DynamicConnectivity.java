
public interface DynamicConnectivity {
	
	/**
	 * This method sets the root of i, and all other 
	 * roots that have a root equal to that of i, equal
	 * to the root of j.
	 *  
	 * @param i first id to union together
	 * @param j second one, keeps its root
	 */
	public void union(int i, int j);
	
	/**
	 * This returns the root or parent node in the same set 
	 * as the inputed node.
	 * 
	 * @param p The node to find its parent/root node
	 * @return The head of the set that p is in
	 */
	public int find(int p);
	
	/**
	 * Checks to see if the two nodes i and j are in the same
	 * set.
	 * 
	 * @param i the first node 
	 * @param j the second node 
	 * @return true if have same root node, false otherwise
	 */
	public boolean connected(int i, int j);
}


/**
 * QuickFind data structure for DynamicConnectivity problem.
 * 
 * This has an O(1) find but a very slow O(N) union which, when
 * coupled with an O(N) initialization, becomes an O(N^2) structure.
 * 
 * @author trevor
 *
 */
public class QuickFind extends DynamicConnectivity {
	
	/**
	 * Contructs a QuickFind object with a given number of
	 * individual nodes to start with. Default root for
	 * each node is itself (all disjoint sets).
	 * 
	 * @param N number of elements in this QuickFind
	 */
	public QuickFind(int N) {
		roots = new int[N];
		for(int i = 0; i < N; i++) {
			roots[i] = i;
		}
	}

	@Override
	void union(int i, int j) {
		int iRoot = find(i);
		int jRoot = find(j);

		for (int k = 0; k < roots.length; k++) {
			if (roots[k] == iRoot) {
				roots[k] = jRoot;
			}
		}
	}

	@Override
	int find(int p) {
		return roots[p];
	}

	@Override
	boolean connected(int i, int j) {
		return roots[i] == roots[j];
	}

}

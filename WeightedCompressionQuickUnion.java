
public class WeightedCompressionQuickUnion implements DynamicConnectivity {
	private int[] roots; // root of tree of each node
	private int[] sz; // number of nodes in a subtree with root of i
	private int num; // number of disjoint sets in this set

	public WeightedCompressionQuickUnion(int size) {
		num = size; // initially every node is its own disjoint set
		roots = new int[size];
		sz = new int[size];
		for (int i = 0; i < size; i++) {
			roots[i] = i; // root of each node is itself at first
			sz[i] = 1; // just that node in its tree
		}
	}
	
	@Override
	public void union(int i, int j) {
		int iRoot = find(i);
		int jRoot = find(j);
		
		if (iRoot != jRoot) {
			if (sz[iRoot] < sz[jRoot]) {
				// make smaller tree root point to larger tree root
				roots[iRoot] = jRoot;
				sz[jRoot] += sz[iRoot];
			} else {
				// j tree is smaller or equal in size so have it point to iRoot
				roots[jRoot] = iRoot;
				sz[iRoot] += sz[jRoot];
			}
			// we just did a union so decrease number of disjoint sets
			num--;
		}
		
	}

	@Override
	public int find(int p) {
		while (p != roots[p]) { // while a node's root is not itself..go up!
			roots[p] = roots[roots[p]]; // this is our simple path compression in that we make each node point to grandparent
			p = roots[p];
		}
		return p;
	}

	@Override
	public boolean connected(int i, int j) {
		return find(i) == find(j);
	}

}

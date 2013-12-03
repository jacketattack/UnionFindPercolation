import static org.junit.Assert.*;

import org.junit.Test;


public class PercolationTest {

	@Test
	public final void testPercolation() {
		int sz = 10;
		WeightedCompressionQuickUnion qf = new WeightedCompressionQuickUnion(sz * sz + 2);
		Percolation perc = new Percolation(qf, sz);
		assertFalse("test empty grid", perc.percolates());
		
		// lets open up some grid spots
		for (int i = 0; i < sz; i++) {
			perc.open(i, 0);
		}
		assertTrue("should have connection along left column", perc.percolates());
		//perc.toStringy();
		WeightedCompressionQuickUnion qf2 = new WeightedCompressionQuickUnion(sz * sz + 2);
		Percolation perc2 = new Percolation(qf2, sz);
		// now lets open all spots along diagonal
		for (int i = 0; i < sz; i++) {
			perc2.open(i, i);
		}
		//perc2.toStringy();
		assertFalse("making sure is not connecting diagonal neighbors", perc2.percolates());
	}
}

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

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
		System.out.println(perc.percentOn());
		//perc.toStringy();
		WeightedCompressionQuickUnion qf2 = new WeightedCompressionQuickUnion(sz * sz + 2);
		Percolation perc2 = new Percolation(qf2, sz);
		// now lets open all spots along diagonal
		for (int i = 0; i < sz; i++) {
			perc2.open(i, i);
		}
		//perc2.toStringy();
		assertFalse("making sure is not connecting diagonal neighbors", perc2.percolates());
		
		// lets try a mock of what will actually be done.opening random spots till percolates
		WeightedCompressionQuickUnion qf3 = new WeightedCompressionQuickUnion(sz * sz + 2);
		Percolation<WeightedCompressionQuickUnion> perc3 = new Percolation<WeightedCompressionQuickUnion>(qf3, sz);
		Random rand = new Random();
		perc3.startTimer();
		while (!perc3.percolates()) {
			int row = rand.nextInt(sz);
			int col = rand.nextInt(sz);
			perc3.open(row, col);
		}
		perc3.endTimer();
		System.out.println("percent on in 3rd test --> " + perc3.percentOn());
		System.out.println("time taken --> " + perc3.calculateTimeTaken());
		toStringy(perc3.percolationPath());
		
		// another mock test with slower union find
		QuickFind qf4 = new QuickFind(sz * sz + 2);
		Percolation<QuickFind> perc4 = new Percolation<QuickFind>(qf4, sz);
		Random rand2 = new Random();
		perc4.startTimer();
		while (!perc4.percolates()) {
			int row = rand2.nextInt(sz);
			int col = rand2.nextInt(sz);
			perc4.open(row, col);
		}
		perc4.endTimer();
		System.out.println("percent on in 4th test --> " + perc4.percentOn());
		System.out.println("time taken --> " + perc4.calculateTimeTaken());
		toStringy(perc4.percolationPath());
	}
	
	public void toStringy(ArrayList<Point> path) {
		StringBuilder lol = new StringBuilder("  PERCOLATION PATH \n");
		for (int i = 0; i < path.size(); i++) {
			lol.append("Point:   " + path.get(i).x + "   " + path.get(i).y + "\n");
		}
		System.out.println("size" + "  " + path.size());
		System.out.println(lol);
	}
}

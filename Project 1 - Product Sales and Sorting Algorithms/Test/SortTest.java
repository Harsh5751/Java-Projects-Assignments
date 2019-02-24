package Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import Product.Product;
import sort.Heap;
import sort.Insertion;
import sort.Merge;
import sort.Quick;

import StopWatch.StopWatch;

public class SortTest {
	// This dummy will be called at the start of each test so the SetUp execution
	// time isn't
	// counted as part of the first array sort when stop watch is executed
	Product[] Dummy = new Product[16];

	Product[] A1 = new Product[16];
	Product[] A2 = new Product[64];
	Product[] A3 = new Product[256];
	Product[] A4 = new Product[1024];
	Product[] A5 = new Product[4096];

	@Before
	public void setUp() throws Exception {
		for (int g = 0; g < 16; g++) {
			Dummy[g] = new Product("A", g);
		}

		File f = new File("data\\a1_in.txt");
		Scanner s = new Scanner(f);

		int whichLine = 1;

		while (s.hasNextLine()) {

			String line = s.nextLine();
			line = line.replace("{", "");
			line = line.replace("}", "");
			line = line.replace(",", " ");
			String[] values = line.split(" ");

			int j = 0;
			for (int i = 0; i < values.length; i += 2) {
				String proId = values[i];
				int proSale = Integer.parseInt(values[i + 1]);
				Product prod = new Product(proId, proSale);
				if (whichLine == 1) {
					A1[j] = prod;
				} else if (whichLine == 2) {
					A2[j] = prod;
				} else if (whichLine == 3) {
					A3[j] = prod;
				} else if (whichLine == 4) {
					A4[j] = prod;
				} else if (whichLine == 5) {
					A5[j] = prod;
				}
				j++;
			}

			whichLine += 1;
		}

		s.close();
	}

	//Method to check if the array is sorted
	public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
		for (int i = 1; i < a.length; i++) {
			if (a[i].compareTo(a[i - 1]) < 0) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testMergeTD() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Merge.sortMergeTD(Dummy, Dummy.length);

		StopWatch time = new StopWatch();
		Merge.sortMergeTD(A1, A1.length);
		System.out.println("Merge TD A1: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeTD(A2, A2.length);
		System.out.println("Merge TD A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeTD(A3, A3.length);
		System.out.println("Merge TD A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeTD(A4, A4.length);
		System.out.println("Merge TD A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeTD(A5, A5.length);
		System.out.println("Merge TD A5: " + time.ElapsedTime() + " ns");

		System.out.println();

		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@Test
	public void testMergeBU() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Merge.sortMergeBU(Dummy, Dummy.length);

		StopWatch time = new StopWatch();
		Merge.sortMergeBU(A1, A1.length);
		System.out.println("Merge BU A1: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeBU(A2, A2.length);
		System.out.println("Merge BU A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeBU(A3, A3.length);
		System.out.println("Merge BU A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeBU(A4, A4.length);
		System.out.println("Merge BU A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Merge.sortMergeBU(A5, A5.length);
		System.out.println("Merge BU A5: " + time.ElapsedTime() + " ns");

		System.out.println("");
		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@Test
	public void testSortInsert() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Insertion.sortInsert(Dummy);

		StopWatch time = new StopWatch();
		Insertion.sortInsert(A1);
		System.out.println("SortInsert A1: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortInsert(A2);
		System.out.println("SortInsert A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortInsert(A3);
		System.out.println("SortInsert A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortInsert(A4);
		System.out.println("SortInsert A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortInsert(A5);
		System.out.println("SortInsert A5: " + time.ElapsedTime() + " ns");

		System.out.println();

		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@Test
	public void testInsertComparable() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Insertion.sortComparable(Dummy, Dummy.length);

		StopWatch time = new StopWatch();
		Insertion.sortComparable(A1, A1.length);
		System.out.println("InsertComparable A1: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortComparable(A2, A2.length);
		System.out.println("InsertComparable A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortComparable(A3, A3.length);
		System.out.println("InsertComparable A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortComparable(A4, A4.length);
		System.out.println("InsertComparable A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortComparable(A5, A5.length);
		System.out.println("InsertComparable A5: " + time.ElapsedTime() + " ns");

		System.out.println();

		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@Test
	public void testInsertBinary() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Insertion.sortBinary(Dummy, Dummy.length);

		StopWatch time = new StopWatch();
		Insertion.sortBinary(A1, A1.length);
		System.out.println("sortBinary A1: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortBinary(A2, A2.length);
		System.out.println("sortBinary A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortBinary(A3, A3.length);
		System.out.println("sortBinary A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortBinary(A4, A4.length);
		System.out.println("sortBinary A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Insertion.sortBinary(A5, A5.length);
		System.out.println("sortBinary A5: " + time.ElapsedTime() + " ns");

		System.out.println();
		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@Test
	public void testBasicQuick() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Quick.sortBasicQuick(Dummy);

		StopWatch time = new StopWatch();
		Quick.sortBasicQuick(A1);
		System.out.println("BasicQuick A1: " + (time.ElapsedTime()) + " ns");

		time = new StopWatch();
		Quick.sortBasicQuick(A2);
		System.out.println("BasicQuick A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Quick.sortBasicQuick(A3);
		System.out.println("BasicQuick A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Quick.sortBasicQuick(A4);
		System.out.println("BasicQuick A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Quick.sortBasicQuick(A5);
		System.out.println("BasicQuick A5: " + time.ElapsedTime() + " ns");

		System.out.println();

		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@Test
	public void testThreePartition() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Quick.sortThreePartition(Dummy, Dummy.length);

		StopWatch time = new StopWatch();
		Quick.sortThreePartition(A1, A1.length);
		System.out.println("ThreePartition A1: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Quick.sortThreePartition(A2, A2.length);
		System.out.println("ThreePartition A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Quick.sortThreePartition(A3, A3.length);
		System.out.println("ThreePartition A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Quick.sortThreePartition(A4, A4.length);
		System.out.println("ThreePartition A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Quick.sortThreePartition(A5, A5.length);
		System.out.println("ThreePartition A5: " + time.ElapsedTime() + " ns");

		System.out.println();

		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@Test
	public void testHeap() {

		// This dummy will be called at the start of each test so the SetUp execution
		// time isn't
		// counted as part of the first array sort when stop watch is executed
		Heap.sortHeap(Dummy, Dummy.length);

		StopWatch time = new StopWatch();
		Heap.sortHeap(A1, A1.length);
		System.out.println("Heap A1: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Heap.sortHeap(A2, A2.length);
		System.out.println("Heap A2: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Heap.sortHeap(A3, A3.length);
		System.out.println("Heap A3: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Heap.sortHeap(A4, A4.length);
		System.out.println("Heap A4: " + time.ElapsedTime() + " ns");

		time = new StopWatch();
		Heap.sortHeap(A5, A5.length);
		System.out.println("Heap A5: " + time.ElapsedTime() + " ns");

		System.out.println();
		assert (isSorted(A1) && isSorted(A2) && isSorted(A3) && isSorted(A4) && isSorted(A5));
	}

	@After
	public void tearDown() throws Exception {
	}

}

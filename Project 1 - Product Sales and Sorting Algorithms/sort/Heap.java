/**
 * @author Harsh Patel
 * @version 1.0 
 */

package sort;

import Product.Product;

public class Heap {

	// NOTE : code of sorting functions from https://algs4.cs.princeton.edu/home/,
	// https://algs4.cs.princeton.edu/lectures/,
	// https://algs4.cs.princeton.edu/code/

	/**
	 * Sink Function using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param k - index of element being sinked in the input array
	 * @param n - length of input array
	 */
	private static void sink(Comparable[] x, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(x, j, j + 1))
				j++;
			if (!less(x, k, j))
				break;
			// exchange elements at index k and j
			exch(x, k, j);
			k = j;
		}
	}

	/**
	 * heap sort using Comparable
	 * 
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortHeap(Comparable[] x, int n) {
		for (int k = n / 2; k >= 1; k--)
			sink(x, k, n);
		while (n > 1) {
			exch(x, 1, n--);
			sink(x, 1, n);
		}
	}

	/**
	 * Exchange Function using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param i - index of element to be swapped
	 * @param j - index of element to be swapped
	 */
	// both helper functions from
	// https://algs4.cs.princeton.edu/24pq/Heap.java.html?fbclid=IwAR3DunuQRgHECyYFKk7cRtOAcB1lSgX1oAZcc3I8FSzFS0DRZbxNgH_003U
	private static void exch(Object[] x, int i, int j) {
		Object swap = x[i - 1];
		x[i - 1] = x[j - 1];
		x[j - 1] = swap;
	}

	/**
	 * Less Function using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param i - index of element to be compared
	 * @param j - index of element to be compared
	 */
	private static boolean less(Comparable[] x, int i, int j) {
		return x[i - 1].compareTo(x[j - 1]) < 0;
	}
}

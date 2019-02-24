/**
 * @author Harsh Patel
 * @version 1.0 
 */

package sort;

import Product.Product;

import java.util.Collections;
import java.util.Random;

public class Quick {

	// NOTE : code of sorting functions from https://algs4.cs.princeton.edu/home/,
	// https://algs4.cs.princeton.edu/lectures/,
	// https://algs4.cs.princeton.edu/code/

	/**
	 * partition using Comparable
	 * 
	 * @param x  - the input array containing products that need to be sorted.
	 * @param lo - lower index of the input array
	 * @param hi - higher index of the input array
	 */
	private static int partition(Comparable[] x, int lo, int hi) {
		int i = lo;
		int j = hi + 1;

		// sorting elements to have all left of partition element less and all right
		// more
		while (true) {

			while (x[++i].compareTo(x[lo]) < 0) {
				if (i == hi) {
					break;
				}
			}

			while (x[lo].compareTo(x[--j]) < 0) {
				if (j == lo) {
					break;
				}
			}

			// break if pointers i and j cross
			if (i >= j) {
				break;
			}

			// swap element at index i with element at index j;
			Comparable temp = x[j];
			x[j] = x[i];
			x[i] = temp;

		}

		// swap first and partition element
		Comparable temp2 = x[lo];
		x[lo] = x[j];
		x[j] = temp2;
		return j;
	}

	/**
	 * quickHelper sort function using Comparable
	 * 
	 * @param x  - the input array containing products that need to be sorted.
	 * @param lo - lower index of the input array
	 * @param hi - higher index of the input array
	 */
	private static void quickHelper(Comparable[] x, int lo, int hi) {
		// terminate function if condition met
		if (hi <= lo) {
			return;
		}

		// Get partition element index
		int j = partition(x, lo, hi);

		// QuickSort Recursive call
		quickHelper(x, lo, j - 1);
		quickHelper(x, j + 1, hi);
	}

	/**
	 * basic quick sort
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void sortBasicQuick(Product[] x) {

		// Perform QuickSort on array x
		quickHelper(x, 0, x.length - 1);
	}

	/**
	 * three partition quick sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param lo - lower index of input array
	 * @param hi - higher index of input array
	 */
	public static void threeQuickHelper(Comparable[] x, int lo, int hi) {

		// terminate if lo is equal to or greater then hi; pointers crossed
		if (hi <= lo) {
			return;
		}

		// find the median
		int median = median3(x, lo, lo + (hi - lo) / 2, hi);
		int j = partition(x, lo, hi);

		// Recursive call
		threeQuickHelper(x, lo, j - 1);
		threeQuickHelper(x, j + 1, hi);
	}

	/**
	 * three partition quick sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortThreePartition(Comparable[] x, int n) {
		// QuickSort call on Three Partition QuickSort
		threeQuickHelper(x, 0, n - 1);
	}

	/**
	 * Median Function using Comparable
	 * 
	 * @param x - Comparable array
	 * @param i - element used to find median of 3
	 * @param k - element used to find median of 3
	 * @param j - element used to find median of 3
	 */
	private static int median3(Comparable[] x, int i, int j, int k) {
		return (less(x[i], x[j]) ? (less(x[j], x[k]) ? j : less(x[i], x[k]) ? k : i)
				: (less(x[k], x[j]) ? j : less(x[k], x[i]) ? k : i));
	}

	/**
	 * Less Function using Comparable
	 * 
	 * @param v - element to be compared
	 * @param w - element to be compared
	 */
	private static boolean less(Comparable v, Comparable w) {
		if (v == w)
			return false; // optimization when reference equal
		return v.compareTo(w) < 0;
	}
}

/**
 * @author Harsh Patel
 * @version 1.0 
 */

package sort;

import Product.Product;

public class Insertion {
	// NOTE : code of sorting functions from https://algs4.cs.princeton.edu/home/,
	// https://algs4.cs.princeton.edu/lectures/,
	// https://algs4.cs.princeton.edu/code/

	/**
	 * regular insertion sort
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 */
	public static void sortInsert(Product[] x) {
		int N = x.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0; j--) {

				// Compare sales amount and swap if greater index has lower
				if (x[j].getSalesAmount() < x[j - 1].getSalesAmount()) {
					Product temp = x[j - 1];
					x[j - 1] = x[j];
					x[j] = temp;
				}

				// Check if sales amounts are same and compare product id
				else if (x[j].getSalesAmount() == x[j - 1].getSalesAmount()) {
					for (int h = 0; h < x[j].getProductId().length(); h++) {

						// Compare characters of product ID
						if (x[j].getProductId().charAt(h) < x[j - 1].getProductId().charAt(h)) {
							Product temp2 = x[j - 1];
							x[j - 1] = x[j];
							x[j] = temp2;
							break;
						}
						// If ID at higher index greater, don't swap and break
						if (x[j].getProductId().charAt(h) > x[j - 1].getProductId().charAt(h)) {
							break;
						}
					}
				}

				// Break loop and move to next product
				else {
					break;
				}
			}
		}

	}

	/**
	 * insertion sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortComparable(Comparable[] x, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0; j--) {

				// Compare sales amount using compareTo function
				if (x[j].compareTo(x[j - 1]) < 0) {
					Comparable temp = x[j - 1];
					x[j - 1] = x[j];
					x[j] = temp;
				}

				// break out of second loop, and move to next product
				else {
					break;
				}
			}
		}
	}

	/**
	 * Binary Search algorithm for sortBinary
	 * 
	 * @param x   - the input array containing products that need to be sorted.
	 * @param val - val needed to be inserted at correct index in sort
	 * @param lo  - minimum index for array x
	 * @param hi  - maximum index for array x
	 * @return integer of index to insert val
	 */
	// Algorithm Similar to https://www.geeksforgeeks.org/binary-insertion-sort/
	public static int BinSearch(Comparable[] x, Comparable val, int lo, int hi) {
		int mid = 0;
		while (lo <= hi) {
			mid = ((lo + hi) / 2);
			if (x[mid].compareTo(val) > 0) {
				hi = mid - 1;
				BinSearch(x, val, lo, hi);
			}
			if (x[mid].compareTo(val) < 0) {
				lo = mid + 1;
				BinSearch(x, val, lo, hi);
			}
			if (x[mid].compareTo(val) == 0) {
				return mid;
			}
		}

		// check if element to insert is greater or less then element at current index
		// return lo to account for that comparison
		if (val.compareTo(x[lo]) > 0) {
			return lo + 1;
		} else {
			return lo;
		}
	}

	/**
	 * optimized insertion sort
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortBinary(Comparable[] x, int n) {
		for (int i = 1; i < n; i++) {

			// Index to insert from Binary Search
			int pos = BinSearch(x, x[i], 0, i);

			// Store current product in temp variable
			Comparable temp = x[i];

			// Shift all values over one index from index i
			for (int j = i; j > pos; j--) {
				x[j] = x[j - 1];
			}

			// insert value in index from Binary Search
			x[pos] = temp;

		}
	}
}

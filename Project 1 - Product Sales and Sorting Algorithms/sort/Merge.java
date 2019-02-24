/**
 * @author Harsh Patel
 * @version 1.0 
 */
package sort;

import Product.Product;

public class Merge {
	// NOTE : code of sorting functions from https://algs4.cs.princeton.edu/home/,
	// https://algs4.cs.princeton.edu/lectures/,
	// https://algs4.cs.princeton.edu/code/

	/**
	 * merge function using Comparable
	 * 
	 * @param x   - the input array containing products that need to be sorted.
	 * @param aux - array in which all elements of x will be copied to.
	 * @param lo  - lower index of array
	 * @param hi  - higher index of array
	 * @param mid - middle index of array
	 */
	// Merge function to merge divided sub arrays
	public static void merge(Comparable[] x, Comparable[] aux, int lo, int mid, int hi) {

		// copy all elements of x to aux array
		for (int k = 0; k <= hi; k++) {
			aux[k] = x[k];
		}

		int i = lo;
		int j = mid + 1;

		// Compare elements of divided array and copy back to array x
		for (int k = lo; k <= hi; k++) {

			// if no more elements in left sub array
			if (i > mid) {
				x[k] = aux[j++];
			}

			// if no more elements in right sub array
			else if (j > hi) {
				x[k] = aux[i++];
			}

			// compare two elements to see which is less
			else if (aux[j].compareTo(aux[i]) < 0) {
				x[k] = aux[j++];
			}

			else {

				// copy to x array and increment pointer
				x[k] = aux[i++];
			}
		}
	}

	/**
	 * sort function using Comparable
	 * 
	 * @param x   - the input array containing products that need to be sorted.
	 * @param aux - array in which all elements of x will be copied to.
	 * @param lo  - lower index of array
	 * @param hi  - higher index of array
	 */
	private static void sortHelper(Comparable[] x, Comparable[] aux, int lo, int hi) {

		// terminate function if lo is equal to or greater then hi
		if (hi <= lo) {
			return;
		}

		int mid = lo + (hi - lo) / 2;

		// recursive calls to sort the two divided sub-arrays
		sortHelper(x, aux, lo, mid);
		sortHelper(x, aux, mid + 1, hi);

		// merge the divided sub-arrays
		merge(x, aux, lo, mid, hi);
	}

	/**
	 * top-down merge sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeTD(Comparable[] x, int n) {

		// Create new aux array to copy elements from x
		Comparable aux[] = new Comparable[n];

		// sort method
		sortHelper(x, aux, 0, n - 1);

	}

	/**
	 * bottom-up merge sort using Comparable
	 * 
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeBU(Comparable[] x, int n) {

		// create new aux array to copy elements from x
		Comparable[] aux = new Comparable[n];

		// Implements Bottom Up method
		for (int i = 1; i < n; i = i * 2) {
			for (int lo = 0; lo < n - i; lo += i * 2) {

				// merge the sub-arrays
				merge(x, aux, lo, lo + i - 1, Math.min(lo + i + i - 1, n - 1));
			}
		}
	}
}

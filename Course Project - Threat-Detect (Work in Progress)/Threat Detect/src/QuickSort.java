import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Michael Barreiros
 *
 * Citation: ArrayList implementation of QuickSort is referencing
 * https://www.withexample.com/quicksort-implementation-example-using-arraylist-java/
 */
public class QuickSort {
	private static ArrayList<Incidents> inputArray = new ArrayList<Incidents>();
	
	// following two lines are variables needed to create a random object to use for shuffling our array
	//Potentially need to put these inside shuffle
	private static Random random; 
	private static long seed;

	
	public QuickSort(ArrayList<Incidents> inputArray){
        QuickSort.inputArray = inputArray;
    }
	
	/**
	 * This method randomly shuffles the incoming array
	 * 
	 */
	public static void shuffle() {
		seed = System.currentTimeMillis();
		random = new Random(seed);

		if (inputArray != null) {
			int n = inputArray.size();
			for (int i = 0; i < n; i++) {
				int r = i + random.nextInt(n - i);
				Incidents temp = inputArray.get(i);
				inputArray.set(i, inputArray.get(r));
				inputArray.set(r, temp);
			}
		}
	}
	
	/**
	 * This method does the partitioning that is necessary for quicksort to function
	 * 
	 * @param lo the lower bound of the array to be partitioned
	 * @param hi the upper bound of the array to be partitioned
	 * @return returns the final location of the element that was partitioned correctly
	 */
	public static int partition(int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Incidents v = inputArray.get(lo); // Partitioning element

		while (true) {
			while (inputArray.get(++i).compareTo(v) < 0)
				if (i == hi)
					break;
			while (v.compareTo(inputArray.get(--j)) < 0)
				if (j == lo)
					break;
			if (i >= j)
				break;
			Incidents temp = inputArray.get(i);
			inputArray.set(i, inputArray.get(j));
			inputArray.set(j, temp);
		}
		Incidents temp = inputArray.get(lo);
		inputArray.set(lo, inputArray.get(j));
		inputArray.set(j,temp);
		
		return j;
	}

	/**
	 * basic quick sort
	 * 
	 */
	public static void sortBasicQuick() {
		shuffle();
		sortBasicQuick(0, inputArray.size() - 1);
	}

	public static void sortBasicQuick(int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(lo, hi); // Partition array
		sortBasicQuick(lo, j - 1); // Sort the left subarray
		sortBasicQuick(j + 1, hi); // Sort the right subarray
	}

	/**
	 * three partition quick sort 
	 * 
	 */
	public static void sortThreePartition() {
		shuffle();
		sortThreePartition(0, inputArray.size() - 1);
	}

	public static void sortThreePartition(int lo, int hi) {
		if (hi <= lo)
			return;
		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		Incidents v = inputArray.get(lo);

		while (i <= gt) {
			int cmp = ((Incidents) inputArray.get(i)).compareTo(v);
			if (cmp < 0) {
				Incidents temp = inputArray.get(lt);
				inputArray.set(lt, inputArray.get(i));
				inputArray.set(i, temp);
				i++;
				lt++;
			} else if (cmp > 0) {
				Incidents temp = inputArray.get(i);
				inputArray.set(i, inputArray.get(gt));
				inputArray.set(gt, temp);
				gt--;
			} else
				i++;
		}
		sortThreePartition(lo, lt - 1);
		sortThreePartition(gt + 1, hi);
	}

	/**
	 * This method checks to see if the array is sorted
	 * 
	 * @param x the array that is being checked
	 * @return true or false whether or not the array is sorted
	 */
	public static boolean isSorted() {
		for (int i = 1; i < inputArray.size(); i++) {
			if (inputArray.get(i).compareTo(inputArray.get(i -1)) < 0)
				return false;
		}
		return true;
	}
}

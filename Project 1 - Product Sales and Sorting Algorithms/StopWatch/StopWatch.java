/**
 * @author Harsh Patel
 * @version 1.0 
 */

package StopWatch;

//StopWatch Class
public class StopWatch {
	// start time
	private long start;
	// end time
	private long stop;

	// initialize StopWatch
	/**
	 * StopWatch to record time
	 */
	public StopWatch() {
		// start time in nanoseconds
		this.start = System.nanoTime();
	}

	// Elapsed time
	/**
	 * Time passed since initialization
	 * @return time passed since StopWatch was initialized 
	 */
	public long ElapsedTime() {
		// end time in nanoseconds
		this.stop = System.nanoTime();
		// elapsed time
		return (stop - start);
	}
}

package cn.glycol.tutils.timing;

/**
 * This class is a timer to test the speed of some codes.
 * 
 * <pre>
 * Timing t = new Timing();
 * t.start();
 * // do something
 * t.stop();
 * long duration = t.duration();
 * </pre>
 * 
 * @author Taskeren
 * @since 1.0
 */
public class Timing {

	public Timing() {}

	private long startTime, stopTime;

	/**
	 * Set the start time
	 */
	public void start() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * Set the stop time
	 */
	public void stop() {
		stopTime = System.currentTimeMillis();
	}

	/**
	 * Get the time between start and stop
	 * @return the time (ms)
	 */
	public long duration() {
		return stopTime - startTime;
	}

}

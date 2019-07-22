package cn.glycol.tutils.tools.timer;

/**
 * This class is a timer to test the speed of some codes.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class Timer {

	public Timer() {
	}

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

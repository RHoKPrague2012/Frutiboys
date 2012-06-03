package cz.rhok.prague.osf.governmentcontacts.helper;

import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

import cz.rhok.prague.osf.governmentcontacts.scraper.UnableToConnectToServer;

public abstract class RepeatOnTimeoutTask<V> implements Callable<V> {

	private static final int WAIT_TIME_BETWEEN_REPETITION = 1000;
	private static final int MAX_NUMBER_OF_REPETITION = 5;


	@Override
	public V call() throws Exception {

		int repetitionCount = 0;

		while(repetitionCount <= MAX_NUMBER_OF_REPETITION) {
			try {
				
				if (repetitionCount > 0) {
					/* just wait, timeout can be caused by detection of automatization (too fast) or server is overloaded  */
					Thread.sleep(WAIT_TIME_BETWEEN_REPETITION); 
				}
				
				return doTask();
			} catch (UnableToConnectToServer utce) {
				repetitionCount++;
			} catch (RuntimeException re) {
				if (re.getCause() instanceof SocketTimeoutException) {
					repetitionCount++;
				}
				else {
					throw re;
				}
			}
		}
		
		throw new RuntimeException("Operation failed");

	}


	public abstract V doTask();

}

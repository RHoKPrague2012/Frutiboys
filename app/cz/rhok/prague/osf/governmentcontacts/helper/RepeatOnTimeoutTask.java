package cz.rhok.prague.osf.governmentcontacts.helper;

import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

import cz.rhok.prague.osf.governmentcontacts.scraper.UnableToConnectToServer;

public abstract class RepeatOnTimeoutTask<V> implements Callable<V> {

	private static final int MAX_NUMBER_OF_REPETITION = 5;


	@Override
	public V call() throws Exception {

		int repetitionCount = 0;

		while(repetitionCount <= MAX_NUMBER_OF_REPETITION) {
			try {
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

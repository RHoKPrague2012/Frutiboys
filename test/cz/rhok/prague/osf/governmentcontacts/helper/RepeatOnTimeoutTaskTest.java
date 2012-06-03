package cz.rhok.prague.osf.governmentcontacts.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.SocketTimeoutException;

import org.junit.Before;
import org.junit.Test;

import cz.rhok.prague.osf.governmentcontacts.scraper.UnableToConnectToServer;

public class RepeatOnTimeoutTaskTest  {

	private static int count = 0;
	
	@Before
	public void beforeEachTest() {
		count = 0; /* reset counter */
	}

	@Test
	public void shouldBeRepeatedOnce() throws Exception {

		String result = new RepeatOnTimeoutTask<String>() {

			@Override
			public String doTask() {
				count++;
				return "text_string";
			}

		}.call();

		assertEquals("text_string", result);
		assertTrue("Should be called only once.", count == 1);

	}


	@Test
	public void shouldBeThreeTimesOnSocketTimeoutException() throws Exception {

		String result = new RepeatOnTimeoutTask<String>() {

			@Override
			public String doTask() {
				
				count++;
				if (count < 3) {
					throw new RuntimeException(new SocketTimeoutException("buga buga"));
				}
				
				return "text_string";
			}

		}.call();

		assertEquals("Should be called three times.", 3, count);

	}
	
	@Test
	public void shouldBeThreeTimesOnUnableToConnectException() throws Exception {

		String result = new RepeatOnTimeoutTask<String>() {

			@Override
			public String doTask() {
				
				count++;
				if (count < 3) {
					throw new UnableToConnectToServer("buga buga", new RuntimeException("buga"));
				}
				
				return "text_string";
			}

		}.call();

		assertEquals("Should be called three times.", 3, count);

	}
	
	@Test(expected = RuntimeException.class)
	public void shouldFailInstantlyOnUncommonError() throws Exception {

		new RepeatOnTimeoutTask<String>() {

			@Override
			public String doTask() {
				throw new RuntimeException("you! you gay mickey mouse!");
			}

		}.call();

		
	}

}

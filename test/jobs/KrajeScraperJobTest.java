package jobs;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import play.jobs.Job;
import play.test.UnitTest;

public class KrajeScraperJobTest extends UnitTest {

	@Test
	public void scrapeJobWOrks() throws InterruptedException, ExecutionException {
		
		Job job = new KrajeScraperJob();
		
		job.now().get();
		
	}
	
}

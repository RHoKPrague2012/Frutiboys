package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.net.URL;
import java.util.List;

import org.junit.Test;

import play.test.UnitTest;

public class SeznamDatovychSchranekListPageScraperIntegrationTest extends UnitTest {

	/**
	 * Does it smoke?
	 */
	@Test
	public void smokeTest() {
		SeznamDatovychSchranekListPageScraper scraper = new SeznamDatovychSchranekListPageScraper();
		List<URL> scrape = scraper.scrape("http://seznam.gov.cz/ovm/othersList.do?ref=obcan");
		assertTrue("There is no url :(", scrape.size() > 0);
	}
	
}

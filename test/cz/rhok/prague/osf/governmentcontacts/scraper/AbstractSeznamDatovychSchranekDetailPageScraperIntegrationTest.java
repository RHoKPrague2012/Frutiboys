package cz.rhok.prague.osf.governmentcontacts.scraper;
import models.Organization;

import org.junit.Before;

import play.test.UnitTest;
import cz.rhok.prague.osf.governmentcontacts.scraper.SeznamDatovychSchranekDetailPageScaper;


public abstract class AbstractSeznamDatovychSchranekDetailPageScraperIntegrationTest extends UnitTest {

	protected static Organization organization;

	@Before
	public void beforeEachTest() {
		SeznamDatovychSchranekDetailPageScaper scraper = new SeznamDatovychSchranekDetailPageScaper();
		String url = getUrlForScraping();
		organization = scraper.scrape(url);
	}

	protected abstract String getUrlForScraping();

}

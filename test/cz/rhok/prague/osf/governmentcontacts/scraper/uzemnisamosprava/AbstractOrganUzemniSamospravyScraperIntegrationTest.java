package cz.rhok.prague.osf.governmentcontacts.scraper.uzemnisamosprava;
import models.Organization;

import org.junit.Before;

import play.test.UnitTest;
import cz.rhok.prague.osf.governmentcontacts.scraper.uzemnisamosprava.OrganyUzemniSamospravyScaper;


public abstract class AbstractOrganUzemniSamospravyScraperIntegrationTest extends UnitTest {

	protected static Organization organization;

	@Before
	public void beforeEachTest() {
		OrganyUzemniSamospravyScaper scraper = new OrganyUzemniSamospravyScaper();
		String url = getUrlForScraping();
		organization = scraper.scrape(url);
	}

	protected abstract String getUrlForScraping();

}

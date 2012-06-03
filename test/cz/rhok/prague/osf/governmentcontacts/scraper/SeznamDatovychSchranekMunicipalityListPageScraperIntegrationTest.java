package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.net.URL;
import java.util.List;

import org.junit.Test;

import play.test.UnitTest;

public class SeznamDatovychSchranekMunicipalityListPageScraperIntegrationTest extends UnitTest {
	
	private static final String LIBEREC_DETAIL_PAGE_WITH_ALL_MUNI = 
				"http://seznam.gov.cz/ovm/regionDetail.do?path=KLIBEREC&listType=allMunicipality";

	@Test
	public void smokeTest() {
		
		SeznamDatovychSchranekMunicipalityListPageScraper scraper = 
										new SeznamDatovychSchranekMunicipalityListPageScraper();
		
		List<URL> municipalityDetailPageUrls = 
					scraper.extractDetailPageUrlsFrom(LIBEREC_DETAIL_PAGE_WITH_ALL_MUNI);
		
		assertTrue(
				"If it's all right it should contains more than 1 record",
				municipalityDetailPageUrls.size() > 1);
		
		
	}
	

}

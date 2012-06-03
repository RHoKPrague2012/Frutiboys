package cz.rhok.prague.osf.governmentcontacts.scraper;

import org.junit.Test;

import cz.rhok.prague.osf.governmentcontacts.scraper.PaginableRecordsListPageRetriever.PaginableRecord;

import play.test.UnitTest;

public class PaginableRecordsListPageRetrieverIntegrationTest extends UnitTest {
	
	@Test
	public void smokeTestKrajMunicipalityList() {
		
		PaginableRecordsListPageRetriever scraper = new PaginableRecordsListPageRetriever();
		
		PaginableRecord listPageLinks = 
							scraper.getListPageLinks("http://seznam.gov.cz/ovm/regionDetail.do?path=KSTRCESKY&listType=allMunicipality&ref=podnikani");
		
		assertTrue(
				"Should contain pages. Now it is totaly empty.", 
				listPageLinks.getPages().size() > 0);
		
		assertTrue(
				"It seems there is no page number. Pages listing:" + listPageLinks.getPages(),
				listPageLinks.getPages().containsKey(1L));
	}
	
	@Test
	public void smokeTestGovernmentAgenciesList() {
		
		PaginableRecordsListPageRetriever scraper = new PaginableRecordsListPageRetriever();
		
		PaginableRecord listPageLinks = 
							scraper.getListPageLinks("http://seznam.gov.cz/ovm/ossList.do?ref=podnikani");
		
		assertTrue(
				"Should contain pages. Now it is totaly empty.", 
				listPageLinks.getPages().size() > 0);
		
		assertTrue(
				"It seems there is no page number. Pages listing:" + listPageLinks.getPages(),
				listPageLinks.getPages().containsKey(1L));
		
		assertNotNull(
				"There should be 'next' button and it should be scraped",
				listPageLinks.getNextPaginable());
		
	}

}

package cz.rhok.prague.osf.governmentcontacts.scraper;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import cz.rhok.prague.osf.governmentcontacts.tests.IntegrationTests;

/**
 * Integration test for scraping "organy uzemni samospravy" with usage of Prague page 
 * (see {@link #PRAGUE_URL}).
 * 
 * @author Michal Bernhard michal@bernhard.cz / twitter @michalb_cz 
 *
 */
@Category(IntegrationTests.class)
public class OrganUzemniSamospravyPragueIntegrationTests 
								extends AbstractSeznamDatovychSchranekDetailPageScraperIntegrationTest {

	private static final String PRAGUE_URL = "http://seznam.gov.cz/ovm/regionDetail.do?path=KPRAHA&ref=obcan";
	
	@Override
	protected String getUrlForScraping() {
		return PRAGUE_URL;
	}
	
    /**
     * data box = datova schranka
     */
    @Test    
    public void testScraperForOrganizationDataBox() {
    	assertEquals("48ia97h", organization.dataBoxId);
    }


    @Test    
    public void testScraperForOrganizationName() {
    	assertEquals("HLAVNÍ MĚSTO PRAHA", organization.name);
    }

    @Test    
    public void testScraperForOrganizationCode() {
    	assertEquals("KPRAHA", organization.code);
    }

    @Test    
    public void testScraperForOrganizationIco() {
    	assertEquals("00064581", organization.organizationId);
    }
    
    @Test    
    public void testScraperForOrganizationDic() {
    	assertEquals("CZ00064581", organization.taxId);
    }
    
    @Test    
    public void testScraperForOrganizationEmail() {
    	assertEquals("posta@cityofprague.cz", organization.email);
    }

}

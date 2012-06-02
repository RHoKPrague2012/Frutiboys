package cz.rhok.prague.osf.governmentcontacts.scraper;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import cz.rhok.prague.osf.governmentcontacts.tests.IntegrationTests;


@Category(IntegrationTests.class)
public class OrganUzemniSamospravyLiberecIntegrationTest 
								extends AbstractSeznamDatovychSchranekDetailPageScraperIntegrationTest {

	private static final String ZLINSKY_KRAJ_URL =
									"http://seznam.gov.cz/ovm/regionDetail.do?path=KZLINSKY&ref=obcan";
	
	@Override
	protected String getUrlForScraping() {
		return ZLINSKY_KRAJ_URL;
	}
	
    /**
     * data box = datova schranka
     */
    @Test    
    public void testScraperForOrganizationDataBox() {
    	assertEquals("scsbwku", organization.dataBoxId);
    }


    @Test    
    public void testScraperForOrganizationName() {
    	assertEquals("Zlínský kraj", organization.name);
    }

    @Test    
    public void testScraperForOrganizationCode() {
    	assertEquals("KZLINSKY", organization.code);
    }

    @Test    
    public void testScraperForOrganizationIco() {
    	assertEquals("70891320", organization.organizationId);
    }
    
    @Test    
    public void testScraperForOrganizationDic() {
    	assertEquals("CZ70891320", organization.taxId);
    }
    
    @Test    
    public void testScraperForOrganizationEmail() {
    	assertEquals("podatelna@kr-zlinsky.cz", organization.email);
    }

}


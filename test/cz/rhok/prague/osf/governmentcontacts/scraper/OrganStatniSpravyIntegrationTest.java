package cz.rhok.prague.osf.governmentcontacts.scraper;

import org.junit.Test;

public class OrganStatniSpravyIntegrationTest extends
		AbstractSeznamDatovychSchranekDetailPageScraperIntegrationTest {

	@Override
	protected String getUrlForScraping() {
		return "http://seznam.gov.cz/ovm/ossDetail.do?path=ClnUDmzlce&ref=obcan";
	}
	
	/**
     * data box = datova schranka
     */
    @Test    
    public void testScraperForOrganizationDataBox() {
    	assertEquals("jg4ahh6", organization.dataBoxId);
    }


    @Test    
    public void testScraperForOrganizationName() {
    	assertEquals("Celní úřad Domažlice", organization.name);
    }

    @Test    
    public void testScraperForOrganizationCode() {
    	assertEquals("ClnUDmzlce", organization.code);
    }

    @Test    
    public void testScraperForOrganizationIco() {
    	assertEquals("71214011", organization.organizationId);
    }
    
        
    @Test    
    public void testScraperForOrganizationEmail() {
    	assertEquals("", organization.email);
    }
 

}

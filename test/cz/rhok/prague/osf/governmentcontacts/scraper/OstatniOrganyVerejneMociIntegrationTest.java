package cz.rhok.prague.osf.governmentcontacts.scraper;

import org.junit.Test;

public class OstatniOrganyVerejneMociIntegrationTest extends
		AbstractSeznamDatovychSchranekDetailPageScraperIntegrationTest {

	@Override
	protected String getUrlForScraping() {
		return "http://seznam.gov.cz/ovm/othersDetail.do?path=ArnKPlznjh&ref=obcan";
	}
	
	/**
     * data box = datova schranka
     */
    @Test    
    public void testScraperForOrganizationDataBox() {
    	assertEquals("wdiaezm", organization.dataBoxId);
    }


    @Test    
    public void testScraperForOrganizationName() {
    	assertEquals("Agrární komora Plzeň-jih", organization.name);
    }

    @Test    
    public void testScraperForOrganizationCode() {
    	assertEquals("ArnKPlznjh", organization.code);
    }

    @Test    
    public void testScraperForOrganizationIco() {
    	assertEquals("49786610", organization.organizationId);
    }
    
    @Test    
    public void testScraperForOrganizationDic() {
    	assertEquals("CZ49786610", organization.taxId);
    }
    
    @Test    
    public void testScraperForOrganizationEmail() {
    	assertEquals("j.tomec@seznam.cz", organization.email);
    }


}

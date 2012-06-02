import models.Organization;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import cz.rhok.prague.osf.governmentcontacts.OrganyUzemniSamospravyScaper;

import play.test.UnitTest;

/**
 * Integration test for scraping "organy uzemni samospravy" with usage of Prague page 
 * (see {@link #PRAGUE_URL}).
 * 
 * @author Michal Bernhard michal@bernhard.cz / twitter @michalb_cz 
 *
 */
@Category(IntegrationTests.class)
public class OrganyUzemniSamospravy_Prague_IntegrationTests extends UnitTest {

	private static final String PRAGUE_URL = 
								  "http://seznam.gov.cz/ovm/regionDetail.do?path=KPRAHA&ref=obcan";
	
	private static Organization organization;
	
	@BeforeClass
	public static void beforeAllTests() {
		OrganyUzemniSamospravyScaper scraper = new OrganyUzemniSamospravyScaper();
		String pragueUrl = PRAGUE_URL;
		organization = scraper.scrape(pragueUrl);
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

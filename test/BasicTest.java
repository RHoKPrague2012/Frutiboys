import models.Organization;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import cz.rhok.prague.osf.governmentcontacts.OrganyUzemniSamospravyScaper;

import play.test.UnitTest;

public class BasicTest extends UnitTest {

	private static OrganyUzemniSamospravyScaper scraper;
	
	@BeforeClass
	public static void beforeAllTests() {
		scraper = new OrganyUzemniSamospravyScaper();
	}
	
    @Test
    @Category(IntegrationTests.class)
    public void testScraperForPragueDataBox() {
        
    	String pragueUrl = "http://seznam.gov.cz/ovm/regionDetail.do?path=KPRAHA&ref=obcan";
		Organization prague = scraper.scrape(pragueUrl);
    	
    	assertEquals("48ia97h", prague.dataBoxId);
    	
    }
    
    

}

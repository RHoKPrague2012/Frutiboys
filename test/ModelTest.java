import models.Organization;

import org.junit.Test;

import play.db.jpa.GenericModel.JPAQuery;
import play.test.UnitTest;

/**
 * 
 */

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
public class ModelTest extends UnitTest {

	@Test
	public void testSave() {
		Organization entity = new Organization();
		entity.name = "Test 1";
		entity.code = "test1";
		
		entity.save();
		
		Organization entityFromDB = Organization.find("byCode", "test1").first();
		
		assertEquals("Test 1", entityFromDB.name);
	}
}

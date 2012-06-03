/**
 * 
 */
package cz.rhok.prague.osf.governmentcontacts.geocoding;

import java.io.IOException;
import java.net.MalformedURLException;

import models.GeoLocation;
import models.Organization;

import org.junit.Test;

import play.test.UnitTest;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
public class GeocoderTest extends UnitTest {

	@Test
	public void test() throws MalformedURLException, IOException {
		Organization organization = new Organization();
		organization.addressStreet = "Notečská 566";
		organization.addressCity = "Praha";
		organization.addressZipCode = "18000";
		
		GeoLocation geoLocation = new Geocoder().geocode(organization);
		assertEquals((Double)50.1255574d, geoLocation.lat);
		assertEquals((Double)14.4232859d, geoLocation.lng);
	}

	@Test
	public void testNotFound() throws MalformedURLException, IOException {
		Organization organization = new Organization();
		organization.addressStreet = "Hovnomrdská";
		
		GeoLocation geoLocation = new Geocoder().geocode(organization);
		assertNull(geoLocation);
	}
}

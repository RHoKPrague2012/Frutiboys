/**
 * 
 */
package jobs;

import java.util.List;

import models.GeoLocation;
import models.Organization;

import org.apache.log4j.Logger;

import play.db.jpa.JPA;
import play.jobs.Job;
import play.jobs.On;
import cz.rhok.prague.osf.governmentcontacts.geocoding.Geocoder;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
//@On("0 0 2 * * ?")
@On("0 0/1 * * * ?")
public class GeocodingJob extends Job {

	private static Logger log = play.Logger.log4j;
	
	@Override
	public void doJob() throws Exception {
		int page = 0;
		Geocoder geocoder = new Geocoder();
		while (true) {
			List<Organization> organizations = Organization.find("byLatitudeIsNull").fetch(page, 100);
			
			if (organizations.isEmpty()) {
				break;
			}
			
			for (Organization organization : organizations) {
				try {
					GeoLocation geoLocation = geocoder.geocode(organization);
					
					if (geoLocation != null) {
						organization.latitude = geoLocation.lat;
						organization.longitude = geoLocation.lng;
						
						organization.save();
						
						// save immediately
						JPA.em().flush();
					}
				} catch (Throwable e) {
					log.warn("Failed to geocode organization address: " + organization.getAddress(), e);
				}
			}
			
			page++;
		}
	}
	
}

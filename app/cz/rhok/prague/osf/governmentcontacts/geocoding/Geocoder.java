/**
 * 
 */
package cz.rhok.prague.osf.governmentcontacts.geocoding;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import models.GeoLocation;
import models.Organization;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
public class Geocoder {

	public GeoLocation geocode(Organization organization) throws MalformedURLException, IOException {
		StringBuilder requestUrl = new StringBuilder();
		
		requestUrl
				.append("http://maps.googleapis.com/maps/api/geocode/json?address=")
				.append(URLEncoder.encode(organization.getAddress(), "utf-8"))
				.append("&sensor=false");
				
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		InputStream stream = new URL(requestUrl.toString()).openStream();
		IOUtils.copy(stream, outputStream);
		
		String responseString = outputStream.toString();
		
		if (responseString.contains("ZERO_RESULTS")) {
			// nothing found
			return null;
		}
		
		int index = responseString.indexOf("\"location\"");
		int begin = responseString.indexOf("{", index);
		int end = responseString.indexOf("}", index);
		String latLngJson = responseString.substring(begin, end + 1);
		Gson gson = new Gson();
		Map<String, Double> jsonObject = gson.fromJson(latLngJson, new TypeToken<Map<String, Double>>() {}.getType());
		
		GeoLocation result = new GeoLocation();
		result.lat = jsonObject.get("lat");
		result.lng = jsonObject.get("lng");
		
		return result;
	}

}

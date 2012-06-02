package cz.rhok.prague.osf.governmentcontacts;

import java.io.IOException;
import java.util.Map;

import models.Organization;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Maps;

public class OrganyUzemniSamospravyScaper {

	private static Logger log = org.apache.log4j.Logger.getLogger("another.logger");
	
	
	/**
	 * @param pageUrl page which contains information to be scraped 
	 * 		  eg.http://seznam.gov.cz/ovm/regionDetail.do?path=KPRAHA&ref=obcan
	 * @return 
	 */
	public Organization scrape(String pageUrl) {
		
		Document doc = null;
		try {
			doc = Jsoup.connect(pageUrl).get();
		} catch (IOException ex) {
			log.error("Unable to parse " + pageUrl, ex);
		}

		Map<String, String> scrappedData = Maps.newHashMap();

		Elements dataRows = doc.select("#colData.institution .data tr");

		for (Element dataRow : dataRows) {

			String label = dataRow.select("th").text();
			String value = dataRow.select("td").text();
			
			// remove trailing ":" from label so 
			// instead of "Identifikátor datové schránky:" its just
			// "Identifikátor datové schránky"
			label = label.replace(":", "");
			
			scrappedData.put(label, value);

		}

		Organization organization = new Organization();

		organization.dataBoxId = scrappedData.get("Identifikátor datové schránky");

		return organization;
	}

}

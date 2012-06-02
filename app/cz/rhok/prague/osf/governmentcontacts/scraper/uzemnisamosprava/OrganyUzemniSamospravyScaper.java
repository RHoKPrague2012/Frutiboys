package cz.rhok.prague.osf.governmentcontacts.scraper.uzemnisamosprava;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Organization;

import org.apache.ivy.plugins.repository.ssh.Scp;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Maps;
import com.sun.xml.internal.bind.v2.TODO;

//TODO : michal pridat scrapovani adresy
public class OrganyUzemniSamospravyScaper {

	private static final Pattern MAIL_REGEX_PATTERN = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
	private static Logger log = play.Logger.log4j; //org.apache.log4j.Logger.getLogger("another.logger");
	
	/**
	 * @param pageUrl page which contains information to be scraped 
	 * 		  eg.http://seznam.gov.cz/ovm/regionDetail.do?path=KPRAHA&ref=obcan
	 * @return 
	 */
	public Organization scrape(String pageUrl) {
		
		log.debug("Start scraping of: " + pageUrl);
		
		Document doc = null;
		try {
			doc = Jsoup.connect(pageUrl).get();
		} catch (IOException ex) {
			log.error("Unable to parse: " + pageUrl, ex);
			throw new RuntimeException("Unable to parse: " + pageUrl, ex);
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
		organization.name = scrappedData.get("Název");
		organization.code = scrappedData.get("Kód organizace");
		organization.taxId = scrappedData.get("DIČ");
		organization.organizationId = scrappedData.get("IČ");
		organization.email = parseEmail(scrappedData);
		

		return organization;
	}

	private String parseEmail(Map<String, String> scrappedData) {
		String rawEmailData = scrappedData.get("E-mail"); // eg. posta@cityofprague.cz (podatelna)

		// extract only mail part from string if there are something else
		String email = "";
		
		Matcher matcher = MAIL_REGEX_PATTERN.matcher(rawEmailData);
		if (matcher.find()) {
			email = matcher.group(0);
		} else {			
			log.warn("Unable to parse e-mail. Parsed text : " + rawEmailData);
			//TODO: michal : log message doesn't contain context information (which input data causes this)
		}
						
		return email;
	}

}

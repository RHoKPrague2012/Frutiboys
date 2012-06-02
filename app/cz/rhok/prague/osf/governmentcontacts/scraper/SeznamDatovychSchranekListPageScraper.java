package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

public class SeznamDatovychSchranekListPageScraper {
	
	private static String DATA_BOXES_BASE_URL = "http://seznam.gov.cz/ovm/";
	
	private static Logger log = play.Logger.log4j; 

	/**
	 * @param listPageUrl napr. http://seznam.gov.cz/ovm/othersList.do?ref=obcan
	 * @return
	 */
	public List<URL> scrape(String listPageUrl) {

		Document doc;
		try {
			doc = Jsoup.connect(listPageUrl).get();
		} catch (IOException ex) {
			log.error("Unable to parse: " + listPageUrl, ex);
			throw new RuntimeException("Unable to parse: " + listPageUrl, ex);
		}

		Elements anchors = doc.select(".content tr td a");
		
		List<URL> detailPagesUrl = Lists.newArrayList();
		
		for (Element anchor : anchors) {

			String relativeUrl = anchor.attr("href");
			String urlAsString = DATA_BOXES_BASE_URL + relativeUrl;
			
			try {
				detailPagesUrl.add(new URL(urlAsString));
			} catch (MalformedURLException e) {
				log.error(
						"URL " + urlAsString + " seems malformed. This url will be skipped." +
						"List page url when this malformed link appeared : " + listPageUrl + ")");
			}
		}
		
		return detailPagesUrl;

	}

}

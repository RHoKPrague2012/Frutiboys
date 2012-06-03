package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

/**
 * Retrieve url of all detail pages on kraj detail page with all municipalities
 * listing eg. see 
 * <a href="http://seznam.gov.cz/ovm/regionDetail.do?path=KLIBEREC&listType=allMunicipality">http://seznam.gov.cz/ovm/regionDetail.do?path=KLIBEREC&listType=allMunicipality</a>.
 * 
 * @author Michal Bernhard michal@bernhard.cz / twitter @michalb_cz
 *         
 *
 */
public class SeznamDatovychSchranekMunicipalityListPageScraper 
										extends ScraperHelper
										implements DetailPageUrlRetriever {
	
	private static final Logger log = play.Logger.log4j; 

	/**
	 * @param listPageUrl napr. http://seznam.gov.cz/ovm/othersList.do?ref=obcan
	 * @return
	 */
	@Override
	public List<URL> extractDetailPageUrlsFrom(String url) {

		Document doc = getDocumentFor(url);

		Elements anchors = doc.select(".content li a");
		
		List<URL> detailPagesUrl = Lists.newArrayList();
		
		for (Element anchor : anchors) {

			String relativeUrl = anchor.attr("href");
			String urlAsString = getDataBoxBaseUrl() + relativeUrl;
			
			try {
				detailPagesUrl.add(new URL(urlAsString));
			} catch (MalformedURLException e) {
				log.error(
						"URL " + urlAsString + " seems malformed. This url will be skipped." +
						"List page url when this malformed link appeared : " + url + ")");
			}
		}
		
		return detailPagesUrl;

	}

}

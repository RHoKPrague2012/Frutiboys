package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Maps;

/**
 * Retrieve all links to each pagination (<< 1 2 3 4 5 >>) and give us link next series 
 * (link called "nasledujici")
 * 
 * @author Michal Bernhard michal@bernhard.cz / twitter @michalb_cz 
 *
 */
public class PaginableRecordsListPageRetriever extends ScraperHelper {

	public PaginableRecord getListPageLinks(String urlOfPaginableList) {

		Document doc = getDocumentFor(urlOfPaginableList);

		Map<Long, URL> pages = Maps.newHashMap();
		Elements paginatorLinks = doc.select(".paginator li a");

		for (Element paginatorLink : paginatorLinks) {
			String relativeUrl = paginatorLink.attr("href");
			String urlAsString = getDataBoxBaseUrl() + relativeUrl;
			try {
				URL pageUrl = new URL(urlAsString);
				Long pageNumber = Long.valueOf(paginatorLink.text());
				pages.put(pageNumber, pageUrl);
			} catch (MalformedURLException e) {
				throw new RuntimeException("Cannot obtain url for: " + urlAsString,  e);
			}
		}

		Element nextButton = doc.select(".paginator .next").get(0);

		URL nextPaginable = null;
		if (nextButton != null) {
			String nextRelativeLink = nextButton.attr("href");

			try {
				nextPaginable = new URL(getDataBoxBaseUrl() + nextRelativeLink);
			} catch (MalformedURLException e) {
				throw new RuntimeException(
							"Cannot obtain url from element " + nextButton + ".",  e);
			}
		}

		return new PaginableRecord(pages, nextPaginable);

	}


	public static class PaginableRecord {

		private final Map<Long, URL> pages = Maps.newHashMap();
		private final URL nextPaginable;

		public PaginableRecord(Map<Long, URL> pages, URL nextPaginable) {
			this.pages.putAll(pages);
			this.nextPaginable = nextPaginable;
		}

		public Map<Long, URL> getPages() {
			return pages;
		}
		public URL getNextPaginable() {
			return nextPaginable;
		}




	}

}



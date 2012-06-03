package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ScraperHelper {
	
	private static String DATA_BOXES_BASE_URL = "http://seznam.gov.cz/ovm/";
	
	private static final Logger log = play.Logger.log4j; 

	
	
	/**
	 * @param url 
	 * @return 
	 * @throws UnableToConnectToServer when connection is unavailable
	 */
	protected Document getDocumentFor(URL url) {
		Document doc;
		
		try {
			doc = Jsoup.connect(url.toExternalForm()).get();
			return doc;
		} catch (IOException ex) {
			
			if (ex instanceof SocketTimeoutException) {
				throw new UnableToConnectToServer("Unable to connect to: " + url, ex);
			}
			
			log.error("Unable to parse: " + url, ex);
			throw new RuntimeException("Unable to parse: " + url, ex);
		}
		
	}



	protected Document getDocumentFor(String url) {
		try {
			return getDocumentFor(new URL(url));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getDataBoxBaseUrl() {
		return DATA_BOXES_BASE_URL;
	}

	

}

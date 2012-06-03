package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.net.URL;
import java.util.List;

public interface DetailPageUrlRetriever {
	
	List<URL> extractDetailPageUrlsFrom(String url);

}

package jobs;

import java.net.URL;

import play.Logger;

public class KrajMunicipalitiesScraperJob extends AbstractScraperJob {

	private final URL urlOfKrajDetailPage;

	public KrajMunicipalitiesScraperJob(URL urlOfKrajDetailPage) {
		this.urlOfKrajDetailPage = urlOfKrajDetailPage;
	}
	
	@Override
	public void doJob() throws Exception {
		
		Logger.info("Scraping of " + urlOfKrajDetailPage + " has started.");
		
		
	}
}

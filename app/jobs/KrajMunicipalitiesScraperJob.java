package jobs;

import java.net.URL;

import cz.rhok.prague.osf.governmentcontacts.scraper.SeznamDatovychSchranekDetailPageScaper;

import play.Logger;
import play.jobs.Job;

public class KrajMunicipalitiesScraperJob extends Job {

	private final URL urlOfKrajDetailPage;

	public KrajMunicipalitiesScraperJob(URL urlOfKrajDetailPage) {
		this.urlOfKrajDetailPage = urlOfKrajDetailPage;
	}
	
	@Override
	public void doJob() throws Exception {
		
		Logger.info("Scraping of " + urlOfKrajDetailPage + " has started.");
		
		
	}
}

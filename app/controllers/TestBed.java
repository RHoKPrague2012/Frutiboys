package controllers;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

import jobs.GeocodingJob;
import jobs.KrajeScraperJob;
import models.Organization;

import org.apache.commons.lang.time.StopWatch;

import play.mvc.Controller;
import play.mvc.With;

import com.google.common.collect.Lists;

import cz.rhok.prague.osf.governmentcontacts.scraper.SeznamDatovychSchranekDetailPageScaper;
import cz.rhok.prague.osf.governmentcontacts.scraper.SeznamDatovychSchranekListPageScraper;

@With(Secure.class)
public class TestBed extends Controller {

    public static void index() {
        render();
    }
    
    public static void scrapeAndSaveSome() {
    	
    	String listPageUrl = params.get("listPageUrl");
    	
    	SeznamDatovychSchranekListPageScraper listPageScraper = 
    									new SeznamDatovychSchranekListPageScraper();
    	
    	SeznamDatovychSchranekDetailPageScaper detailPageScaper = 
    									new SeznamDatovychSchranekDetailPageScaper();
    	
    	List<URL> detailPageUrls = Lists.newArrayList();
    	
    	StopWatch watch = new StopWatch();
    	
    	watch.start();
    	
    	boolean repeatLoop = true;
		while (repeatLoop) {
			try {
				detailPageUrls.addAll(listPageScraper.extractDetailPageUrlsFrom(listPageUrl));
				Thread.sleep(1000 /* ms */); // pause before next request (to not spam the web page too much)
				repeatLoop = false;
			} catch (RuntimeException ex) {
				if ( ! (ex.getCause() instanceof SocketTimeoutException)) {
					repeatLoop = false;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
    	
    	for (URL url : detailPageUrls) {
    		Organization organization = detailPageScaper.scrape(url.toString());

    		Organization existingOrganization = Organization.find("byDataBoxId", organization.dataBoxId).first();
    		
    		if (existingOrganization != null) {
    			existingOrganization.copyStateFrom(organization);
    			organization = existingOrganization;
    		}
    		
    		organization.save();
		}
    	
    	watch.stop();
    	
    	Long timeElapsed = watch.getTime();
		renderText("Succesfully saved (in " + timeElapsed + " ms)");
    
    }
    
    public static void startScrapeJob() {
    	new KrajeScraperJob().now();
    }

    public static void startGeocodingJob() {
    	new GeocodingJob().now();
    }

}

package jobs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import play.jobs.Job;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cz.rhok.prague.osf.governmentcontacts.helper.RepeatOnTimeoutTask;
import cz.rhok.prague.osf.governmentcontacts.scraper.PaginableRecordsListPageRetriever;
import cz.rhok.prague.osf.governmentcontacts.scraper.PaginableRecordsListPageRetriever.PaginableRecord;
import cz.rhok.prague.osf.governmentcontacts.scraper.SeznamDatovychSchranekDetailPageScaper;
import cz.rhok.prague.osf.governmentcontacts.scraper.SeznamDatovychSchranekKrajeListPageScraper;
import cz.rhok.prague.osf.governmentcontacts.scraper.SeznamDatovychSchranekMunicipalityListPageScraper;

public class KrajeScraperJob extends Job {

	private static final String GET_PARAM_TO_LIST_ALL_MUNI_IN_KRAJ = "&listType=allMunicipality";
	private static final String KRAJS_LISTING_PAGE = "http://seznam.gov.cz/ovm/regionList.do";

	@Override
	public void doJob() throws Exception {

		final SeznamDatovychSchranekKrajeListPageScraper krajsListPageScraper = 
				new SeznamDatovychSchranekKrajeListPageScraper();

		RepeatOnTimeoutTask<List<URL>> repeatingExtractDetailPageForKrajsPage = new RepeatOnTimeoutTask<List<URL>>() {

					@Override
					public List<URL> doTask() {
						return krajsListPageScraper.extractDetailPageUrlsFrom(KRAJS_LISTING_PAGE);
					}

		};
		List<URL> krajDetailPageUrl = repeatingExtractDetailPageForKrajsPage.call();

		List<URL> krajDetailPageWithAllMuniListedUrl = 				
				Lists.newArrayList(
						Collections2.transform(
								krajDetailPageUrl,
								convertToUrlWithAllMunicipalityListing()));

		PaginableRecordsListPageRetriever listPagesRetriever = new PaginableRecordsListPageRetriever();

		for (URL krajDetailUrl : krajDetailPageWithAllMuniListedUrl) {

			/* get all urls for list pages of municipalities */
			Map<Long, URL> allPages = Maps.newHashMap();

			URL nextPaginable = krajDetailUrl;
			while(nextPaginable != null) {
				String url = nextPaginable.toExternalForm();
				PaginableRecord paginable = listPagesRetriever.getListPageLinks(url);
				allPages.putAll(paginable.getPages());
			}

			SeznamDatovychSchranekMunicipalityListPageScraper municipalityListPageScraper = 
					new SeznamDatovychSchranekMunicipalityListPageScraper();

			for(URL municipalityListPage : allPages.values()) {

				List<URL> detailPageLinks = 
						municipalityListPageScraper.extractDetailPageUrlsFrom(municipalityListPage.toExternalForm());

				SeznamDatovychSchranekDetailPageScaper detailPageScaper = 
						new SeznamDatovychSchranekDetailPageScaper();

				for (URL municipalityDetailPageUrl : detailPageLinks) {
					detailPageScaper.scrape(municipalityDetailPageUrl.toExternalForm());
				}

			}


		}



	}

	private Function<URL, URL> convertToUrlWithAllMunicipalityListing() {
		return new Function<URL, URL>() {

			@Override
			public URL apply(URL urlToBeConverted) {
				String urlToBeConvertedAsString = urlToBeConverted.toExternalForm();
				String newUrl = urlToBeConvertedAsString + GET_PARAM_TO_LIST_ALL_MUNI_IN_KRAJ;
				try {
					return new URL(newUrl);
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}

		};
	}

}

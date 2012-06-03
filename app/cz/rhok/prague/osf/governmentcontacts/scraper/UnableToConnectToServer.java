package cz.rhok.prague.osf.governmentcontacts.scraper;

import java.io.IOException;

public class UnableToConnectToServer extends RuntimeException {

	public UnableToConnectToServer(String message, Exception ex) {
		super(message, ex);
	}

	
}

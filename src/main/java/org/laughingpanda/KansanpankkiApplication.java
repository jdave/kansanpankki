package org.laughingpanda;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class KansanpankkiApplication extends WebApplication {
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}
}

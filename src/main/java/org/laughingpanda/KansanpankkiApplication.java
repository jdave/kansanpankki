package org.laughingpanda;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class KansanpankkiApplication extends WebApplication {
	@Override
	public Class getHomePage() {
		return HomePage.class;
	}
}

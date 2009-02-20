package org.laughingpanda.jetty;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class WebDriverJettyStarter extends Jetty {
    private static final int PORT = 8082;
	private static Server server;
    
    public static void main(String... args) {
        server = new WebDriverJettyStarter().start();
    }
    
    @Override
    protected Connector[] getConnectors() {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(PORT);
        return new Connector[] { connector };
    }
}

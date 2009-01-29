package org.laughingpanda;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.nio.SelectChannelConnector;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class WebDriverJettyStarter extends Jetty {
    private static final int PORT = 8082;
    
    public static void main(String... args) {
        new WebDriverJettyStarter().start();
    }
    
    @Override
    protected Connector[] getConnectors() {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(PORT);
        return new Connector[] { connector };
    }
}

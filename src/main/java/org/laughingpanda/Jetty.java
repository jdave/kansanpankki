package org.laughingpanda;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class Jetty {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        SelectChannelConnector connector = new  SelectChannelConnector();
        connector.setPort(8080);
        server.setConnectors(new Connector[] { connector });

        WebAppContext web = new WebAppContext();
        web.setContextPath("/kansanpankki");
        web.setWar("src/main/webapp");
        server.addHandler(web);
        server.start();
        server.join();
    }
}

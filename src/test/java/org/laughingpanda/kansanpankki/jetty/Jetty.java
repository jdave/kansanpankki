package org.laughingpanda.kansanpankki.jetty;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class Jetty {
    private static final int PORT = 8080;
    protected static final String CONTEXT_PATH = "/kansanpankki";
    private static final String WAR = "src/main/webapp";
	private Server server;

    public static void main(String... args) throws Exception {
        new Jetty().start();
    }
    
    public Server start() {
        server = new Server();
        server.setConnectors(getConnectors());
        server.addHandler(getWebApplicationContext());
        startServer();
        joinServer();
        return server;
    }
    
    private void joinServer() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    server.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    
    public synchronized void stop() {
        if (server == null) {
            return;
        }
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        server = null;
    }

    protected Connector[] getConnectors() {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(PORT);

        return new Connector[] { connector };
    }
    
    private void startServer() {
        try {
            server.start();
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
    
    protected WebAppContext getWebApplicationContext() {
        WebAppContext app = new WebAppContext();
        app.setContextPath(CONTEXT_PATH);
        app.setWar(WAR);
        return app;
    }
}
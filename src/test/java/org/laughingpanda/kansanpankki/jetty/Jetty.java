/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            System.out.println("Stopped.");
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
            System.out.println("Kansanpankki now running at http://localhost:" + PORT + CONTEXT_PATH + "/");
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
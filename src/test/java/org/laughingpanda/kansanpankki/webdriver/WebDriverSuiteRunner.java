package org.laughingpanda.kansanpankki.webdriver;

import jdave.webdriver.WebDriverGroupRunner;
import jdave.webdriver.WebDriverHolder;
import org.laughingpanda.kansanpankki.jetty.WebDriverJettyStarter;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class WebDriverSuiteRunner extends WebDriverGroupRunner {
    private WebDriverJettyStarter server = new WebDriverJettyStarter();

    public WebDriverSuiteRunner(Class<?> suite) {
        super(suite);
    }

    @Override
    protected void onBeforeRun() {
        server.start();
        super.onBeforeRun();
    }

    @Override
    protected void onAfterRun() {
        if (WebDriverHolder.get() != null) {
            super.onAfterRun();
        } else {
            System.out.println("WebDriver was null in WebDriverSuiteRunner");
        }
        server.stop();
    }
}

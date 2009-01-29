package org.laughingpanda;

import jdave.Specification;
import jdave.webdriver.WebDriverHolder;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class KansanpankkiWebDriverSpecification<T> extends Specification<T> {
	@Override
	public void create() throws Exception {
		new WebDriverJettyStarter().start();
	}
    public void openBaseUrl() {
        WebDriverHolder.get().navigate().to("http://localhost:8082/kansanpankki/");
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			//lol
		}
    }
    
    public boolean textContainsInPage(String text) {
        return WebDriverHolder.get().getPageSource().contains(text);
    }
}

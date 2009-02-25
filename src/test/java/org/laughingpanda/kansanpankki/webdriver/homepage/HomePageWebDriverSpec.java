package org.laughingpanda.kansanpankki.webdriver.homepage;

import jdave.Group;
import jdave.webdriver.WebDriverSpecRunner;

import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.webdriver.KansanpankkiWebDriverSpecification;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(WebDriverSpecRunner.class)
@Group(value = { "kansanpankki" })
public class HomePageWebDriverSpec extends KansanpankkiWebDriverSpecification<HomePageElements> {
	public class AnyHomePage {
		public HomePageElements create() {
			openBaseUrl();
			return new HomePageElements();
		}
		
		public void showsHomePage() {
			specify(textContainsInPage("Kansan"));
//			specify(textContainsInPage("Salary"));
		}
	}
}

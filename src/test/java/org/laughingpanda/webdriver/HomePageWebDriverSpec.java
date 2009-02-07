package org.laughingpanda.webdriver;

import jdave.webdriver.WebDriverSpecRunner;

import org.junit.runner.RunWith;
import org.laughingpanda.accounts.HomePage;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(WebDriverSpecRunner.class)
public class HomePageWebDriverSpec extends KansanpankkiWebDriverSpecification<HomePage> {
	public class AnyHomePage {
		public HomePage create() {
			openBaseUrl();
			return null;
		}
		
		public void showsHomePage() {
			specify(textContainsInPage("KansanPankki"));
			specify(textContainsInPage("Salary"));
		}
	}
}

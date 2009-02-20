package org.laughingpanda.kansanpankki.webdriver.homepage;

import jdave.Group;
import jdave.webdriver.WebDriverSpecRunner;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.accounts.HomePage;
import org.laughingpanda.kansanpankki.webdriver.KansanpankkiWebDriverSpecification;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(WebDriverSpecRunner.class)
@Group(value = { "kansanpankki" })
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

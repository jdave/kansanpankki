package org.laughingpanda.webdriver.accountspanel;

import jdave.Group;
import jdave.webdriver.WebDriverSpecRunner;

import org.junit.runner.RunWith;
import org.laughingpanda.webdriver.KansanpankkiWebDriverSpecification;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(WebDriverSpecRunner.class)
@Group(value = { "kansanpankki" })
public class AccountsPanelWebDriverSpec extends KansanpankkiWebDriverSpecification<AccountsPanelElements> {
	public class PanelWithAccounts {
		public AccountsPanelElements create() {
			openBaseUrl();
			return new AccountsPanelElements();
		}
		
		public void containsAccountLabels() {
	        specify(context.getAccountLabels().size(), does.equal(2));
		}
		
		public void containsAccountLinks() {
			specify(context.getAccountLinks().size(), does.equal(2));
		}
	}
}
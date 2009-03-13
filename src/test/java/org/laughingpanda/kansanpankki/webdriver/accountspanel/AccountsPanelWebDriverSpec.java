package org.laughingpanda.kansanpankki.webdriver.accountspanel;

import jdave.Group;
import jdave.webdriver.WebDriverHolder;
import jdave.webdriver.WebDriverSpecRunner;

import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.webdriver.KansanpankkiWebDriverSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
		
		public void newAccountCanBeAdded() {
			WebElement accountNumber = WebDriverHolder.get().findElement(By.id("accountNumber"));
			accountNumber.sendKeys("1111-2222");
			WebElement addAccount = WebDriverHolder.get().findElement(By.name("addAccountButton"));
			addAccount.click();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				//
			}
			specify(context.getAccountLinks().size(), does.equal(3));
		}
	}
	
	public class PanelWhenAccountLinkIsClicked {
		public AccountsPanelElements create() {
			openBaseUrl();
			AccountsPanelElements accountsPanelElements = new AccountsPanelElements();
			accountsPanelElements.clickFirstAccountLink();
			return accountsPanelElements;
		}
		
		public void showsSalaryAccount() {
			specify(textContainsInPage("Salary account"));
		}
	}
}
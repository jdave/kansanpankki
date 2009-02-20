package org.laughingpanda.kansanpankki.webdriver.accountspanel;

import java.util.List;
import jdave.webdriver.WebDriverHolder;
import org.laughingpanda.kansanpankki.webdriver.BaseElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class AccountsPanelElements extends BaseElements {
	
	public List<WebElement> getAccountLabels() {
		WebElement accountList = WebDriverHolder.get().findElement(By.id("accounts"));
		List<WebElement> accounts = accountList.findElements(By.id("account"));
		return accounts;
	}
	
	public List<WebElement> getAccountLinks() {
		WebElement accountList = WebDriverHolder.get().findElement(By.id("accounts"));
		List<WebElement> accounts = accountList.findElements(By.id("account"));
		return accounts;
	}
}

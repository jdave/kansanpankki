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
        return accountList.findElements(By.id("account"));
	}
	
	public List<WebElement> getAccountLinks() {
		WebElement accountList = WebDriverHolder.get().findElement(By.id("accounts"));
        return accountList.findElements(By.id("account"));
	}
	
	public void clickFirstAccountLink() {
		getAccountLinks().get(0).click();
	}

    public void enterNewAccountNumber(String newAccountNumber) {
        WebElement accountNumberElement = WebDriverHolder.get().findElement(By.id("accountNumber"));
        accountNumberElement.sendKeys(newAccountNumber);
    }

    public void clickAddAccountButton() {
        WebElement addAccount = WebDriverHolder.get().findElement(By.name("addAccountButton"));
        addAccount.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //
        }
    }
}

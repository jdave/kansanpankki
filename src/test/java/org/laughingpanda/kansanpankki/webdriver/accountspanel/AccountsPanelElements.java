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
package org.laughingpanda.kansanpankki.webdriver.accountspanel;

import java.util.List;
import jdave.webdriver.WebDriverHolder;
import jdave.webdriver.elements.Find;
import jdave.webdriver.elements.Link;
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
        Link addAccount = Find.link(By.name("addAccountButton"));
        addAccount.click();
    }
}

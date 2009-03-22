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
import jdave.Group;
import jdave.webdriver.WebDriverSpecRunner;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.webdriver.KansanpankkiWebDriverSpecification;
import org.openqa.selenium.WebElement;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(WebDriverSpecRunner.class)
@Group(value = {"kansanpankki"})
public class AccountsPanelWebDriverSpec extends KansanpankkiWebDriverSpecification<AccountsPanelElements> {
    public class PanelWithAccounts {
        public AccountsPanelElements create() {
            openBaseUrl();
            return new AccountsPanelElements();
        }

        public void containsAccountLinks() {
            specify(context.getAccountLinks().size(), does.equal(2));
        }

        public void newAccountCanBeAdded() {
            context.enterNewAccountNumber("1111-2222");
            context.clickAddAccountButton();
            specify(context.getAccountLinks().size(), does.equal(3));
        }
        
        public void containsAccountLabels() {
            specify(context.getAccountLabels().get(1).getText(), does.equal("Salary account"));
            specify(context.getAccountLabels().get(0).getText(), does.equal("Salary account"));
        }
        
        public void containsBalanceLabels() {
        	specify(context.getBalanceLabels().get(1).getText(), does.equal("9500 euros"));
        	specify(context.getBalanceLabels().get(0).getText(), does.equal("0 euros"));
        }
        
        public void amountToTransferTextFieldsThatHasZeroBalanceIsNotEnabled() {
            WebElement transferAmountElement = context.getTransferAmountElementOfRow(1);
            specify(transferAmountElement.isEnabled(), does.equal(false));
        }

        public void amountToTransferTextFieldsThatHasBalanceIsEnabled() {
            WebElement transferAmountElement = context.getTransferAmountElementOfRow(2);
            specify(transferAmountElement.isEnabled());
        }
        
        public void showsTargetAccountNumbers() {
            context.getTransferAmountTextBoxOfRow(2).type("100");
            List<WebElement> transferTexts = context.getTransferLinkTexts();
			specify(transferTexts.get(0).getText(), does.equal("Transfer 100 euros to 9500-12345"));
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
            specify(textContainsInPage("KansanPankki - Salary account"));
        }
    }
}
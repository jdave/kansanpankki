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

import jdave.Group;
import jdave.webdriver.WebDriverSpecRunner;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.webdriver.KansanpankkiWebDriverSpecification;

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
            context.enterNewAccountNumber("1111-2222");
            context.clickAddAccountButton();
			specify(context.getAccountLinks().size(), does.equal(3));
            specify(context.getAccountLabels().get(2).getText(), does.equal("Salary account"));
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
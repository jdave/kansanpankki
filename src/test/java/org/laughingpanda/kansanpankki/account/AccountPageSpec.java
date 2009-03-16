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
package org.laughingpanda.kansanpankki.account;

import java.util.List;
import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.DummyHomePage;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountTransaction;
import org.laughingpanda.kansanpankki.domain.Money;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountPageSpec extends ComponentSpecification<AccountPage, Account> {
    private Account account = new Account("1234-5678");
	private DummyHomePage dummyPage;

	public class Any {
        public AccountPage create() {
            return startComponent(new Model<Account>(account));
        }

        public void pageIsRendered() {
            specify(context, isNotNull());
        }

        public void containsAccountIdLabel() {
            Label accountIdLabel = selectFirst(Label.class, "accountId").from(context);
            specify(accountIdLabel.getDefaultModelObject(), does.equal(account));
        }

        public void containsBackLink() {
            specify(backLink(), isNotNull());
        }

        public void whenBackLinkIsClickedRedirectsToSameHomePageInstance() {
        	wicket.executeAjaxEvent(backLink(), "onclick");
        	specify(wicket.getLastRenderedPage(), does.equal(dummyPage));
        }

        public void containsListOfTransactions() {
            List<ListItem<AccountTransaction>> transactions = selectAll(ListItem.class).from(context);
            specify(transactions.size(), does.equal(2));
            specify(selectFirst(Label.class).from(transactions.get(0)).getDefaultModelObjectAsString(),
                    does.equal("500 euros from 1111-1111 to 1234-5678"));
            specify(selectFirst(Label.class).from(transactions.get(1)).getDefaultModelObjectAsString(),
                    does.equal("100 euros from 1234-5678 to 2222-2222"));
        }
    }

    private AjaxLink<Void> backLink() {
    	return selectFirst(AjaxLink.class, "back").from(context);
    }

    @Override
    protected void onCreate() {
        Account sourceAccount = new Account("1111-1111").save(Money.euros(1000));
        Account targetAccount = new Account("2222-2222");
        sourceAccount.transfer(Money.euros(500)).to(account);
        account.transfer(Money.euros(100)).to(targetAccount);
    }

    @Override
    protected AccountPage newComponent(String id, IModel<Account> model) {
        dummyPage = new DummyHomePage();
		return new AccountPage(model, dummyPage);
    }
}

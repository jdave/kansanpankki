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
package org.laughingpanda.kansanpankki.accounts;

import java.util.List;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.tester.FormTester;
import org.jmock.Expectations;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.account.AccountPage;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountRepository;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountsPanelSpec extends ComponentSpecification<AccountsPanel, Account> {
    private AccountRepository accountRepository = mock(AccountRepository.class);

    public class PanelContainingMultipleAccounts {
        public AccountsPanel create() {
            return startComponent();
        }

        public void containsDataView() {
            specify(accountsView(), isNotNull());
        }

        public void containsAccountIdLabels() {
            specify(accountLabels().size(), does.equal(2));
        }

        public void containsAccountLinks() {
            specify(accountLinks().size(), does.equal(2));
        }

        public void accountLinkCanBeClicked() {
            wicket.executeAjaxEvent(accountLinks().get(0), "onclick");
        }

        public void newAccountCanBeAdded() {
            checking(new Expectations() {{
                one(accountRepository).addAccount(new Account("1111-2222"));
            }});
            FormTester form = wicket.newFormTester(form().getPageRelativePath());
            form.setValue("accountNumber", "1111-2222");
            wicket.executeAjaxEvent(selectFirst(Button.class, "addAccountButton").from(context).getPageRelativePath(), "onclick");
        }
    }

    public class PanelWhenAccountLinkIsClicked {
        public AccountsPanel create() {
            AccountsPanel panel = startComponent();
            wicket.executeAjaxEvent(selectAll(AjaxLink.class, "accountLink").from(panel).get(0), "onclick");
            return panel;
        }

        public void isInAccountPage() {
            specify(wicket.getLastRenderedPage().getClass(), does.equal(AccountPage.class));
        }
    }

    private List<? extends Component> accountLinks() {
        return selectAll(AjaxLink.class, "accountLink").<AjaxLink<?>>from(accountsView());
    }

    private List<? extends Component> accountLabels() {
        return selectAll(Label.class, "accountId").<Label>from(accountsView());
    }

    private DataView<Account> accountsView() {
        return selectFirst(DataView.class, "accounts").from(context);
    }
    
    private Form<Account> form() {
    	return selectFirst(Form.class, "newAccountForm").from(context);
    }

    @Override
    protected AccountsPanel newComponent(String id, IModel<Account> model) {
        AccountsPanel accountsPanel = new AccountsPanel(id);
        accountsPanel.accountRepository = accountRepository;
        return accountsPanel;
    }
}

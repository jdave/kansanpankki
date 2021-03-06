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

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountTransaction;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class AccountPage extends WebPage {
    private final Page accountsPage;

    public AccountPage(IModel<Account> accountModel, Page accountsPage) {
        this.accountsPage = accountsPage;
        add(new AccountIdLabel("accountHeaderId", accountModel));
        add(new AccountIdLabel("accountId", accountModel));
        add(new AjaxLink<Void>("back") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(AccountPage.this.accountsPage);
            }
        });
        add(new ListView<AccountTransaction>("transactions", accountModel.getObject().getTransactions()) {
            @Override
            protected void populateItem(ListItem<AccountTransaction> accountTransactionListItem) {
                accountTransactionListItem.add(new Label("transaction", accountTransactionListItem.getModel()));
            }
        });
    }

    private final static class AccountIdLabel extends Label {
        public AccountIdLabel(String id, IModel<Account> model) {
            super(id, model);
        }
    }
}


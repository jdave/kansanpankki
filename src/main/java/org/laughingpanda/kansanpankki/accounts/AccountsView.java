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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.laughingpanda.kansanpankki.account.AccountPage;
import org.laughingpanda.kansanpankki.domain.Account;

public class AccountsView extends DataView<Account> {
    AccountsView(String id, IDataProvider<Account> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected void populateItem(Item<Account> item) {
        final IModel<Account> model = new Model<Account>(item.getModelObject());
        AjaxLink<Account> accountLink = new AjaxLink<Account>("accountLink", model) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(new AccountPage(getModel(), getPage()));
            }
        };
        accountLink.add(new Label("accountId", item.getDefaultModelObjectAsString()));
        item.add(accountLink);
        item.add(new TextField<Integer>("amountToTransfer") {
            @Override
            public boolean isEnabled() {
                return !(model.getObject()).isEmpty();
            }
        });
    }
}

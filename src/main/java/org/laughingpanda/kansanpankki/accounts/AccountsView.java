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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.laughingpanda.kansanpankki.account.AccountPage;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.Money;

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
        item.add(new Label("balance", model.getObject().getBalance().toString()));
        item.add(accountLink);
        final PossibleTargetAccounts targetAccounts = new PossibleTargetAccounts(model);
        item.add(targetAccounts);
        final TextField<Integer> amountToTransfer = new TextField<Integer>("amountToTransfer", new Model<Integer>()) {
            @Override
            public boolean isEnabled() {
                return !(model.getObject()).isEmpty();
            }
            
            // Wicket uses disabled="disabled" attribute for disabled text fields
            // However, WebDriver checks disabled state by checking if there is
            // fields having disabled attribute set to "true"
            @Override
            protected void onDisabled(ComponentTag tag) {
            	tag.put("disabled", "true");
            }
        };
        amountToTransfer.setType(Integer.class);
        amountToTransfer.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                if (amountToTransfer.getModelObject() == null) {
                    targetAccounts.setVisible(false);
                } else {
                    targetAccounts.setVisible(true);
                    targetAccounts.setAmountToTransfer(Money.euros(amountToTransfer.getModelObject()));
                }
                target.addComponent(targetAccounts);
            }
        });
        item.add(amountToTransfer);
    }

    private class PossibleTargetAccounts extends Panel {
        private Money amountToTransfer;

        private PossibleTargetAccounts(final IModel<Account> sourceAccountModel) {
            super("targetAccounts");
            add(new ListView<Account>("list", getOtherAccounts(sourceAccountModel)) {
                @Override
                protected void populateItem(final ListItem<Account> accountListItem) {
                    accountListItem.add(new Label("targetAmount", new AbstractReadOnlyModel<Money>() {
                        @Override
                        public Money getObject() {
                            return amountToTransfer;
                        }
                    }));
                    IModel<Account> targetAccountModel = accountListItem.getModel();
                    AjaxLink<Account> transferLink = new TransferLink(targetAccountModel, sourceAccountModel, amountToTransfer);
                    accountListItem.add(transferLink);
                    transferLink.add(new Label("targetAccountId", accountListItem.getDefaultModel()));
                }
            });
            setVisible(false);
            setOutputMarkupPlaceholderTag(true);
        }

		private List<Account> getOtherAccounts(
				final IModel<Account> sourceAccountModel) {
			List<Account> accounts = new ArrayList<Account>();
            Iterator<IModel<Account>> allAccounts = getItemModels();
            while (allAccounts.hasNext()) {
                accounts.add(allAccounts.next().getObject());
            }
            accounts.remove(sourceAccountModel.getObject());
			return accounts;
		}

        public void setAmountToTransfer(Money amountToTransfer) {
            this.amountToTransfer = amountToTransfer;
        }
    }
}

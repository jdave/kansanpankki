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

import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.jmock.Expectations;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountRepository;
import org.laughingpanda.kansanpankki.domain.Money;

@RunWith(JDaveRunner.class)
public class AccountsViewSpec extends ComponentSpecification<AccountsView, Void> {
    private AccountRepository repository = mock(AccountRepository.class);
    private Account emptyAccount = new Account("12345-6789");
    private Account accountWithMoney = new Account("9500-9500");

    public class RowOfEmptyAccount {
        private Item<Account> row;

        public AccountsView create() {
            AccountsView accountsView = startComponent();
            row = selectFirst(Item.class).which(is(emptyAccount)).from(accountsView);
            return accountsView;
        }

        public void hasTransferAmountTextFieldDisabled() {
            TextField<?> amountToTransfer = selectFirst(TextField.class).from(row);
            specify(amountToTransfer.isEnabled(), does.equal(false));
        }
    }

    public class RowOfAccountWith9500Euros {
        private Item<Account> row;

        public AccountsView create() {
            AccountsView accountsView = startComponent();
            row = selectFirst(Item.class).which(is(accountWithMoney)).from(accountsView);
            return accountsView;
        }

        public void hasTransferAmountTextFieldEnabled() {
            TextField<?> amountToTransfer = selectFirst(TextField.class).from(row);
            specify(amountToTransfer.isEnabled());
        }
        
        public void containsBalanceLabel() {
        	List<Label> balanceLabels = selectAll(Label.class, "balance").from(context);
        	specify(balanceLabels.get(1).getDefaultModelObjectAsString(), does.equal("9500 euros"));
        }
    }

    @Override
    protected AccountsView newComponent(String id, IModel<Void> iModel) {
        accountWithMoney.save(Money.euros(9500));
        checking(new Expectations() {{
            atLeast(1).of(repository).findAllAccounts(); will(returnValue(Arrays.asList(emptyAccount, accountWithMoney)));
        }});
        return new AccountsView(id, new AccountsDataProvider(repository));
    }
}

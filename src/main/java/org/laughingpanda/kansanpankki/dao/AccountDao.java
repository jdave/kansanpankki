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
package org.laughingpanda.kansanpankki.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountRepository;
import org.laughingpanda.kansanpankki.domain.Money;

public class AccountDao implements AccountRepository, Serializable {
    private List<Account> accounts = new ArrayList<Account>();
    {
        Account emptyAccount = new Account("9500-12345");
        accounts.add(emptyAccount);
        Account accountWithMoney = new Account("9500-56789");
        accountWithMoney.save(Money.euros(9500));
        accounts.add(accountWithMoney);
        accountWithMoney.transfer(Money.euros(5000)).to(emptyAccount);
        emptyAccount.transfer(Money.euros(2000)).to(accountWithMoney);
        emptyAccount.transfer(Money.euros(3000)).to(accountWithMoney);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accounts;
    }

    @Override
    public void addAccount(Account account) {
        if (accounts.contains(account)) {
            throw new IllegalArgumentException("There is already an account with id " + account.getAccountId());
        }
        accounts.add(account);
    }
}

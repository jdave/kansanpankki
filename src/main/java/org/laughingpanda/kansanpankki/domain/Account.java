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
package org.laughingpanda.kansanpankki.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
	private String accountId;
	private Money balance = Money.euros(0);
    private List<AccountTransaction> transactions = new ArrayList<AccountTransaction>();

    public Account(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}
	
	public Money getBalance() {
        return balance;
    }

    public Account save(Money money) {
        balance = balance.add(money);
        return this;
    }

    public void withdraw(Money money) {
        if (balance.lessThan(money)) {
            throw new IllegalArgumentException("Cannot withdraw " + money +
                    " from an account that has only " + balance);
        }
        balance = balance.subtract(money);
    }

    public boolean isEmpty() {
        return balance.isEmpty();
    }

    public Transfer transfer(Money amountToTransfer) {
        return new Transfer(amountToTransfer);
    }

    public List<AccountTransaction> getTransactions() {
        return new ArrayList<AccountTransaction>(transactions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public class Transfer {
        private final Money amountToTransfer;

        public Transfer(Money amountToTransfer) {
            this.amountToTransfer = amountToTransfer;
        }

        public void to(Account targetAccount) {
            AccountTransaction transaction = AccountTransaction.transfer(Account.this, targetAccount, amountToTransfer);
            transactions.add(transaction);
            targetAccount.transactions.add(transaction);
        }
    }
}

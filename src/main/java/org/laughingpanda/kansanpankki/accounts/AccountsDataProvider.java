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

import java.util.Iterator;
import java.util.List;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountRepository;

final class AccountsDataProvider implements IDataProvider<Account> {
	private AccountRepository accountRepository;
	
	public AccountsDataProvider(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
    public Iterator<Account> iterator(int first, int count) {
	    return allAccounts().iterator();
	}

	@Override
    public int size() {
	    return allAccounts().size();
	}

	@Override
    public IModel<Account> model(Account object) {
	    return new Model<Account>(object);
	}

	@Override
    public void detach() {
	}

	private List<Account> allAccounts() {
		return accountRepository.findAllAccounts();
	}
}

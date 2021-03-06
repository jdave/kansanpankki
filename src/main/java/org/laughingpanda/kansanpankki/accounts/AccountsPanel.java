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
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.laughingpanda.kansanpankki.dao.AccountDao;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountRepository;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class AccountsPanel extends Panel implements TransferListener {
	AccountRepository accountRepository = new AccountDao();

    public AccountsPanel(String id) {
		super(id);
		setOutputMarkupId(true);
        add(new AccountsView("accounts", new AccountsDataProvider(accountRepository)));
		add(new AccountForm("newAccountForm"));
	}

    private final class AccountForm extends Form<Void> {
    	private AccountForm(String id) {
    		super(id);
    		final TextField<String> accountNumberField = new TextField<String>("accountNumber", new Model<String>());
    		add(accountNumberField);
    		add(new AjaxButton("addAccountButton") {
    			@Override
    			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
    				accountRepository.addAccount(new Account(accountNumberField.getDefaultModelObjectAsString()));
    				target.addComponent(AccountsPanel.this);
    			}});
    	}
    }
}

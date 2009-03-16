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

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.accounts.HomePage;
import org.laughingpanda.kansanpankki.domain.Account;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountPageSpec extends ComponentSpecification<AccountPage, Account> {
    private Account account = new Account("");

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
            AjaxLink<Void> backLink = selectFirst(AjaxLink.class, "back").from(context);
            specify(backLink, isNotNull());
        }
    }

    @Override
    protected AccountPage newComponent(String id, IModel<Account> model) {
        return new AccountPage(model, new HomePage());
    }
}

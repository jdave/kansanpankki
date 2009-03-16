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

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class AccountPage extends WebPage {
    public AccountPage(PageParameters pageParameters) {
        String accountId = pageParameters.getString("accountId");
        add(new AccountIdLabel("accountHeaderId", accountId));
        add(new AccountIdLabel("accountId", accountId));
    }

    private class AccountIdLabel extends Label {
        public AccountIdLabel(String id, String label) {
            super(id, label);
        }
    }
}


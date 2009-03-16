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

import org.apache.wicket.PageParameters;
import org.apache.wicket.model.IModel;
import org.junit.runner.RunWith;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountPageSpec extends ComponentSpecification<AccountPage, Void> {
	public class Any {
		public AccountPage create() {
			return startComponent();
		}

		public void pageIsRendered() {
			specify(context, isNotNull());
		}
	}

	@Override
	protected AccountPage newComponent(String id, IModel<Void> model) {
		return new AccountPage(new PageParameters());
	}
}

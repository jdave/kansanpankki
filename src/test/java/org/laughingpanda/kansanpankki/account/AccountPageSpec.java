package org.laughingpanda.kansanpankki.account;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;
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
		return new AccountPage();
	}
}

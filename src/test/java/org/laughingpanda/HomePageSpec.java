package org.laughingpanda;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;

import org.apache.wicket.model.IModel;
import org.junit.runner.RunWith;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class HomePageSpec extends ComponentSpecification<HomePage, Void> {
	public class Any {
		public HomePage create() {
			return startComponent();
		}
		
		public void containsAccountsPanel() {
			specify(selectFirst(AccountsPanel.class).from(context), isNotNull());
		}
	}
	@Override
	protected HomePage newComponent(String id, IModel<Void> model) {
		return new HomePage();
	}
}

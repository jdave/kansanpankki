package org.laughingpanda;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;

import org.apache.wicket.model.IModel;
import org.junit.runner.RunWith;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountsPanelSpec extends ComponentSpecification<AccountsPanel, Void> {
	public class MultipleTransactions {
		public AccountsPanel create() {
			return startComponent();
		}
		
		public void rendersPanel() {}
	}
	
	@Override
	protected AccountsPanel newComponent(String id, IModel<Void> model) {
		return new AccountsPanel(id);
	}
}

package org.laughingpanda;

import java.util.List;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.junit.runner.RunWith;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountsPanelSpec extends ComponentSpecification<AccountsPanel, Void> {
	public class PanelContainingMultipleTransactions {
		public AccountsPanel create() {
			return startComponent();
		}
		
		public void containsDataView() {
			specify(accountsView(), isNotNull());
		}

		
		public void containsAccountIdLabels() {
			List<Component> accounts = selectAll(Label.class, "accountId").from(accountsView());
			specify(accounts.size(), does.equal(2));
			specify(accounts.get(0).getDefaultModelObjectAsString(), does.equal("9500-12345"));
			specify(accounts.get(0).getDefaultModelObjectAsString(), does.equal("9500-12345"));
		}
	}
	
	private DataView<String> accountsView() {
		return selectFirst(DataView.class, "accounts").from(context);
	}

	@Override
	protected AccountsPanel newComponent(String id, IModel<Void> model) {
		return new AccountsPanel(id);
	}
}

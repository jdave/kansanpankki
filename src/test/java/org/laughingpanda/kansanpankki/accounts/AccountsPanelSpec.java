package org.laughingpanda.kansanpankki.accounts;

import java.util.Arrays;
import java.util.List;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.account.AccountPage;
import org.laughingpanda.kansanpankki.domain.Account;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountsPanelSpec extends ComponentSpecification<AccountsPanel, Void> {
	public class PanelContainingMultipleAccounts {
		public AccountsPanel create() {
			return startComponent();
		}
		
		public void containsDataView() {
			specify(accountsView(), isNotNull());
		}
		
		public void containsAccountIdLabels() {
			specify(accountLabels().size(), does.equal(2));
			specify(accountLabels().get(0).getDefaultModelObjectAsString(), does.equal("9500-12345"));
			specify(accountLabels().get(0).getDefaultModelObjectAsString(), does.equal("9500-12345"));
		}
		
		public void containsAccountLinks() {
			specify(accountLinks().size(), does.equal(2));
		}
		
		public void accountLinkCanBeClicked() {
			wicket.executeAjaxEvent(accountLinks().get(0), "onclick");
		}
	}

	public class PanelWhenAccountLinkIsClicked {
		public AccountsPanel create() {
			AccountsPanel panel = startComponent();
			wicket.executeAjaxEvent(selectAll(AjaxLink.class, "accountLink").from(panel).get(0), "onclick");
			return panel;
		}
		
		public void isInAccountPage() {
			specify(wicket.getLastRenderedPage().getClass(), does.equal(AccountPage.class));
		}
	}
	
	private List<? extends Component> accountLinks() {
        return selectAll(AjaxLink.class, "accountLink").<AjaxLink<?>>from(accountsView());
	}
	
	private List<? extends Component> accountLabels() {
		return selectAll(Label.class, "accountId").<Label>from(accountsView());
	}
	
	private DataView<String> accountsView() {
		return selectFirst(DataView.class, "accounts").from(context);
	}

	@Override
	protected AccountsPanel newComponent(String id, IModel<Void> model) {
		return new AccountsPanel(id, new ListDataProvider<Account>(Arrays.asList(
                new Account() {
                    @Override
                    public String toString() {
                        return "9500-12345";
                    }
                }, new Account() {
                    @Override
                    public String toString() {
                        return "9500-56789";
                    }
                 }
        )));
 	}
}

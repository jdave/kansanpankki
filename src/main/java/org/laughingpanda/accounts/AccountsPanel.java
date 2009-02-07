package org.laughingpanda.accounts;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.laughingpanda.AccountPage;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class AccountsPanel extends Panel {
	public AccountsPanel(String id, IDataProvider<String> accountsProvider) {
		super(id);
		add(new DataView<String>("accounts", accountsProvider) {

			@Override
			protected void populateItem(Item<String> item) {
				AjaxLink<Void> accountLink = new AjaxLink<Void>("accountLink") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						setResponsePage(AccountPage.class);
					}
				};
				item.add(accountLink);
				accountLink.add(new Label("accountId", item
						.getDefaultModelObjectAsString()));
			}
		});
	}
}

package org.laughingpanda;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

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
				item.add(new Label("accountId", item.getDefaultModelObjectAsString()));
			}
		});
	}
}

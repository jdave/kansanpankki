package org.laughingpanda;

import java.util.Arrays;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 */
public class AccountsPanel extends Panel {
	public AccountsPanel(String id) {
		super(id);
		add(new DataView<String>("accounts", new ListDataProvider<String>(Arrays.asList("9500-12345","9500-56789"))) {

			@Override
			protected void populateItem(Item<String> item) {
				item.add(new Label("accountId", item.getDefaultModelObjectAsString()));
			}
		});
	}
}

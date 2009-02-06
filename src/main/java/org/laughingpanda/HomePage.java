package org.laughingpanda;

import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class HomePage extends WebPage {
	public HomePage() {
		add(new AccountsPanel("accountsPanel", getAccountsProvider()));
	}

	private ListDataProvider<String> getAccountsProvider() {
		return new ListDataProvider<String>(Arrays.asList("9500-12345","9500-56789"));
	}
}

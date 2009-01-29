package org.laughingpanda;

import org.apache.wicket.markup.html.WebPage;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class HomePage extends WebPage {
	public HomePage() {
		add(new AccountsPanel("accountsPanel"));
	}
}

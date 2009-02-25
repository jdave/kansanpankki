package org.laughingpanda.kansanpankki.account;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class AccountPage extends WebPage {
	public AccountPage(PageParameters pageParameters) {
		String accountId = (String) pageParameters.getString("accountId");
		add(new AccountIdLabel("accountHeaderId", accountId));
		add(new AccountIdLabel("accountId", accountId));
	}
	
	private class AccountIdLabel extends Label {
		public AccountIdLabel(String id, String label) {
			super(id, label);
		}
	}
}


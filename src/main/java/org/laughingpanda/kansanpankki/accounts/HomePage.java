package org.laughingpanda.kansanpankki.accounts;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class HomePage extends WebPage {

    public HomePage() {
		add(new AccountsPanel("accountsPanel", getAccountsProvider()));
	}

	private IDataProvider<String> getAccountsProvider() {
		return new IDataProvider<String>() {
            private List<String> accounts = Arrays.asList("9500-12345","9500-56789");
            public Iterator<? extends String> iterator(int first, int count) {
                return accounts.iterator();
            }

            public int size() {
                return accounts.size();
            }

            public IModel<String> model(String object) {
                return new Model<String>(object);
            }

            public void detach() {
            }
        };
	}
}

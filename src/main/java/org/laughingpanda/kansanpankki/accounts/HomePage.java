package org.laughingpanda.kansanpankki.accounts;

import java.util.Iterator;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.laughingpanda.kansanpankki.dao.AccountDao;
import org.laughingpanda.kansanpankki.domain.Account;
import org.laughingpanda.kansanpankki.domain.AccountRepository;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class HomePage extends WebPage {
    AccountRepository accountRepository = new AccountDao();

    public HomePage() {
		add(new AccountsPanel("accountsPanel", getAccountsProvider()));
	}

	private IDataProvider<Account> getAccountsProvider() {
		return new IDataProvider<Account>() {
            public Iterator<Account> iterator(int first, int count) {
                return allAccounts().iterator();
            }

            public int size() {
                return allAccounts().size();
            }

            public IModel<Account> model(Account object) {
                return new Model<Account>(object);
            }

            public void detach() {
            }
        };
	}

    private List<Account> allAccounts() {
        return accountRepository.findAllAccounts();
    }
}

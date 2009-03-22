/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laughingpanda.kansanpankki.domain;

import java.util.List;
import jdave.Block;
import jdave.Specification;
import jdave.contract.EqualsHashCodeContract;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class AccountSpec extends Specification<Account> {
    private final Account account = new Account("9500-00000");

    public class AnyAccount {
        public void isEqualToAccountWithSameId() {
            specify(account, satisfies(new EqualsHashCodeContract<Account>() {
                @Override
                protected Account equal() {
                    return new Account("9500-00000");
                }

                @Override
                protected Account nonEqual() {
                    return new Account("12345-6789");
                }

                @Override
                protected Account subType() {
                    return new Account("9500-00000") {};
                }
            }));
        }
    }

    public class NewAccount {
        public Account create() {
            return new Account("9500-00000");
        }

        public void hasBalanceOfZero() {
            specify(context.getBalance(), does.equal(Money.euros(0)));
        }

        public void isEmpty() {
            specify(context.isEmpty());
        }
    }

    public class EmptyAccount {
        public Account create() {
            account.save(Money.euros(0));
            return account;
        }

        public void isEmpty() {
            specify(context.isEmpty());
        }

        public void hasBalanceOf9500EurosAfterSaving9500Euros() {
            context.save(Money.euros(9500));
            specify(context.getBalance(), does.equal(Money.euros(9500)));
        }
    }

    public class AccountWithBalanceOf50Euros {
        public Account create() {
            account.save(Money.euros(50));
            return account;
        }

        public void isNotEmpty() {
            specify(context.isEmpty(), does.equal(false));
        }

        public void hasBalanceOf80EurosAfterSaving30Euros() {
            account.save(Money.euros(30));
            specify(context.getBalance(), does.equal(Money.euros(80)));
        }

        public void hasBalanceOf90EurosAfterSaving30And10Euros() {
            account.save(Money.euros(30));
            account.save(Money.euros(10));
            specify(context.getBalance(), does.equal(Money.euros(90)));
        }

        public void hasBalanceOf45EurosAfterWithdrawing5Euros() {
            account.withdraw(Money.euros(5));
            specify(context.getBalance(), does.equal(Money.euros(45)));
        }

        public void hasBalanceOf42EurosAfterWithdrawing5And3Euros() {
            account.withdraw(Money.euros(5));
            account.withdraw(Money.euros(3));
            specify(context.getBalance(), does.equal(Money.euros(42)));
        }

        public void throwsExceptionOnWithdrawing100Euros() {
            specify(new Block() {
                @Override
                public void run() throws Throwable {
                    account.withdraw(Money.euros(100));
                }
            }, does.raiseExactly(IllegalArgumentException.class,
                    "Cannot withdraw 100 euros from an account that has only 50 euros"));
        }

        public void stillHasBalanceOf50EurosAfterTryingToWithdrawTooMuch() {
            specify(new Block() {
                @Override
                public void run() throws Throwable {
                    account.withdraw(Money.euros(100));
                }
            }, does.raise(Exception.class));
            specify(context.getBalance(), does.equal(Money.euros(50)));
        }

        public void becomesEmptyAfterWithDrawing50Euros() {
            context.withdraw(Money.euros(50));
            specify(context.isEmpty());
        }
    }

    public class AccountWithTransfers {
        private Account sourceAccount = new Account("1234-5678").save(Money.euros(1000));
        private Account targetAccount = new Account("9876-5432");

        public Account create() {
            Account account = new Account("1111-2222");
            sourceAccount.transfer(Money.euros(500)).to(account);
            account.transfer(Money.euros(100)).to(targetAccount);
            return account;
        }

        public void containsTransferHistory() {
            List<AccountTransaction> transactions = context.getTransactions();
            specify(transactions.size(), does.equal(2));

            AccountTransaction save = transactions.get(0);
            specify(save.getSource(), does.equal(sourceAccount));
            specify(save.getTarget(), does.equal(context));
            specify(save.getAmount(), does.equal(Money.euros(500)));

            AccountTransaction withdraw = transactions.get(1);
            specify(withdraw.getSource(), does.equal(context));
            specify(withdraw.getTarget(), does.equal(targetAccount));
            specify(withdraw.getAmount(), does.equal(Money.euros(100)));
        }

        public void transferHistoryIsRecordedOnBothAccounts() {
            List<AccountTransaction> sourceAccountTransactions = sourceAccount.getTransactions();
            specify(sourceAccountTransactions.size(), does.equal(1));

            AccountTransaction save = sourceAccountTransactions.get(0);
            specify(save.getSource(), does.equal(sourceAccount));
            specify(save.getTarget(), does.equal(context));
            specify(save.getAmount(), does.equal(Money.euros(500)));

            List<AccountTransaction> targetAccountTransactions = targetAccount.getTransactions();
            specify(targetAccountTransactions.size(), does.equal(1));
            AccountTransaction withdraw = targetAccountTransactions.get(0);
            specify(withdraw.getSource(), does.equal(context));
            specify(withdraw.getTarget(), does.equal(targetAccount));
            specify(withdraw.getAmount(), does.equal(Money.euros(100)));
        }
    }
}

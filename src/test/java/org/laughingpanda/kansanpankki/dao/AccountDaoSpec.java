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
package org.laughingpanda.kansanpankki.dao;

import jdave.Block;
import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.domain.Account;

@RunWith(JDaveRunner.class)
public class AccountDaoSpec extends Specification<Account> {
    private AccountDao accountDao = new AccountDao();

    public class NewAccountWithNewAccountId {
        public Account create() {
            return new Account("91823-12934");
        }

        public void canBeAdded() {
            specify(accountDao.findAllAccounts(), does.not().contain(context));
            accountDao.addAccount(context);
            specify(accountDao.findAllAccounts(), does.contain(context));
        }
    }

    public class NewAccountWithExistingAccountId {
        public Account create() {
            String existingAccountId = accountDao.findAllAccounts().iterator().next().getAccountId();
            return new Account(existingAccountId);
        }

        public void throwsExceptionWhenAdding() {
            specify(new Block() {
                @Override
                public void run() throws Throwable {
                    accountDao.addAccount(context);
                }
            }, does.raiseExactly(IllegalArgumentException.class));
        }
    }
}

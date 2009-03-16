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

import java.io.Serializable;

public class AccountTransaction implements Serializable {
    private final Account source;
    private final Account target;
    private final Money amount;

    private AccountTransaction(Account source, Account target, Money amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    public static AccountTransaction transfer(Account source, Account target, Money amountToTransfer) {
        source.withdraw(amountToTransfer);
        target.save(amountToTransfer);
        return new AccountTransaction(source, target, amountToTransfer);
    }

    public Account getSource() {
        return source;
    }

    public Account getTarget() {
        return target;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount + " from " + source.getAccountId() + " to " + target.getAccountId();
    }
}

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

public class Money implements Serializable {
    private final int amountOfEuros;

    private Money(int amountOfEuros) {
        this.amountOfEuros = amountOfEuros;
    }

    public static Money euros(int amountOfEuros) {
        return new Money(amountOfEuros);
    }

    public Money add(Money money) {
        return new Money(amountOfEuros + money.amountOfEuros);
    }

    public Money subtract(Money money) {
        return new Money(amountOfEuros - money.amountOfEuros);
    }

    public boolean lessThan(Money money) {
        return amountOfEuros < money.amountOfEuros;
    }

    @Override
    public String toString() {
        return amountOfEuros + " euros";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amountOfEuros == money.amountOfEuros;
    }

    @Override
    public final int hashCode() {
        return amountOfEuros;
    }
}

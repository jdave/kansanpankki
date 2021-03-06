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

import jdave.Specification;
import jdave.contract.EqualsHashCodeContract;
import jdave.contract.SerializableContract;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class MoneySpec extends Specification<Money> {
    public class AnyEuros {
        public void isSerializable() {
            specify(Money.euros(15), satisfies(new SerializableContract()));
        }
        
        public void isEqualToItself() {
        	final Money tenEuros = Money.euros(10);            
        	specify(tenEuros, satisfies(new EqualsHashCodeContract<Money>() {
                @Override
                protected Money equal() {
                    return tenEuros;
                }

                @Override
                protected Money nonEqual() {
                    return Money.euros(20);
                }

                @Override
                protected Money subType() {
                    return null;
                }
            }));
        }

        public void isEqualToMoneyWithSameAmountOfEuros() {
            specify(Money.euros(10), satisfies(new EqualsHashCodeContract<Money>() {
                @Override
                protected Money equal() {
                    return Money.euros(10);
                }

                @Override
                protected Money nonEqual() {
                    return Money.euros(20);
                }

                @Override
                protected Money subType() {
                    return null;
                }
            }));
        }
    }
}

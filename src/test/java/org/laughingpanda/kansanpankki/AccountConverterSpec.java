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
package org.laughingpanda.kansanpankki;

import java.util.Locale;
import jdave.Block;
import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;
import org.laughingpanda.kansanpankki.domain.Account;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class AccountConverterSpec extends Specification<AccountConverter> {
    public class Any {
    	public AccountConverter create() {
    		return new AccountConverter();
    	}
    	
    	public void canConvertToString() {
    		specify(context.convertToString(new Account("9500-12345"), new Locale("fi")), does.equal("9500-12345"));
    	}
    	
    	public void throwsExceptionWhenTryingToConvertObject() {
    		specify(new Block() {
                @Override
                public void run() throws Throwable {
                	context.convertToObject(("9500-12345"), new Locale("fi"));
                }
            }, should.raise(UnsupportedOperationException.class));
    	}
    }
}

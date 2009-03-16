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

import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.convert.ConverterLocator;
import org.laughingpanda.kansanpankki.accounts.HomePage;
import org.laughingpanda.kansanpankki.domain.Account;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
public class KansanpankkiApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
    
    @Override
    protected IConverterLocator newConverterLocator() {
    	ConverterLocator converterLocator = new ConverterLocator();
    	converterLocator.set(Account.class, new AccountConverter());
    	return converterLocator;
    }

    @Override
    protected void outputDevelopmentModeWarning() {
    }
}
